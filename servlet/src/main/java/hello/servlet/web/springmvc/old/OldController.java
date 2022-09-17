package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @Conponent("빈이름=url") 을 통해 빈 등록을 하고 매핑을 한다
@Component("/springmvc/old-controller")
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
        /**
         * 위의 방식 대신에 
         * return new ModelAndView("/WEB-INF/views/new-form.jsp")
         * 이렇게 전체 경로를 적어서 반환해도 동작하기는 한다.
         */
    }
}

/**
 * HandlerMapping
 * 0 = RequestMappingHandlerMapping : 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
 * 1 = BeanNameUrlHandlerMapping : 스프링 빈의 이름으로 핸들러를 찾는다.
 *
 * HandlerAdapter
 * 0 = RequestMappingHandlerAdapter : 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
 * 1 = HttpRequestHandlerAdapter : HttpRequestHandler 처리
 * 2 = SimpleControllerHandlerAdapter : Controller 인터페이스(애노테이션X, 과거에 사용) 처리
 * 
 * OldController
 * HandlerMapping = BeanNameUrlHandlerMapping
 * HandlerAdapter = SimpleControllerHandlerAdapter
 * 
 */
