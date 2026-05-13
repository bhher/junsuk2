package com.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart")
@Getter
@Setter
@ToString
public class Cart extends BaseEntity {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
//fetch = FetchType.LAZY - 필요할 때 가져오기
//EAGER - 장바구니 조회할때 무조건 (Member) 도 가져와 - Join발생
// LAZY - 장바구니만 가져오기 필요할 때 cart.getMember().getName();
// 호출할때 그때 가져와 -> 나중에 쿼리발생
}
//장바구니 회원과의 관계
//회원1명 -> 장바구니1개   장바구니 1개 -> 회원한명
// Cart 테이블에 member_id(fk) 컬럼이 생기고
// 이컬럼이 맴버 테이블의 기본 (pk) 가리키게됨
// Cart엔티티가 외래키를 들고 있으면 , 이연관계의 주인
//cart.getMember().getEmail()