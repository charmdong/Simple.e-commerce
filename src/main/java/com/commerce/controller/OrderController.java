package com.commerce.controller;

import com.commerce.domain.Role;
import com.commerce.repository.OrderSearch;
import com.commerce.service.OrderService;
import com.commerce.util.SessionUtils;
import com.commerce.vo.SessionVO;
import com.commerce.vo.order.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     * @param session
     * @param itemId
     * @param count
     * @return
     */
    @PostMapping("/order")
    public String order (@SessionAttribute(name = SessionUtils.LOGIN_SESSION) SessionVO session,
                         @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
        String userId = session.getId();
        orderService.order(userId, itemId, count);
        return "redirect:/orders";
    }

    /**
     * 주문 목록 조회
     *
     * @param sessionVO
     * @param model
     * @return
     */
    @GetMapping("/orders")
    public String orderList (@ModelAttribute("orderSearch") OrderSearch orderSearch,
                             @SessionAttribute(name = SessionUtils.LOGIN_SESSION) SessionVO sessionVO, Model model) {

        if (sessionVO.getRole().equals(Role.USER)) {
            orderSearch.setUserId(sessionVO.getId());
        }
        else if (sessionVO.getRole().equals(Role.SALE)) {
            // 자신이 판매하는 상품을 구매한 내역만 조회
        }

        List<OrderVO> orderList = orderService.findOrdersByCondition(orderSearch);
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
