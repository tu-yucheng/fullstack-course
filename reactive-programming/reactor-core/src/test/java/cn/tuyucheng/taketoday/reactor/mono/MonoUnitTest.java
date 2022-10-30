package cn.tuyucheng.taketoday.reactor.mono;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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

	@Test
	void whenMonoProducesListOfElements_thenConvertToFluxOfElements() {
		Mono<List<String>> monoList = monoOfList();

		StepVerifier.create(monoToFluxUsingFlatMapIterable(monoList))
				.expectNext("one", "two", "three", "four")
				.verifyComplete();

		StepVerifier.create(monoToFluxUsingFlatMapMany(monoList))
				.expectNext("one", "two", "three", "four")
				.verifyComplete();
	}

	private Mono<List<String>> monoOfList() {
		List<String> list = new ArrayList<>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");

		return Mono.just(list);
	}

	private <T> Flux<T> monoToFluxUsingFlatMapMany(Mono<List<T>> monoList) {
		return monoList
				.flatMapMany(Flux::fromIterable)
				.log();
	}

	private <T> Flux<T> monoToFluxUsingFlatMapIterable(Mono<List<T>> monoList) {
		return monoList
				.flatMapIterable(list -> list)
				.log();
	}
}