package cn.tuyucheng.taketoday.reactor.core;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class FluxVsMonoUnitTest {

	@Test
	void givenMonoPublisher_whenSubscribeThenReturnSingleValue() {
		Mono<String> helloMono = Mono.just("Hello");

		StepVerifier.create(helloMono)
				.expectNext("Hello")
				.expectComplete()
				.verify();
	}

	@Test
	void givenFluxPublisher_whenSubscribeThenReturnMultipleValues() {
		Flux<String> stringFlux = Flux.just("Hello", "Tuyucheng");

		StepVerifier.create(stringFlux)
				.expectNext("Hello")
				.expectNext("Tuyucheng")
				.expectComplete()
				.verify();
	}

	@Test
	void givenFluxPublisher_whenSubscribeThenReturnMultipleValuesWithError() {
		Flux<String> stringFlux = Flux.just("Hello", "Tuyucheng", "Error")
				.map(str -> {
					if (str.equals("Error"))
						throw new RuntimeException("Throwing Error");
					return str;
				});

		StepVerifier.create(stringFlux)
				.expectNext("Hello")
				.expectNext("Tuyucheng")
				.expectError()
				.verify();
	}
}