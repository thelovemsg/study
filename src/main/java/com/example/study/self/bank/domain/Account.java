package com.example.study.self.bank.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.AbstractCollection;
import java.util.Currency;
import java.util.Map;

/***
    시나리오 1
    - 일일 출금 한도 체크
    - VIP 고객 수수료 면제 처리  
    - 연체 이자 계산 및 적용
    - 휴면 계좌 전환 (90일 미사용시)

    시나리오 2
    - 필자는 주식을 자주 한다.
      해외 투자시에는 (예를 들어, 미국 주식) 원화를 달러로 변환한 후 달러로 주식을 구매해야 한다.
      주식을 구매한다면, 처음 주식을 구매했던 때에 비해서 현재 얼마가 이득인지 혹은 손해인지를 퍼센트로 확인할 수 있으며,
      달러의 경우 처음 환전한 금액들을 환율의 오르내림에 따라 얼마큼의 금액이 이득인지도 확인할 수 있다.

    - 다양한 주식을 구매할 수 있다. 여기서는 국내 -> 미국 으로 구매를 자주 하는 필자의 상황을 예시로 들어서 tdd를 연습할 것이다.

    은행 계좌번호를 이루는 번호중...
    => 은행코드(3) / 지점코드(3) / 계좌일련번호(6~8)
        라고 한다.

    필자는 갯수만 흉내내도록 하겟다.
* */

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class Account {

    private String accountId;
    private String accountNumber;
    private Money mount;
    private Money limitPerDay;
    private Map<Currency, Money> balances;

    public Account createAccount(BigDecimal initialAmount, Currency currency) {
        return null;
    }

    public Account of(BigDecimal initialAmount) {
        return null;
    }

    public Account of(BigDecimal initialAmount, Currency currency) {
        return null;
    }

}
