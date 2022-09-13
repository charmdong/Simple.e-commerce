package com.commerce.api;

import com.commerce.message.MessageConstants;
import com.commerce.service.OrderService;
import com.commerce.util.SessionUtils;
import com.commerce.vo.SessionVO;
import com.commerce.vo.order.OrderItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 주문하기
     * @param sessionVO
     * @param request
     * @return
     * @throws RuntimeException
     */
    @PostMapping("/order")
    public ResponseEntity<Map<String, Object>> orderItem (@SessionAttribute(name = SessionUtils.LOGIN_SESSION) SessionVO sessionVO, @RequestBody OrderItemRequest request) throws RuntimeException {

        String userId = sessionVO.getId();

        orderService.order(userId, request.getItemId(), request.getCount());

        Map<String, Object> result = new HashMap<>();
        result.put("data", true);
        result.put("message", MessageConstants.ORDER_SUCCESS);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 주문 취소
     * @param orderId
     * @return
     */
    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Map<String, Object>> cancelOrder (@PathVariable("orderId") Long orderId) {

        orderService.cancelOrder(orderId);

        Map<String, Object> result = new HashMap<>();
        result.put("data", true);
        result.put("message", MessageConstants.ORDER_CANCEL);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
