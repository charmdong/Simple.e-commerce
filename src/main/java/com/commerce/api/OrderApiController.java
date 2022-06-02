package com.commerce.api;

import com.commerce.dto.SessionVO;
import com.commerce.dto.order.CartDto;
import com.commerce.dto.order.OrderItemRequest;
import com.commerce.service.OrderService;
import com.commerce.util.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * OrderApiController
 *
 * @author donggun
 * @since 2022/06/02
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Map<String, Object>> orderItem (HttpSession session, @RequestBody OrderItemRequest request) throws RuntimeException {

        SessionVO sessionVO = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        String userId = sessionVO.getId();

        orderService.order(userId, request.getItemId(), request.getCount());

        Map<String, Object> result = new HashMap<>();
        result.put("data", true);
        result.put("message", "주문이 완료되었습니다.");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 장바구니 추가
     * @param session
     * @param itemId
     * @param count
     * @return
     */
    @PostMapping("/cart")
    public ResponseEntity<Map<String, Object>> addCart (HttpSession session,
                                                        @RequestParam("itemId") Long itemId,
                                                        @RequestParam("count") int count) {

        SessionVO sessionVO = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        String userId = sessionVO.getId();

        CartDto cartDto = orderService.addCart(userId, itemId, count);
        Map<String, Object> result = new HashMap<>();
        result.put("data", cartDto);
        result.put("message", "SUCCESS");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("/cart/{cartId}")
    public ResponseEntity<Map<String, Object>> updateCart (HttpSession session,
                                                        @PathVariable("cartId") Long cartId,
                                                        @RequestParam("count") int count) {

        orderService.updateCart(cartId, count);
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
    @DeleteMapping("/cart")
    public ResponseEntity<Map<String, Object>> removeCart (HttpSession session,
                                                           @RequestParam("itemId") Long itemId) {
        SessionVO sessionVO = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        String userId = sessionVO.getId();

        orderService.removeCart(userId, itemId);
        Map<String, Object> result = new HashMap<>();
        result.put("data", true);
        result.put("message", "장바구니에 추가되었습니다.");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
