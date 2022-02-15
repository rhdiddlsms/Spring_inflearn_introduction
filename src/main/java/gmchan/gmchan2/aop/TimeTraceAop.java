package gmchan.gmchan2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/*
* AOP: Aspect Oriented Programming
* 회원가입, 회원조회 등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리.
* 시간을 측정하는 로직을 별도의 공통 로직으로 생성
* 핵심관심 사항을 깔끔하게 유지 가능
* 변경이 필요하면 여기있는 로직만 변경
* 원하는 적용 대상을 선택 할 수 있다.
* */
@Aspect   // aop 사용하기 위해서 적어주기
@Component
public class TimeTraceAop {

    // 패키지명1.패키지명2.패키지3..*(메서드))
    @Around("execution(* gmchan.gmchan2..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());

        try{
            //다음메서드로 진행
            return joinPoint.proceed(); //inline Variable로 합치기 (Ctrl + Alt + Shift + T -> inline)

        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
