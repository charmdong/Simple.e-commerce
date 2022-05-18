package com.commerce.controller;

import com.commerce.dto.SessionVO;
import com.commerce.dto.order.OrderDto;
import com.commerce.service.OrderService;
import com.commerce.util.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 주문 폼 이동
     *
     * @return
     */
    @GetMapping("/order")
    public String orderForm () {
        return "order/orderForm";
    }

    /**
     * 주문
     *
     * @param userId
     * @param itemId
     * @param count
     * @return
     */
    @PostMapping("/order")
    public String order (@RequestParam("memberId") String userId, @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
        orderService.order(userId, itemId, count);
        return "redirect:/orders";
    }

    /**
     * 주문 목록 조회
     *
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/orders")
    public String orderList (HttpSession session, Model model) {
        SessionVO sessionVO = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        String userId = sessionVO.getId();
        List<OrderDto> orderList = orderService.findOrdersByMember(userId);
        model.addAttribute("orderList", orderList);

        return "order/orderList";
    }

    /**
     * 주문 취소
     *
     * @param orderId
     * @return
     */
    @GetMapping("/orders/cancel/{orderId}")
    public String cancelOrder (@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
