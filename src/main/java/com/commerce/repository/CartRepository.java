package com.commerce.repository;

import com.commerce.domain.Cart;
import com.commerce.domain.Item;
import com.commerce.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c" +
            " join fetch c.item i" +
            " inner join c.member m" +
            " where m.id = :userId")
    List<Cart> findAllByUserId(@Param("userId") String userId);

    Cart findByItemAndMember(Item item, Member member);

    void deleteByMemberAndItem(Member member, Item item);
}
