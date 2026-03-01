package util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(util.CountryCurrencyProperties.class)
public class HackerrankPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(HackerrankPracticeApplication.class, args);
    }
}

