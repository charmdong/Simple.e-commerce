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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Map<String, Object>> orderItem(HttpSession session, @RequestBody OrderItemRequest request) throws RuntimeException {

        SessionVO sessionVO = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        String userId = sessionVO.getId();

        orderService.order(userId, request.getItemId(), request.getCount());

        Map<String, Object> result = new HashMap<>();
        result.put("data", true);
        result.put("message", "주문이 완료되었습니다.");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/cart")
    public ResponseEntity<Map<String, Object>> saveCart(HttpSession session,
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

}
