package com.commerce.service;

import com.commerce.domain.Cart;
import com.commerce.domain.Item;
import com.commerce.domain.Member;
import com.commerce.vo.order.CartVO;
import com.commerce.repository.CartRepository;
import com.commerce.repository.ItemRepository;
import com.commerce.repository.MemberRepository;
import com.commerce.repository.OrderRepository;
import com.commerce.util.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;


    /**
     * 아이템 장바구니 중복 여부 확인
     * @param userId
     * @param itemId
     * @return
     */
    public Boolean isCartDuplicated (String userId, Long itemId) {

        Member member = memberRepository.findById(userId).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.USER_NOT_FOUND));
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.ITEM_NOT_FOUND));

        return cartRepository.findByItemAndMember(item, member).isPresent();
    }

    /**
     * 장바구니 추가
     * @param userId
     * @param itemId
     * @param count
     */
    public CartVO addCart (String userId, Long itemId, int count) {

        Member member = memberRepository.findById(userId).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.USER_NOT_FOUND));
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.ITEM_NOT_FOUND));

        Cart cart = Cart.createCart(member, item, count);
        cartRepository.save(cart);

        return new CartVO(cart);
    }

    /**
     * 장바구니 목록 조회
     * @param userId
     * @return
     */
    @Transactional(readOnly = true)
    public List<CartVO> findCartByUserId (String userId) {
        return cartRepository.findAllByUserId(userId).stream().map(CartVO::new).collect(Collectors.toList());
    }

    /**
     * 장바구니 목록 조회 (상품명)
     * @param userId
     * @param itemName
     * @return
     */
    @Transactional(readOnly = true)
    public List<CartVO> findCartListByItemName (String userId, String itemName) {
        return cartRepository.findByUserIdAndItemName(userId, itemName).stream().map(CartVO::new).collect(Collectors.toList());
    }

    /**
     * 장바구니 수정
     * @param cartId
     * @param count
     */
    public void updateCart (Long cartId, int count) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.CART_NOT_FOUND));
        cart.updateCart(count);
    }

    /**
     * 장바구니 삭제
     * @param cartId
     */
    public void removeCart (Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
