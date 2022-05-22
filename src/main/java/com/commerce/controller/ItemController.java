package com.commerce.controller;

import com.commerce.controller.form.ItemForm;
import com.commerce.domain.Item;
import com.commerce.domain.Role;
import com.commerce.dto.CategoryDto;
import com.commerce.dto.ItemDto;
import com.commerce.dto.SessionVO;
import com.commerce.service.CategoryService;
import com.commerce.service.ItemService;
import com.commerce.util.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;

    /**
     * 상품 등록 폼 이동
     *
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String createForm (Model model) {
        List<CategoryDto> categoryList = categoryService.categoryList();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("form", new ItemForm());

        return "items/createItemForm";
    }

    /**
     * 상품 등록
     *
     * @param form
     * @return
     */
    @PostMapping("/new")
    public String create (ItemForm form) {
        Item item = Item.createItem(form);
        itemService.saveItem(item);

        return "redirect:/";
    }

    /**
     * 상품 목록 조회
     *
     * @param model
     * @return
     */
    @GetMapping
    public String getItemList (HttpSession session, Model model) {
        SessionVO loginInfo = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);

        List<ItemDto> items = new ArrayList<>();

        // 판매자인 경우
        if (loginInfo.getRole() == Role.SALE) {
            items = itemService.findItems(loginInfo.getId());
        }
        // 관리자, 일반 사용자의 경우
        else {
            items = itemService.findAllItems();
        }

        model.addAttribute("items", items);

        return "items/itemList";
    }

    /**
     * 상품 상세 조회
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{itemId}")
    public String itemDetail (@PathVariable("itemId") Long id, Model model) {
        ItemDto item = itemService.findOne(id);
        model.addAttribute("item", item);

        return "items/itemDetail";
    }

    /**
     * 상품 수정 폼 이동
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("{itemId}/edit")
    public String updateItemForm (@PathVariable("itemId") Long id, Model model) {
        ItemDto item = itemService.findOne(id);
        ItemForm itemForm = ItemForm.createItemForm(item);
        model.addAttribute("form", itemForm);

        return "items/updateItemForm";
    }

    /**
     * 상품 수정
     *
     * @param id
     * @param form
     * @return
     */
    @PostMapping("{itemId}/edit")
    public String updateItem (@PathVariable("itemId") Long id, @ModelAttribute("form") ItemForm form) {
        itemService.updateItem(id, form);

        return "redirect:/items/" + id;
    }

    /**
     * 상품 삭제
     *
     * @param id
     * @return
     */
    @GetMapping("{itemId}/delete")
    public String deleteItem (@PathVariable("itemId") Long id) {
        itemService.deleteItem(id);

        return "redirect:/items";
    }
}
