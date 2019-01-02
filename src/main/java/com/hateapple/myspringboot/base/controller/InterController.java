package com.hateapple.myspringboot.base.controller;

import com.hateapple.myspringboot.base.aspect.CrateLog;
import com.hateapple.myspringboot.base.domain.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/inter")
public class InterController {

    @RequestMapping("/test")
    @ResponseBody
    @CrateLog
    public Student test(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws IOException {
         Student student = new Student();
         student.setName((String)request.getAttribute("name"));
         return student;
    }
}