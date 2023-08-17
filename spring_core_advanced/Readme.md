# 스프링 핵심 원리 - 고급편

## 쓰레드 로컬 - ThreadLocal

스프링에서 싱글톤으로 객체를 관리한다.  
따라서, `FieldLogTrace`를 싱글톤으로 관리한다.  
싱글톤 객체를 사용할 때 동시성 이슈를 조심해야한다.  
`http://localhost:8080/v3/request?itemId=hello` 를 1초 이내에 여러번 실행했을 경우,

```
2023-08-16 14:53:05.799  INFO 75512 --- [nio-8080-exec-1] h.advanced.trace.logtrace.FieldLogTrace  : [dafc3e56] OrderController.request()
2023-08-16 14:53:05.801  INFO 75512 --- [nio-8080-exec-1] h.advanced.trace.logtrace.FieldLogTrace  : [dafc3e56] |-->OrderService.orderItem()
2023-08-16 14:53:05.802  INFO 75512 --- [nio-8080-exec-1] h.advanced.trace.logtrace.FieldLogTrace  : [dafc3e56] |   |-->OrderRepository.save()
2023-08-16 14:53:05.835  INFO 75512 --- [nio-8080-exec-2] h.advanced.trace.logtrace.FieldLogTrace  : [dafc3e56] |   |   |-->OrderController.request()
2023-08-16 14:53:05.835  INFO 75512 --- [nio-8080-exec-2] h.advanced.trace.logtrace.FieldLogTrace  : [dafc3e56] |   |   |   |-->OrderService.orderItem()
2023-08-16 14:53:05.835  INFO 75512 --- [nio-8080-exec-2] h.advanced.trace.logtrace.FieldLogTrace  : [dafc3e56] |   |   |   |   |-->OrderRepository.save()
2023-08-16 14:53:06.805  INFO 75512 --- [nio-8080-exec-1] h.advanced.trace.logtrace.FieldLogTrace  : [dafc3e56] |   |<--OrderRepository.save() time=1003ms
2023-08-16 14:53:06.806  INFO 75512 --- [nio-8080-exec-1] h.advanced.trace.logtrace.FieldLogTrace  : [dafc3e56] |<--OrderService.orderItem() time=1005ms
2023-08-16 14:53:06.806  INFO 75512 --- [nio-8080-exec-1] h.advanced.trace.logtrace.FieldLogTrace  : [dafc3e56] OrderController.request() time=1007ms
2023-08-16 14:53:06.838  INFO 75512 --- [nio-8080-exec-2] h.advanced.trace.logtrace.FieldLogTrace  : [dafc3e56] |   |   |   |   |<--OrderRepository.save() time=1003ms
2023-08-16 14:53:06.840  INFO 75512 --- [nio-8080-exec-2] h.advanced.trace.logtrace.FieldLogTrace  : [dafc3e56] |   |   |   |<--OrderService.orderItem() time=1005ms
2023-08-16 14:53:06.840  INFO 75512 --- [nio-8080-exec-2] h.advanced.trace.logtrace.FieldLogTrace  : [dafc3e56] |   |   |<--OrderController.request() time=1005ms
```

위와 같이 예상과 다르게 출력된다.  
`[nio-8080-exec-1]`, `[nio-8080-exec-2]` 스레드 2개가 같은 객체를 사용해서 동시성 이슈가 발생한 것이다.
<br>

`ThreadLocal`을 통해 해결할 수 있다.  
스레드 로컬을 사용하면 각 스레드마다 별도의 내부 저장소를 제공한다.  
따라서, 같은 인스턴스의 스레드 로컬 필드에 접근해도 문제 없다.

```
2023-08-16 23:08:30.166  INFO 82257 --- [nio-8080-exec-3] h.a.trace.logtrace.ThreadLocalLogTrace   : [5d8e0d9d] OrderController.request()
2023-08-16 23:08:30.167  INFO 82257 --- [nio-8080-exec-3] h.a.trace.logtrace.ThreadLocalLogTrace   : [5d8e0d9d] |-->OrderService.orderItem()
2023-08-16 23:08:30.167  INFO 82257 --- [nio-8080-exec-3] h.a.trace.logtrace.ThreadLocalLogTrace   : [5d8e0d9d] |   |-->OrderRepository.save()
2023-08-16 23:08:30.365  INFO 82257 --- [nio-8080-exec-4] h.a.trace.logtrace.ThreadLocalLogTrace   : [0ec5e389] OrderController.request()
2023-08-16 23:08:30.366  INFO 82257 --- [nio-8080-exec-4] h.a.trace.logtrace.ThreadLocalLogTrace   : [0ec5e389] |-->OrderService.orderItem()
2023-08-16 23:08:30.366  INFO 82257 --- [nio-8080-exec-4] h.a.trace.logtrace.ThreadLocalLogTrace   : [0ec5e389] |   |-->OrderRepository.save()
2023-08-16 23:08:31.173  INFO 82257 --- [nio-8080-exec-3] h.a.trace.logtrace.ThreadLocalLogTrace   : [5d8e0d9d] |   |<--OrderRepository.save() time=1005ms
2023-08-16 23:08:31.174  INFO 82257 --- [nio-8080-exec-3] h.a.trace.logtrace.ThreadLocalLogTrace   : [5d8e0d9d] |<--OrderService.orderItem() time=1007ms
2023-08-16 23:08:31.175  INFO 82257 --- [nio-8080-exec-3] h.a.trace.logtrace.ThreadLocalLogTrace   : [5d8e0d9d] OrderController.request() time=1009ms
2023-08-16 23:08:31.369  INFO 82257 --- [nio-8080-exec-4] h.a.trace.logtrace.ThreadLocalLogTrace   : [0ec5e389] |   |<--OrderRepository.save() time=1003ms
2023-08-16 23:08:31.370  INFO 82257 --- [nio-8080-exec-4] h.a.trace.logtrace.ThreadLocalLogTrace   : [0ec5e389] |<--OrderService.orderItem() time=1004ms
2023-08-16 23:08:31.371  INFO 82257 --- [nio-8080-exec-4] h.a.trace.logtrace.ThreadLocalLogTrace   : [0ec5e389] OrderController.request() time=1006ms
```

<br>

`ThreadLocal`의 값을 사용한 후 제거하지 않으면 WAS처럼 스레드 풀을 사용하는 경우에 심각한 문제가 발생할 수 있다.  
스레드 풀에서는 스레드를 재사용하기 때문에 이전에 사용한 데이터가 살아있게 되고, 다른 사용자가 해당 데이터를 사용할 수 있다.  
따라서, `ThreadLocal.remove()`로 스레드 로컬를 제거해야 한다.  
<br>

## 템플릿 메서드 패턴과 콜백 패턴

### 템플릿 메서드 패턴

요구사항에 따라 로그 추적기를 구현했지만, 핵심 기능보다 부가 기능(로그를 출력하는 코드)이 더 길어졌다.  
`Controller`, `Service`, `Repository`를 보면 로그 추적기를 사용하는 구조는 모두 동일하다.

```
  TraceStatus status = null;
  try {
      status = trace.begin("message");

      //핵심 기능 호출

      trace.end(status);
  } catch (Exception e) {
      trace.exception(status, e);
      throw e;
  }
```

<br>

`템플릿 메서드 패턴`을 사용하면 핵심 기능(변하는) 부분과 부가 기능(변하지 않는, 로그 추적) 부분을 분리하여 개발할 수 있다.

<p align="center">
  <img width="599" alt="image" src="https://github.com/0takkk/inflearn/assets/89503136/57ed3a91-0391-4353-91be-578068d09bb0">
</p>

변하지 않는 부분을 `execute()`에 두고, 변하는 부분을 `call()` 추상 메서드로 만든다.  
`템플릿 메서드 패턴`은 부모 클래스에 변하지 않는 템플릿 코드를 두고, 변하는 부분은 자식 클래스에 두고 상속과 오버라이딩을 통해 처리한다.
<br>

`템플릿 메서드 패턴`은 `상속`을 사용한다.  
자식 클래스는 부모 클래스의 기능을 사용하지 않지만 모든 기능을 상속받고 의존하고 있다.  
이런 잘못된 의존관계로 인해 부모 클래스가 수정되면, 자식 클래스도 영향을 받는다.  
또한, 상속 구조로 인해 별도의 클래스나 익명 내부 클래스를 만들어야 한다.  
이러한 단점을 해결한 디자인 패턴이 `전략 패턴`이다.
