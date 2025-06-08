## SRP (단일 책임 원칙)
`SRP(Single Responsibility Principle)`는 객체 지향 설계 원칙 중 하나로,  
> "하나의 클래스는 하나의 책임만 가져야 한다."는 것을 의미한다.

<br>

여기서 `책임`이란, 클래스가 `해야 하는 것`이나 `할 수 있는 것`으로 간주되며,  
클래스가 변경되어야 하는 이유의 개수로 해석할 수 있다.  

<br>

예를 들어, 어떤 클래스가 비즈니스 로직 처리와 DB 저장 로직을 모두 갖고 있다면,  
- 비즈니스 규칙이 변경될 때  
- 저장 방식이 변경될 때

두 가지 서로 다른 이유로 해당 클래스를 수정해야 하므로 SRP를 위반하게 된다.

<br>

### 왜 SRP가 중요한가?
좋은 설계란, 시스템에 변경이 생겨도 영향 받는 범위를 최소화하는 구조를 말한다.  
클래스에 너무 많은 책임이 몰려 있으면 다음과 같은 문제가 발생할 수 있다.
- 특정 책임에 대한 변경이 전혀 관계없는 기능에도 영향을 준다.
- 하나의 클래스에 의존하는 코드가 많아질수록, 변경 시 테스트 범위가 넓어진다.
- 유지보수 시 수정해야 할 부분을 모두 찾기 어렵다.

<br>

### 산탄총 수술 (Shotgun Surgery)
SRP 위반은 클래스가 너무 많은 책임을 가질 때 뿐만 아니라,  
`하나의 책임이 여러 클래스에 분산`되어 있을 때도 발생한다.  
예를 들어, '결제 정책'을 변경하려면, `Order`, `PaymentService`, `DiscountPolicy` 등 여러 클래스의 메서드를 조금씩 변경해야 한다면, 이는 `산탄총 수술`이 필요한 구조이다.  
- 변경이 산발적으로 퍼져있고
- 수정이 누락될 경우 에러 가능성이 높으며
- 코드 추적 및 테스트 비용이 매우 커진다.

<br>

## OCP (개방-폐쇄 원칙)
`OCP(Open-Closed Principle)`는 
> "소프트웨어 구성 요소는 확장에는 열려(Open) 있어야 하고, 변경에는 닫혀(Closed) 있어야 한다."는 의미를 가진다.  

기존 코드를 `수정하지 않고도` 새로운 기능을 `확장`할 수 있도록 설계해야 한다는 원칙이다.  
즉, `기존 코드를 건드리지 않고`, 새로운 클래스를 `추가`하거나 `구현체를 교체`함으로써 기능을 확장할 수 있어야 한다.  
반대로, 새로운 기능을 추가할 때 기존의 클라이언트 코드를 수정해야 한다면, OCP를 위반하는 것이다.  

<br>

### OCP를 지키는 핵심 : 추상화와 다형성
OCP를 구현하는 핵심 기법은 다음과 같다.
1. `변하지 않는 부분과 변하는 부분을 분리`한다.
2. 변하는 부분은 `추상화(인터페이스 또는 추상 클래스)`로 감싸고
3. 클라이언트는 구체적인 구현이 아니라, `추상화에 의존`하도록 만든다.
즉, 기능 확장 시 `구현체를 추가하면 되도록` 설계하는 것이다.

<br>

### 예제 코드
``` java
// OCP 위반
public class PaymentService {
	public void pay(String type) {
		if(type.equals("KAKAO")) {
			// 카카오 결제
		} else if(type.equals("NAVER")) {
			// 네이버 결제
		}
 	}
}
```

``` java
// OCP 준수
public interface PaymentStrategy {
	void pay();
}

public class KakaoPay implements PaymentStrategy {
	public void pay() {
		// 카카오 결제
	}
}

public class NaverPay implements PaymentStrategy {
	public void pay() {
		// 네이버 결제
	}
}

public class PaymentService {
	private final PaymentStrategy strategy;

	public PaymentService(PaymentStrategy strategy) {
		this.strategy = strategy;
	}

	pulbic void executePayment() {
		strategy.pay();
	}
}
```
- 새로운 결제 수단은 `PaymentStrategy`를 구현한 클래스를 추가하면 되고,  
- 기존의 `PaymentService` 코드는 변경 없이 그대로 유지할 수 있다.

<br>

### 테스트와 OCP
OCP는 단위 테스트에서도 매우 유용하다.  
- 테스트 대상 클래스가 외부 서비스(DB, 네트워크)에 의존할 경우,
- 인터페이스를 통해 추상화하고, 테스트 시에는 가짜(Mock) 구현체로 대체할 수 있다.

이처럼, 클래스 외부의 변경에 유연하게 대응할 수 있는 구조를 만드는데 OCP는 중요한 역할을 한다.

<br>

## LSP (리스코프 치환 원칙)
`LSP(Liskov Substitution Principle)`는  
> "자식 클래스는 언제나 부모 클래스를 대체되어도 프로그램이 정상적으로 작동해야 한다."는 의미를 가진다.  

즉, 부모 클래스의 인스턴스를 사용하는 코드가 자식 클래스로 교체되어도 동작이 변하지 않아야 하며, 예외나 예기치 않은 결과가 발생해서는 안된다.  

<br>

### 예제 코드
```java
public class Bag {  
    private int price;  
  
    public void setPrice(int price) {  
        this.price = price;  
    }  
  
    public int getPrice() {  
        return price;  
    }  
}
```

```java
// LSP 위반
public class DiscountedBag extends Bag {  
    private double discountedRate = 10;  
  
    public void setDiscountedRate(double discountedRate) {  
        this.discountedRate = discountedRate;  
    }  
  
    @Override  
    public void setPrice(int price) {  
        super.setPrice(price - (int)(discountedRate * price));  
    }  
}
```
- `Bag`를 사용하는 클라이언트 코드는 `setPrice(10000)`를 호출하면 내부 `price`가 정확히 10000으로 설정된다고 기대한다.
- 하지만 `DiscountedBag`는 `setPrice`의 동작을 변경했다.
- 이는 **부모 클래스의 계약을 자식 클래스가 위반**한 것이며, 클라이언트 코드는 의도치 않은 동작을 겪게 된다.

<br>

### LSP를 지키려면
LSP를 지키기 위해서는 부모 클래스의 동작을 override할 때, 그 의미나 결과가 바뀌면 안된다.  
변경이 필요한 경우에는 **별도의 기능 메서드(`applyDiscount`)를 두거나**, **전략 패턴 등으로 위임 구조를 만들고**, **상속을 지양**하는 것이 더 안전하다.  

```java
// LSP 준수
public class DiscountedBag extends Bag {  
    private double discountedRate = 10;  
  
    public void setDiscountedRate(double discountedRate) {  
        this.discountedRate = discountedRate;  
    }  
  
    public void applyDiscount(int price) {  
        super.setPrice(price - (int)(discountedRate * price));  
    }  
}
```

<br>


