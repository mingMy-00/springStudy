package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;


  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);
    //지금 할인 정책에 대해서 주문 서비스는 몰라도 된다. 할인은 그냥 discountPolicy가 알아서 하면됨.

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
}
