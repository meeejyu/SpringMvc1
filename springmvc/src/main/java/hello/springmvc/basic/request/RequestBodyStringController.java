package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {


    // HTTP 메세지 바디를 통해 데이터가 직접 넘어오는 경우는 @RequestParam, @ModelAttribute를 사용할 수 없다.
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    // request를 InputStream으로 바로 받을 수도 있다, Writer도 가능
    /**
     * InputStream(Reader) : HTTP 요청 메시지 바디의 내용을 직접 조회
     * OutputStream(Writer) : HTTP 응답 메시지의 바디에 직접 결과 출력
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        /**
         * HttpEntity<String> httpEntity
         * : HTTP header, body 정보를 편리하게 조회
         *      - 메시지 바디 정보를 직접 조회
         *      - 요청 파라미터를 조회하는 기능과 관계없음 @RequestParam, @ModelAttribue 안됨
         * : 응답에도 사용 가능
         *      - 메시지 바디 정보 직접 반환
         *      - 헤더 정보 포함 가능
         *      - view 조회 X
         *  RequestEntity, ResponseEntity 도 같은 기능 제공
         * 
         */
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {

        /**
         * @RequestBody
         * HTTP 메시지 바디 정보를 편리하게 조회할 수 있다
         * 헤더 정보가 필요하면 @RequestHeader를 사용하면 된다
         * 
         */
        log.info("messageBody={}", messageBody);
        return "ok";
    }

    /**
     * 정리
     * 
     * 요청 파라미터 vs HTTP 메시지 바디
     *      요청 파라미터를 조회하는 기능 : @Requestparam, @ModelAttribute
     *      HTTP 메시지 바디를 직접 조회하는 기능 ; @RequestBody
     * 
     * @ResponseBody
     *      응답 결과를 HTTP 메시지 바디에 직접 담아서 전달할 수 있다.
     *      물론 이 경우에도 view를 사용하지 않는다.
     * 
     */
}
