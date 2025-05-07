## OSIV
`OSIV(Open Session In View)`는 스프링 프레임워크에서 사용하는 세션 관리 전략 중 하나로,  
여기서 세션은 하이버네이트의 세션, 즉 `영속성 컨텍스트`를 의미한다.

<br>

### OSIV ON
![](https://i.imgur.com/5cbNizH.png)
``` yaml
spring.jpa.open-in-view: true # 기본값
```
스프링 부트에서 OSIV의 기본값은 True이다.
즉, View까지 영속성 컨텍스트를 열어둔다는 의미이다.

`트랜잭션 시작`처럼 최초 데이터베이스 커넥션 시점(Service 계층)부터 API 응답이 끝나는 시점(Controller 계층)까지 `영속성 컨텍스트와 데이터베이스 커넥션을 유지`한다.
따라서, View Template이나 지연로딩이 가능하다.

하지만, 이 전략은 `너무 오랫동안 데이터베이스 커넥션 리소스를 사용`하기 때문에, 실시간 트래픽이 중요한 애플리케이션에서는 커넥션이 부족할 수 있고, 장애로 이어질 수 있다.

<br>

### OSIV OFF
![](https://i.imgur.com/OXeSm8L.png)
``` yaml
spring.jpa.open-in-view: false
```
OSIV를 끄면 트랜잭션을 종료할 때 영속성 컨텍스트를 닫고, 데이터베이스 커넥션도 반환한다.  

따라서, 커넥션 리소스를 낭비하지 않는다.  

하지만, 지연 로딩을 트랜잭션 안에서 처리해야 한다.
