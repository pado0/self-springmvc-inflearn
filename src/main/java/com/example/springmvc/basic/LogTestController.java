package com.example.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        log.info("info log = {}", name);
        log.debug("debug log = {}", name);
        log.warn("warn log = {}", name);
        log.error("error log = {}", name);
        return "ok"; // RestController에서는 스트링이 바로 바디에 리턴됨.
    }
}
