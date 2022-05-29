package com.example.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("hello basic");
        return "ok";
    }

    // 1. 단순 get mapping
    @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        log.info("get v2");
        return "ok";
    }

    // 2. path Variable 설정
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String userId) { // 변수명과 path variable 변수명이 같으면 생략 가능
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    // 3. 다중 매핑
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mapping path userId={}, orderId= {}", userId, orderId);

        return "ok";
    }

    // 4. 파라미터로 추가매핑. param 내용이 요청에 있어야 호출됨. 사용 많이 안함
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mapping param");
        return "ok";
    }

     //5. 특정 헤더 조건 매핑@GetMapping(value = "/mapping-header", headers = "mode=debug")
      public String mappingHeader() {
          log.info("mappingHeader");
          return "ok";
      }

      // 6. 미디어 조건 매핑 - consume 사용. 요청타입을 소비한다. content-type
     @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
      public String mappingConsumes() {
          log.info("mappingConsumes");
          return "ok";
      }

      // 7.accept 헤더 필요. "text/html" 을 생산. 컨텐츠 타입이 accept = "text/html"인 애만 수용할 수 있음
      @PostMapping(value = "/mapping-produce", produces = "text/html")
      public String mappingProduces() {
          log.info("mappingProduces");
          return "ok";
      }

}
