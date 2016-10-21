package py.blog.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import py.blog.blog.entity.MailMessage;
import py.blog.util.Mail;


/**
 * Created by Administrator on 2016/10/7 0007.
 */
@Controller
@RequestMapping("/blog")
public class A {
    @RequestMapping(value = "/test.do", method = RequestMethod.POST)
    public String test(@ModelAttribute("formParam") MailMessage formParam) throws Exception {
//    public String test(MailMessage formParam) throws Exception {
//    public String test() throws Exception {
        int i = 1;
        System.out.println(1);
        Mail.send("smtp.163.com", "18720978420@163.com", "404158848@qq.com",
                "这是一条测试信息",  "我想和你聊聊天", "18720978420@163.com", "pengyong940130");
//        Mail.send("smtp.163.com", "18720978420@163.com", a.getEmail(),
//                a.getSubject(),  a.getMessage(), "18720978420@163.com", "pengyong940130");
        return "a";
    }

}
