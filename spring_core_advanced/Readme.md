# 스프링 핵심 원리 - 고급편

### 쓰레드 로컬 - ThreadLocal

스프링에서 싱글톤으로 객체를 관리함.  
따라서, `FieldLogTrace`를 싱글톤으로 관리함.  
싱글톤 객체를 사용할 때 동시성 이슈를 조심해야함.  
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

위와 같이 예상과 다르게 출력됨.  
`[nio-8080-exec-1]`, `[nio-8080-exec-2]` 스레드는 2개가 같은 객체를 사용해서 동시성 이슈가 발생한 것.
