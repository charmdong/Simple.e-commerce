package com.commerce.controller;

import com.commerce.service.CartService;
import com.commerce.util.SessionUtils;
import com.commerce.vo.SessionVO;
import com.commerce.vo.order.CartVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * 장바구니 목록 조회
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/carts")
    public String cartList(@SessionAttribute(name = SessionUtils.LOGIN_SESSION) SessionVO session,
                           @RequestParam(name = "itemName", required = false) String itemName, Model model) {

        String userId = session.getId();
        List<CartVO> cartList;

        // 상품명 존재
        if (StringUtils.hasText(itemName)) {
            cartList = cartService.findCartListByItemName(userId, itemName);
        } else {
            cartList = cartService.findCartByUserId(userId);
        }

        model.addAttribute("cartList", cartList);
        return "cart/cartList";
    }
}
