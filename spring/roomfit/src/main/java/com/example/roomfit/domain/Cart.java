package com.example.roomfit.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) //회원 1명 <-> 장바구니 1개
    @JoinColumn(nullable = false, unique = true)//unique = true -> db에서 회원당 cart1개만 허용
    private Member member; //LAZY -> member는 필요시만 조회

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true) //장바구니한개 <-> cartItem 여러개
    @Builder.Default //빈 리스트 초기화
    private List<CartItem> items = new ArrayList<>();
    //orphanRemoval = true -> 목록에서 빠진 item은 DB에서도 삭제
    //cascade = CascadeType.ALL  cart 저장/삭제 시 item 도 함께 처리
}
//   Member(1) - (1) Cart - (N) cartItem (N) - (1) Product
//cart 는 컨테이너 역할만 하고  실제 상품 수량 정보는 cartItem에 있다.