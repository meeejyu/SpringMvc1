package hello.springmvc.basic;

import lombok.Data;

@Data // toString도 포함
public class HelloData {
    private String username;
    private int age;
}
