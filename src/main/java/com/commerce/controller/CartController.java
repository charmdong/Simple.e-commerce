package com.commerce.controller;

import com.commerce.dto.SessionVO;
import com.commerce.dto.order.CartDto;
import com.commerce.service.CartService;
import com.commerce.util.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/carts")
    public String cartList(@SessionAttribute(name = SessionUtils.LOGIN_SESSION) SessionVO session, Model model) {
        String userId = session.getId();
        List<CartDto> cartList = cartService.findCartByUserId(userId);

        model.addAttribute("cartList", cartList);
        return "cart/cartList";
    }
}
