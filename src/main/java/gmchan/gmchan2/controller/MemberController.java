package gmchan.gmchan2.controller;

import gmchan.gmchan2.domain.Member;
import gmchan.gmchan2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //url 입력 할 때
    @GetMapping(value = "/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //데이터를 form에 넣어서 보내고 받을 때 postmapping을 사용
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member); //join 하면 memberService를 타고 들어감

        return "redirect:/";  //완료되면 홈화면으로
    }

    @GetMapping("members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); //멤버에 리스트 자체를 모델에 담아셔 화면에 넘김
        return "members/memberList";

    }

}
