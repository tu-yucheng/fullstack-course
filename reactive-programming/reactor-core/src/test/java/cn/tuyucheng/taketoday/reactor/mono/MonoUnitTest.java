package cn.tuyucheng.taketoday.reactor.mono;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class MonoUnitTest {

	@Test
	void whenMonoProducesString_thenBlockAndConsume() {
		String result1 = blockingHelloWorld().block();
		assertEquals("Hello world!", result1);

		String result2 = blockingHelloWorld().block(Duration.of(1000, ChronoUnit.MILLIS));
		assertEquals("Hello world!", result2);

		Optional<String> result3 = Mono.<String>empty().blockOptional();
		assertEquals(Optional.empty(), result3);
	}

	private Mono<String> blockingHelloWorld() {
		// blocking
		return Mono.just("Hello world!");
	}

	@Test
	void whenMonoProducesString_thenConsumeNonBlocking() {
		blockingHelloWorld()
				.doOnNext(result -> assertEquals("Hello world!", result))
				.subscribe();

		blockingHelloWorld()
				.subscribe(result -> assertEquals("Hello world!", result));
	}
}