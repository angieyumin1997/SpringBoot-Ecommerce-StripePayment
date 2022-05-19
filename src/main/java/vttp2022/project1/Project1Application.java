package vttp2022.project1;

import javax.annotation.PostConstruct;

import com.stripe.Stripe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Project1Application {

	@PostConstruct
	public void setup(){
		Stripe.apiKey = "sk_test_51L0iLFGzWeQzLKycRS8XGb4NYoyr8UgcEOnobrdpktcSRAT65FK5qNOByaw1737AAHrQZ2bhQPAfSuEhVPsaWAIb00VKeK9iAn";
	}

	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
  }
	
}
