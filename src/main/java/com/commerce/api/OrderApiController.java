package com.commerce.api;

import com.commerce.dto.SessionVO;
import com.commerce.dto.order.OrderItemRequest;
import com.commerce.service.OrderService;
import com.commerce.util.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Map<String, Object>> orderItem(HttpSession session, @RequestBody OrderItemRequest request) {

        SessionVO sessionVO = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        String userId = sessionVO.getId();

        orderService.order(userId, request.getItemId(), request.getCount());

        Map<String, Object> result = new HashMap<>();
        result.put("result", true);
        result.put("message", "주문이 완료되었습니다.");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
