package com.example.study.self.bank.domain;

import jakarta.interceptor.InterceptorBinding;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

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
@Getter
public class Account {

    private static final Currency KRW = Currency.getInstance("KRW");

    private final String accountId;
    private final String accountNumber;

    @Builder.Default
    private final Money limitPerDay = Money.of(BigDecimal.ZERO);

    @Builder.Default
    private final boolean dormant = false;

    private Map<Currency, Money> balances;

    private Account(String accountId,
                    String accountNumber,
                    Money limitPerDay,
                    boolean dormant,
                    Map<Currency, Money> balances) {

        this.accountId = Objects.requireNonNull(accountId, "accountId");
        this.accountNumber = Objects.requireNonNull(accountNumber, "accountNumber");
        this.limitPerDay = limitPerDay == null ? Money.of(BigDecimal.ZERO) : limitPerDay;
        this.dormant = dormant;
        // 내부 저장은 항상 불변 Map
        this.balances = Map.copyOf(
                balances == null ? defaultBalances() : balances
        );
    }

    public static Account create(String accountId, String accountNumber) {
        return new Account(accountId, accountNumber, Money.of(BigDecimal.ZERO), false, defaultBalances());
    }

    private static Map<Currency, Money> defaultBalances() {
        Map<Currency, Money> init = new HashMap<>();
        init.put(KRW, Money.zero()); // 초기 KRW 0원
        return init;
    }

    /* ---------- 행동(항상 새 인스턴스 반환) ---------- */

    /** 국내 전용: 금액만 → KRW 입금 */
    public Account deposit(Money money) {
        return deposit(money, KRW);
    }

    /** 통화 지정 입금 */
    public Account deposit(Money money, Currency currency) {
        requireNotDormant();

        Map<Currency, Money> next = new HashMap<>(this.balances); // ① 복사
        next.merge(currency, money, Money::add);
        return new Account(accountId, accountNumber, limitPerDay, dormant, next); // ② 새 인스턴스
    }

    public Account withdraw(Money money) {
        return withdraw(money, KRW);
    }

    public Account withdraw(Money money, Currency currency) {
        requireNotDormant();

        Money current = balances.get(currency);
        if (current == null) {
            throw new IllegalArgumentException("해당 통화의 잔액이 없습니다: " + currency);
        }
        Map<Currency, Money> next = new HashMap<>(this.balances);
        next.put(currency, current.subtract(money));
        return new Account(accountId, accountNumber, limitPerDay, dormant, next);
    }

    public Account withDormant(boolean dormant) {
        if (this.dormant == dormant) return this; // no-op 최적화
        return new Account(accountId, accountNumber, limitPerDay, dormant, balances);
    }

    public Account withLimitPerDay(Money newLimit) {
        if (Objects.equals(this.limitPerDay, newLimit)) return this; // no-op 최적화
        return new Account(accountId, accountNumber, newLimit, dormant, balances);
    }

    private void requireNotDormant() {
        if (dormant) throw new IllegalArgumentException("거래 정지 계좌");
    }

}
