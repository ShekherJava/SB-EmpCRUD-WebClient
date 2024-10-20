package com.example.demo.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.model.Employee;

import reactor.core.publisher.Mono;

@Component
public class MyApplicationRunner implements ApplicationRunner {
	
	private static final String GET_EMP_BY_ID_URL = "http://localhost:8080/api/{id}";
	private static final String GET_EMPLOYEES_URL = "http://localhost:8080/api/employees";
	private static final String POST_EMP_URL = "http://localhost:8080/api/create";
	private static final String PUT_EMP_URL = "http://localhost:8080/api/{id}";

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		WebClient webClient = WebClient.create();
		
		/*
		Mono<Employee> empMono = webClient.get()
		.uri(GET_EMP_BY_ID_URL, 7201)
		.retrieve()
		.bodyToMono(Employee.class);
		*/
		
		/*
		 * subscribe() method triggers the WebClient to make a HTTP call to the
		 * given url and react once the data is available.
		 */
		
		//empMono.subscribe( emp -> System.out.println(emp));
		//empMono.subscribe(System.out::println);
		
		/*
		Flux<Employee> empFlux = webClient.get()
										  .uri(GET_EMPLOYEES_URL)
										  .retrieve()
										  .bodyToFlux(Employee.class);
		empFlux.collectList().subscribe( employees -> {
			employees.forEach(System.out::println);
		});
		
		*/
		
		Employee updatedEmployee = new Employee(8989, "Tom", 6599.0, 20);
		
		Mono<ResponseEntity<Employee>> mono = webClient.put()
														.uri(PUT_EMP_URL, 8989)
														.bodyValue(updatedEmployee)
														.retrieve()
														.toEntity(Employee.class);
		mono.subscribe( re -> {
			System.out.println("status code : " + re.getStatusCode().value());
			System.out.println("response body : " + re.getBody());
		});
		
		

	}

}
