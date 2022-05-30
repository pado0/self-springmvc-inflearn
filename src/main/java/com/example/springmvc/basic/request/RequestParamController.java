package com.example.springmvc.basic.request;

import com.example.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    // http servlet이 제공하는 get request를 사용
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {} , age ={}", username, age);

        response.getWriter().write("ok");
    }

    // 스프링이 제공하는 requestparam 이용
    @ResponseBody // 리턴값을 바디에 넣고 싶을 때. 응답 메세지로 반환시켜버림. @RestController와 동일동작
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("username = {} , age ={}", memberName, memberAge);
        return "ok";
    }

    // 생략1
    @ResponseBody // 리턴값을 바디에 넣고 싶을 때. 응답 메세지로 반환시켜버림. @RestController와 동일동작
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username = {} , age ={}", username, age);
        return "ok";
    }

    // 생략2
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) { // primitive 타입이면 @RequestParam 생략 가능. 다만 적는게 유지보수상.. 좋을듯
        log.info("username = {} , age ={}", username, age);
        return "ok";
    }

    // 필수파라미터
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, // 필수 파라미터. null이 아닌 빈문자가 들어오면 통과
            @RequestParam(required = false) Integer age // 없어도 되는 파라미단 Null이 들어와 exception. Integer 타입으로 바꿔야함
    ) {
        log.info("username = {} , age ={}", username, age);
        return "ok";
    }

    // 디폴트 벨류: 들어가면 required가 필요 없음. 값이 있던 없던 디폴트값이 세팅됨. 빈문자도 디폴트값으로.
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username, // 필수 파라미터. null이 아닌 빈문자가 들어오면 통과
            @RequestParam(required = false, defaultValue = "-1") Integer age // 없어도 되는 파라미단 Null이 들어와 exception. Integer 타입으로 바꿔야함
    ) {
        log.info("username = {} , age ={}", username, age);
        return "ok";
    }

    // 모든 파라미터 받고싶을 때 Map. 파라미터 값이 여러개일 수 있으면 MultiValueMap 사용 (보통 하나 ㅎㅎ)
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username = {} , age ={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    // v1
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam Integer age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);
        log.info("username = {} , age ={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData); // toString 자동처리
        return "ok";
    }

    // v2 필드 대신 객체를 세팅
    // 1. helloData 객체 생성
    // 2. 요청 파라미터 이름으로 객체의 프로퍼티(getter, setter)를 찾음
    // 3. 세터 호출하여 파라미터 값을 세팅(바인딩) 해준다.
    // -> 파라미터 명이 usernamedlaus setUsername() 메서드를 찾아 호출하여 객체에 값 입력
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username = {} , age ={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData); // toString 자동처리
        return "ok";
    }
}
