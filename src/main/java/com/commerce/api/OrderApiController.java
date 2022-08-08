package com.commerce.api;

import com.commerce.message.MessageConstants;
import com.commerce.vo.SessionVO;
import com.commerce.vo.order.OrderItemRequest;
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
        result.put("message", MessageConstants.ORDER_SUCCESS);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
