package gmchan.gmchan2;

import gmchan.gmchan2.aop.TimeTraceAop;
import gmchan.gmchan2.repository.*;
import gmchan.gmchan2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

/*    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }*/

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository(); //jdbc X 순수 데이터
//        return new JdbcMemberRepository(dataSource); //jdbc 이용
//        return new JdbcTemplateMemberRepository(dataSource); //jdbc 템플릿 사용
        return new JpaMemberRepositoty(em);
    }
/*
    //component scan 안썻을 때 써줘야됨
    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
*/
}
