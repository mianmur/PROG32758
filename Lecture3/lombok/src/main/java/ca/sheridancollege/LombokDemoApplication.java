package ca.sheridancollege;

import ca.sheridancollege.beans.School;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class LombokDemoApplication {

	public static void main(String[] args) {
		log.info("Starting application", LombokDemoApplication.class);
		ConfigurableApplicationContext context = SpringApplication.run(LombokDemoApplication.class, args);

		School school = (School) context.getBean("school");

		System.out.println(school.getName());
	}

}
