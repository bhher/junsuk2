package com.shop.service;

import com.shop.dto.OrderDto;
import com.shop.dto.OrderHistDto;
import com.shop.dto.OrderItemDto;
import com.shop.entity.*;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;

    private final MemberRepository memberRepository;

    private final OrderRepository orderRepository;

    private final ItemImgRepository itemImgRepository;

    //셔츠 1품목만 주문
    //상품상세페이지 - 구매하기 버튼 - order 실행 (1품목만 바로구매)
    public Long order(OrderDto orderDto, String email){

       Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

       Member member= memberRepository.findByEmail(email);

       List<OrderItem> orderItemList = new ArrayList<>();
      OrderItem orderItem=  OrderItem.createOrderItem(item, orderDto.getCount());
      orderItemList.add(orderItem);
      Order order= Order.createOrder(member, orderItemList)  ;
      orderRepository.save(order);
      return order.getId();
    }


// 여러 상품 주문 / 장바구니 전체주문
    //장바구니 체크박스 선택 -> 선택주문 -> orders
    public Long orders(List<OrderDto> orderDtoList, String email){


        Member member= memberRepository.findByEmail(email);
        List<OrderItem> orderItemList = new ArrayList<>();

        for(OrderDto orderDto: orderDtoList) {
            Item item = itemRepository.findById(orderDto.getItemId())
                    .orElseThrow(EntityNotFoundException::new);
            OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
            orderItemList.add(orderItem);
        }
//셔츠 1개 바지 1개 운동화 개


        Order order= Order.createOrder(member, orderItemList)  ;
        orderRepository.save(order);
        return order.getId();
    }




    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderId, String email){
        Member curMember = memberRepository.findByEmail(email);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = order.getMember();

        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            return false;
        }

        return true;
    }


    public void cancelOrder(long orderId){
       Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
       order.cancelOrder();
    }

    @Transactional(readOnly = true)
    public Page<OrderHistDto> getOrderList(String email, Pageable pageable){

       List<Order> orders= orderRepository.findOrders(email, pageable);
       //현재 로그인 사용자의 주문 목록 조회

       Long totalCount  = orderRepository.countOrder(email); //페이지 계산용 전체 데이터수


       List<OrderHistDto> orderHistDtos = new ArrayList<>();
        //화면에 전달할 DTO 저장 리스트

       for(Order order: orders){  //주문을 하나씩 처리
           OrderHistDto orderHistDto = new OrderHistDto(order); //order Entity -> Dto변환
           List<OrderItem> orderItems= order.getOrderItems(); //한주문 안에 상품목록 주문1개 - 셔츠 , 바지 ,가디건
           for(OrderItem orderItem : orderItems){ //상품 하나씩을 DTO변환
             ItemImg itemImg  = itemImgRepository.findByItemIdAndRepimgYn(orderItem.getItem().getId(),"Y");
               //현재 상품 대표이미지 가져오기
             OrderItemDto orderItemDto = //화면 출력용 Dto생성
                       new OrderItemDto(orderItem, itemImg.getImgUrl());
               orderHistDto.addOrderItemDto(orderItemDto); //주문 Dto안에 상품Dto를 추가
           }//주문 DTO(주문정보 , 주문상품 DTO 리스트 )

           orderHistDtos.add(orderHistDto); // 주문 DTO 리스트에 추가

       }
       //Spring page 객체생성
        return new PageImpl<OrderHistDto>(orderHistDtos, pageable, totalCount);
// PageImpl<OrderHistDto>(데이터, 페이지정보 , 전체개수)
    }
//로그인 사용자의 주문 목록을 조회 , 주문 + 주문상품 + 상품이지 정보 DTO 변환 한 뒤
    //페이징 형태로 반환 - 주문이력페이지

    //order entity - > OrderHistDto - 주문일자 , 주문정보 , 주문상태
    //orderItem entity -> OrderItemDto - 상품명 , 주문가격, 주문수량
    //ItemImg  ->  OrderItemDto  - 상품 대표이미지 Url





}
