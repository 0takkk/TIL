# 스프링 DB 2편 - 데이터 접근 활용 기술

## JPA

`EntityManager`는 순수한 JPA 기술이고, 예외가 발생하면 `PersistenceException`을 발생시킨다.  
`@Repository`는 JPA 예외를 스프링 예외로 변환해주는 기능을 가지고 있다.

<img width="598" alt="image" src="https://github.com/0takkk/inflearn/assets/89503136/bc924d8b-0764-47bd-9fb8-da09f0b9489d">

**예외 변환 후**  
<img width="590" alt="image" src="https://github.com/0takkk/inflearn/assets/89503136/059decbd-b2ce-4ecc-92cd-4f5bfd96ca62">

<br>

## 스프링 트랜잭션 전파

트랜잭션이 둘 이상 있을 때 어떻게 동작하는지, 스프링이 제공하는 `트랜잭션 전파(propagation)`에 대해 알아본다.

<br>

<img width="592" alt="image" src="https://github.com/0takkk/inflearn/assets/89503136/a5424ae0-6ee9-4b4d-bd1f-cad927a54d0f">

<img width="597" alt="image" src="https://github.com/0takkk/inflearn/assets/89503136/b3be1b97-90e9-4c66-af64-bb7a0de0421a">

스프링은 이런 경우 외부 트랜잭션과 내부 트랜잭션을 묶어서 하나의 트랜잭션으로 만든다.  
( 이것은 기본 동작(`REQUIRED`)이고, 옵션을 통해 다른 동작방식도 선택 가능 )

<img width="592" alt="image" src="https://github.com/0takkk/inflearn/assets/89503136/45fb4a0a-b752-4396-be95-9a711ca908d1">

이를 논리 트랜잭션과 물리 트랜잭션이라는 개념으로 나눈다.  
기본 동작 방식은, **모든 논리 트랜잭션이 커밋되어야 물리 트랜잭션이 커밋된다.**  
또한, **하나의 논리 트랜잭션이라도 롤백되면 물리 트랜잭션을 롤백된다.**

<img width="594" alt="image" src="https://github.com/0takkk/inflearn/assets/89503136/c5828ad7-42c8-4e81-bcb4-9c68d42f21ba">

따라서, 내부 트랜잭션이 롤백되고 외부 트랜잭션이 커밋되더라도, 물리 트랜잭션은 롤백된다.

<br>

`REQUIRES_NEW` 옵션을 사용하면 내부 트랜잭션이 롤백되어도 외부 트랜잭션은 커밋할 수 있다.  
<img width="595" alt="image" src="https://github.com/0takkk/inflearn/assets/89503136/2af4fd1e-9d9c-4863-a2c9-392c757baf18">

`REQUIRES_NEW` 옵션을 사용하면 외부 트랜잭션과 내부 트랜잭션이 각각 별도의 물리 트랜잭션을 가진다.  
즉, 별도의 DB 커넥션을 따로 사용한다는 뜻이다.

<br>

## 스프링 트랜잭션 전파 - 복구

<img width="595" alt="image" src="https://github.com/0takkk/inflearn/assets/89503136/d2e3c126-8180-4eab-ad26-576ae53cf06d">

위와 같이 내부 트랜잭션에서 예외가 발생했지만, 예외를 잡아서 정상 처리되더라도 트랜잭션A는 롤백된다.

<img width="596" alt="image" src="https://github.com/0takkk/inflearn/assets/89503136/9b0c868b-afbb-42f6-80c5-9cd7342e21a0">

이는 내부 트랜잭션에서 `rollbackOnly`를 설정하기 때문에 정상 흐름 처리되더라도 롤백된다.  
그리고 `UnexpectedRollbackException`이 던져진다.

<br>

이 경우, 내부 트랜잭션이 롤백되더라도 물리 트랜잭션을 커밋하고 싶다면, `REQUIRES_NEW` 옵션을 사용하면 된다.  
<img width="596" alt="image" src="https://github.com/0takkk/inflearn/assets/89503136/466aceea-efc8-4d87-9b79-fdbcf6898be0">
