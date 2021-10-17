package com.cloud.function.serverless;

import com.cloud.function.serverless.model.HelloRequest;
import com.cloud.function.serverless.model.HelloResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Locale;
import java.util.function.Function;

@SpringBootApplication
public class ServerlessApplication implements ApplicationContextInitializer<GenericApplicationContext> {

	public static void main(String[] args) {
		SpringApplication.run(ServerlessApplication.class, args);
	}

	@Override
	public void initialize(GenericApplicationContext context) {
		context.registerBean("function", FunctionRegistration.class,
				() -> new FunctionRegistration<>(members())
						.type(FunctionType.from(HelloRequest.class).to(HelloResponse.class).getType()));
	}

	public Function<HelloRequest, HelloResponse> members() {
		return request -> {
			HelloResponse response = new HelloResponse("Hello " + request.getName().toUpperCase(Locale.ROOT));
			return response;
		};
	}
}
