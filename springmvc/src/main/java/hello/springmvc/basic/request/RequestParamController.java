package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
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

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));


        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-required") // url이 /request-param?username= 으로 들어오면 username이 빈문자열("")로 들어옴
    public String requestParamRequired(
            @RequestParam(required = true) String username, // required를 false로 하면 값이 없어도 됨, 기본값은 true
            @RequestParam(required = false) Integer age) { // int는 객체형이기 때문에 null을 넣을 수 없음, 그래서 Integer로 바꿔줘야함

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault( // defaultValue를 사용하면 required와 상관없이 기본값이 들어간다. 빈문자열("")도 처리가능
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        /**
         * @ModelAttribute 실행순서
         * HelloData의 객체를 생성한다
         * 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다
         * 해당 프로퍼티의 setter를 호출해서 파라미터의 값을 입력(바인딩) 한다.
         * 
         * 프로퍼티?
         * 객체에 getAge(), setAge() 메서드가 있으면, 이 객체는 age이라는 프로퍼티를 가지고 있다
         */
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) { // @ModelAttribute는 생략 가능
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /**
     * 스프링 규칙
     * Spring, int, Integer 같은 단순 타입 = @RequestParam
     * 나머지 = @ModelAttribute (argument resolver로 지정해둔 타입 외 = 내가 만든 타입)
     * 
     */
}
