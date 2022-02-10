package gmchan.gmchan2;

import gmchan.gmchan2.repository.MemberRepository;
import gmchan.gmchan2.repository.MemoryMemberRepository;
import gmchan.gmchan2.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
