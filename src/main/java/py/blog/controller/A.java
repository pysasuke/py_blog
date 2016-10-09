package py.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2016/10/7 0007.
 */
@Controller
@RequestMapping("/blog")
public class A {
    @RequestMapping(value="/test",method = RequestMethod.GET)
    public  void test(Object o){
        int i =1;
        System.out.println(1);
    }
}
