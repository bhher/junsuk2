package com.example.roomfit.repository;

import com.example.roomfit.domain.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository  extends JpaRepository<Cart, Long> {

    //특정 repository 메서드에서만 강제로  eager 처럼 가져옴
    @EntityGraph(attributePaths = {"items", "items.product"})
    Optional<Cart> findWithItemsByMemberId(Long memberId);

    // findByMemberId 회원 Id로 cart 를 조회
    // Cart 만 가져옴 - items

    // findWithItemsByMemberId 회원 Id로 cart 를 조회
    // @EntityGraph 로 연관 데이터를 한 번 로드
    // items - CartItem 목록
    //items.product 각 item의 Product(이름, 가격, 이미지)
   //cart.getTtems() , item.getProduct()

    Optional<Cart> findByMemberId(Long memberId);



}
