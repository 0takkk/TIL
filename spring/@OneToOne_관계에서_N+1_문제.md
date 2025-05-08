### @OneToOne
```java
@Entity  
@Getter  
@NoArgsConstructor(access = AccessLevel.PROTECTED)  
public class Member {  
  
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "member_id")  
    private Long id;  
  
    private String name;  
  
    private int age;  
  
    @OneToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "team_id")  
    private Team team;  
  
    public Member(String name, int age, Team team) {  
        this.name = name;  
        this.age = age;  
        this.team = team;  
    }  
}
```

```java
@Entity  
@Getter  
@NoArgsConstructor(access = AccessLevel.PUBLIC)  
public class Team {  
  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "team_id")  
    private Long id;  
  
    @OneToOne(mappedBy = "team", fetch = FetchType.LAZY)  
    private Member member;  
  
}
```

```java
@Slf4j  
@SpringBootTest  
@Transactional  
public class NPlus1Test {  
  
    @Autowired  
    MemberRepository memberRepository;  
  
    @Autowired  
    TeamRepository teamRepository;  
  
    @Autowired  
    EntityManager em;  
  
    @BeforeEach  
    public void before() {  
        Team team1 = new Team();  
        Team team2 = new Team();  
        Team team3 = new Team();  
  
        teamRepository.save(team1);  
        teamRepository.save(team2);  
        teamRepository.save(team3);  
  
        Member member1 = new Member("SEO", 28, team1);  
        Member member2 = new Member("LEE", 30, team2);  
        Member member3 = new Member("KIM", 28, team3);  
  
        memberRepository.save(member1);  
        memberRepository.save(member2);  
        memberRepository.save(member3);  
  
        em.flush();  
        em.clear();  
    }  
  
    @Test  
    public void findMembers() {  
        memberRepository.findAll();  
    }  
  
    @Test  
    public void findTeams() {  
        teamRepository.findAll();  
    }  
}
```

`findMembers()`은 N+1 문제가 발생하지 않음
```
Hibernate: 
    select
        m1_0.member_id,
        m1_0.age,
        m1_0.name,
        m1_0.team_id 
    from
        member m1_0
```

`findTeams()`는 N+1 문제 발생!!
```
Hibernate: 
    select
        t1_0.team_id 
    from
        team t1_0
Hibernate: 
    select
        m1_0.member_id,
        m1_0.age,
        m1_0.name,
        m1_0.team_id 
    from
        member m1_0 
    where
        m1_0.team_id=?
Hibernate: 
    select
        m1_0.member_id,
        m1_0.age,
        m1_0.name,
        m1_0.team_id 
    from
        member m1_0 
    where
        m1_0.team_id=?
Hibernate: 
    select
        m1_0.member_id,
        m1_0.age,
        m1_0.name,
        m1_0.team_id 
    from
        member m1_0 
    where
        m1_0.team_id=?
```

`findTeams()`에서 `지연 로딩`으로 설정하고, `member` 엔티티를 사용하지 않았음에도 즉시 로딩하여 **N+1 문제가 발생**한다.

현재 `Team` 엔티티는 **양방향 관계**를 지니고 있으며, `mappedBy` 속성을 가진 **연관 관계의 주인이 아니다.**  
`Member` 엔티티가 `Team`의 id를 외래키로 가지고 있는 **연관 관계의 주인**이다.

`Lazy 로딩`은 프록시 객체를 만들어서, 필요 시점에 쿼리를 날린다.  
`Member`는 연관 관계의 주인으로 외래키를 가지고 있기 때문에, null인지 아닌지 알 수 있어 프록시를 넣어 줄 수 있다.  
하지만, `Team`은 외래키를 가지고 있지 않기 때문에, 프록시로 만들 객체가 null인지 아닌지 알 수 없다.  

따라서, **Hibernate는 이 경우 프록시 객체를 통한 지연 로딩을 지원하지 않고, 즉시 로딩하여 N+1 문제가 발생**한다.  

<br>

### @OneToMany
```java
@Entity  
@Getter  
@NoArgsConstructor(access = AccessLevel.PUBLIC)  
public class Team {  
  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "team_id")  
    private Long id;  
  
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)  
    private List<Member> members = new ArrayList<>();  
  
}


@Test  
public void findTeams() {  
    teamRepository.findAll();  
}

```

```
Hibernate: 
    select
        t1_0.team_id 
    from
        team t1_0
```

`@OneToMany` 에서는 연관 관계 주인이 아니더라도 N+1 문제가 발생하지 않는다.  
이는 빈 컬렉션이 초기화될 때 `new ArrayList<>()`로 프록시가 생기기 때문이다.  
`members`는 null이 아니고 size가 0이기 때문에 `@OneToMany` 관계는 `지연 로딩`이 가능하다.  

<br>

### 해결 방법
1. Fetch Join 사용
   - 연관 관계 주인(`Member`)에서 fetch join으로 연관 객체(`Team`)를 한 번에 조회
     ``` java
     @Query("SELECT m FROM Member m JOIN FETCH m.team")
      List<Member> findAllWithTeam();
     ```
2. 양방향 관계를 제거한다.
   - `Team.member`를 제거하여 단반향 관계로 설계 -> 필요할 때만 `Member`에서 접근
3. `@OneToOne` 관계를 `@OneToMany` 등 다른 관계로 변환한다.
   - 비즈니스 요구에 따라 실제로 1:N 가능성이 있다면 `@OneToMany`로 전환 고려



<br>

### 정리
`@OneToOne` `양방향 관계`에서 `연관 관계 주인이 아닌 엔티티`를 조회하게 되면 지연 로딩을 하더라도 프록시 객체를 만들 수 없어 즉시 로딩을 수행한다.


---

참고자료 
- https://devlog-wjdrbs96.tistory.com/432  
- https://pooney.tistory.com/95
