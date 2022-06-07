package com.commerce.api;

import com.commerce.dto.SessionVO;
import com.commerce.dto.order.CartDto;
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

        String message = "SUCCESS";
        if (result == true) {
            message = "이미 장바구니에 존재합니다.";
        }
        response.put("message", message);

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

        CartDto cartDto = cartService.addCart(userId, itemId, count);
        Map<String, Object> result = new HashMap<>();
        result.put("data", cartDto);
        result.put("message", "장바구니에 담았습니다.");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("/{cartId}")
    public ResponseEntity<Map<String, Object>> updateCart (@PathVariable("cartId") Long cartId, @RequestParam("count") int count) {

        cartService.updateCart(cartId, count);
        Map<String, Object> result = new HashMap<>();
        result.put("data", true);
        result.put("message", "SUCCESS");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 장바구니 삭제
     * @param session
     * @param itemId
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Map<String, Object>> removeCart (@SessionAttribute(name = SessionUtils.LOGIN_SESSION) SessionVO session,
                                                           @RequestParam("itemId") Long itemId) {
        String userId = session.getId();

        cartService.removeCart(userId, itemId);
        Map<String, Object> result = new HashMap<>();
        result.put("data", true);
        result.put("message", "장바구니에 추가되었습니다.");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
