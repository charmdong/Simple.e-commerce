package com.commerce.api;

import com.commerce.message.MessageConstants;
import com.commerce.vo.SessionVO;
import com.commerce.vo.order.CartVO;
import com.commerce.service.CartService;
import com.commerce.util.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartApiController {

    private final CartService cartService;

    /**
     * 장바구니 중복 여부 확인
     * @param session
     * @param itemId
     * @return
     */
    @GetMapping("/{itemId}")
    public ResponseEntity<Map<String, Object>> isCartDuplicated (@SessionAttribute(name = SessionUtils.LOGIN_SESSION) SessionVO session,
                                                                 @PathVariable("itemId") Long itemId) {
        String userId = session.getId();

        Boolean result = cartService.isCartDuplicated(userId, itemId);

        Map<String, Object> response = new HashMap<>();
        response.put("data", result);
        response.put("message", result == true ? MessageConstants.CART_DUP : MessageConstants.ORDER_SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 장바구니 추가
     * @param session
     * @param itemId
     * @param count
     * @return
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addCart (@SessionAttribute(name = SessionUtils.LOGIN_SESSION) SessionVO session,
                                                        @RequestParam("itemId") Long itemId,
                                                        @RequestParam("count") int count) {
        String userId = session.getId();

        CartVO cartVO = cartService.addCart(userId, itemId, count);
        Map<String, Object> result = new HashMap<>();
        result.put("data", cartVO);
        result.put("message", MessageConstants.CART_ADD);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 장바구니 수정
     * @param cartId
     * @param count
     * @return
     */
    @PatchMapping("/{cartId}")
    public ResponseEntity<Map<String, Object>> updateCart (@PathVariable("cartId") Long cartId, @RequestParam("count") int count) {

        cartService.updateCart(cartId, count);
        Map<String, Object> result = new HashMap<>();
        result.put("data", true);
        result.put("message", "장바구니가 수정되었습니다.");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 장바구니 삭제
     * @param session
     * @param cartId
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Map<String, Object>> removeCart (@SessionAttribute(name = SessionUtils.LOGIN_SESSION) SessionVO session,
                                                           @RequestParam("cartId") Long cartId) {

        cartService.removeCart(cartId);
        Map<String, Object> result = new HashMap<>();
        result.put("data", true);
        result.put("message", "장바구니에서 삭제되었습니다.");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
