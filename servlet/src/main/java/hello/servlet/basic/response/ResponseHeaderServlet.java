package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * http://localhost:8080/response-header
 *
 */
@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    // [status-line]
    response.setStatus(HttpServletResponse.SC_OK); //200

    // [response-headers]
    response.setHeader("Content-Type", "text/plain;charset=utf-8");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 캐쉬 무효화
    response.setHeader("Pragma", "no-cache"); // 캐쉬 무효화
    response.setHeader("my-header","hello"); // 임의의 헤더를 만들수 있음, 내가 해더의 내용 추가하는 부분

    // [Header 편의 메서드] 
    content(response); 
    cookie(response); 
    redirect(response);

    // [message body]
    PrintWriter writer = response.getWriter();
    writer.println("ok");
    }

    private void content(HttpServletResponse response) {
        
        // Content-Type: text/plain;charset=utf-8
        // Content-Length: 2

        // response.setHeader("Content-Type", "text/plain;charset=utf-8"); 아래의 2줄과 동일한 내용 
        response.setContentType("text/plain"); 
        response.setCharacterEncoding("utf-8"); 

        // response.setContentLength(2); // 생략시 자동 생성
    }

    private void cookie(HttpServletResponse response) {
       
        // Set-Cookie: myCookie=good; Max-Age=600; 
        // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600"); 
        Cookie cookie = new Cookie("myCookie", "good"); 
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        
        //Status Code 302
        //Location: /basic/hello-form.html
        
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");

        // 위의 두줄과 동일한 내용, 위의 두줄 대신 아래의 코드를 입력해도 됨.
        response.sendRedirect("/basic/hello-form.html");
    }
         
}