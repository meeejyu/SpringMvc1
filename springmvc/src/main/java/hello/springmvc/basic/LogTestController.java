package hello.springmvc.basic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j  
@RestController // 이걸 사용해서 String를 반환하면 문자 그대로 반환됨
public class LogTestController {
    
    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        // log 레벨 설정
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}

/**
 * log.debug("data="+data)
 * 연산이 일어난 후 로그를 출력하지 않는다.
 * 
 * log.debug("data={}", data)
 * 로그 출력 레벨을 info로 설정하면 아무일도 발생하지 않는다.
 * 첫번째 처럼 로그를 찍으면 연산이 발생하기 때문에 두번째처럼 써야한다.
 * 
 * 로그 사용시 장점
 * 
 * 
 * 상황에 맞게 로그를 조절할수 있다.
 * 로그를 파일로 남길 수 있다.
 * 성능이 System.out보다 좋다
 * 쓰레드 정보, 클래스 이름 같은 부가 정보를 함게 볼 수 있고, 출력 모양을 조정할 수 있다.
 * 
 */
