package gmchan.gmchan2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "gmchan!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name" , name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http body에 리턴 내용을 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //"hello spring"
    }

    //json방식으로 나타남(key : value)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    /* `@ResponseBody`를 사용하면
    *  `viewResolver` 대신에 `HttpMessageConverter`가 동작한다.
    * 기본 문자처리는 `StringHttpMessageConverter`를 사용
    * 기본 객체 처리는 'MappingJackson2HttpMessageConverter' 를 사용
    * byte처리나 기타 등등 여러 'HttpMessageConverter' 가 등록이 되어있다.
    * */

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
