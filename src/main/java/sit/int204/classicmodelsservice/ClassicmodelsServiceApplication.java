package sit.int204.classicmodelsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class ClassicmodelsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassicmodelsServiceApplication.class, args);
    }

}
