package gmchan.gmchan2;

import gmchan.gmchan2.repository.JdbcMemberRepository;
import gmchan.gmchan2.repository.JdbcTemplateMemberRepository;
import gmchan.gmchan2.repository.MemberRepository;
import gmchan.gmchan2.repository.MemoryMemberRepository;
import gmchan.gmchan2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository(); //jdbc X 순수 데이터
        return new JdbcMemberRepository(dataSource); //jdbc 이용
//        return new JdbcTemplateMemberRepository(dataSource); //jdbc 템플릿 사용
    }
}
