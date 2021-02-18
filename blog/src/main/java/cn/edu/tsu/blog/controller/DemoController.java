package cn.edu.tsu.blog.controller;
import java.util.Date;

import cn.edu.tsu.blog.model.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController

public class DemoController {

    @GetMapping("demo/echo/{msg}")
    public String echo(@PathVariable String msg) {
        return "Hello " + msg;
    }

    @GetMapping("test/demo")
    public String info(String msg) {
        return "Hello " + msg;
    }

    @RequestMapping("test/info")
    public String getInfo() {
        String currentUser = "";

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            currentUser = ((UserDetails) principal).getUsername();
        } else {
            currentUser = principal.toString();
        }

        return currentUser;

    }

}
