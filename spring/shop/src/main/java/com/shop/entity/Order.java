package com.shop.entity;
import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order extends  BaseEntity{

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate; //주문일


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //속성값 order 로 적어준이유는 OrderItem 에 있는 Order에 의해 관리
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL
    ,orphanRemoval = true, fetch = FetchType.LAZY)
    // cascade = CascadeType.ALL(REMOVE) 게시글삭제 전체댓글 삭제
    // orphanRemoval = true
    // post.getComments().remove(0) - 게시글(부모) 은 그대로 두고
    // 댓글리스트에서 특정 댓글 삭제

    private List<OrderItem> orderItems = new ArrayList<>();



}
