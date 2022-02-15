# Spring_inflearn_introduction
인프런_김영한강사님_스프링입문

## 일반적인 웹 애플리케이션 계층 구조

- controller -> service -> repository -> DB
- c,s,r -> domain
    - controller : 웹 MVC의 컨트롤러 역할
    - service : 핵심 비즈니스 로직 구현
    - repository : 데이터 베이스에 접근, 도메인 객체를 DB에 저장하고 관리
    - domain : 비즈니스 도메인 객체
        - 회원, 주문 등등 데이터베이스에 저장하고 관리하는 역할

## 클래스 의존관계 

`MemberService` ㅡ> `MemberRepository`   <----- `MemoryMemberRepository`


## 회원 리포지토리 테스트케이스 작성

main이나 controller에서 실행하는건 시간이 많이 걸려서 사용.


- `src/test/java` 하위폴더
- JUnit 프레임워크 사용
- `@AfterEach` : 한번에 여러 테스트를 실행하면 DB에 직전 테스트 결과를 안주려고 종료될 때마다 메모리 DB에 저장된 데이터를 삭제.
- 테스트 순서에 의존관계가 있으면 좋은 테스트가 아님

## 회원 서비스 테스트

- `@BeforeEach`
  - 각 테스트 실행 전 호출 
  - 테스트 서로간 영향 없도록 항상 새로운 객체를 생성
  - 의존관계 맺어줌

## 스프링 빈과 의존관계

1. 컴포넌트 스캔과 자동 의존관계 설정
2. 자바 코드로 직접 스프링 빈 등록하기

### 1. 컴포넌트 스캔과 자동 의존관계 설정

`MemberController.java` 클래스의 생성자에 `@Autowired`가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌

이런식으로 객체 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection) 의존성 주입이라고 한다.


#### 컴포넌트 스캔 원리

- `@Component` : 스프링 빈으로 자동 등록
- `@Controller` : 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문.
- `@Component`를 포함하는 아래의 애노테이션도 스프링 빈으로 등록된다.
    - `@Service`
    - `@Controller`
    - `@Repository`