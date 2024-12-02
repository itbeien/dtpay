package cn.itbeien.task.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TestController {

    @RequestMapping("/users")
    public String test(@RequestBody String data){
        return "success";
    }
}
