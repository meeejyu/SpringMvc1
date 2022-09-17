package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // 서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

	/**
	 * 뷰 리졸러
	 * 
	 * propertiese의 정보를 가져와서 자동으로 등록해준다.
	 * 
	 * @Bean
	 * ViewResolver internalResourceViewResolver() {
	 * 		return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
	 * }
	 */

}
