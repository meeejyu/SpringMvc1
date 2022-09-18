package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello";
    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
    /**
     * Void를 반환하는 경우
     * @Controller를 사용하고, HttpServletResponse, OutputStream(Writer) 같은 HTTP 메시지
     * 바디를 처리하는 파라미터가 없으면 요청 URL을 참고해서 논리 뷰 이름으로 사용
     * 요청 URL : /response/hello
     * 실행 : templates/response/hello.html
     * 
     * 권장하지 않는 방식
     * 
     */
        model.addAttribute("data", "hello!");
    }

    /**
     * 뷰 템플릿 경로
     * src/main/resource/templates
     * 
     * Thymeleaf 스프링 부트 설정을 하면 application.properties에 기본적으로 아래와 같이 등록된 효과가 있다.
     * spring.thymeleaf.prefix=classpath:/templates/
     * spring.thymeleaf.suffix=.html
     * 
     */

}
