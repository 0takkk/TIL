package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.w3c.dom.html.HTMLHeadElement;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("ads");

        System.out.println("helloLombok = " + helloLombok);
    }
}
