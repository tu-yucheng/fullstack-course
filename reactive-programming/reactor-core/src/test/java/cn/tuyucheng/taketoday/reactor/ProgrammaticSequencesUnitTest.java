package cn.tuyucheng.taketoday.reactor;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProgrammaticSequencesUnitTest {

	@Test
	void givenFluxWithStatefulImmutableGenerate_whenSubscribeAddItemsToCollect_thenAllItemsAreCollectedByTheSubscriber()
			throws InterruptedException {
		List<String> elements = new ArrayList<>();
		ProgrammaticSequences producer = new ProgrammaticSequences();
		producer.statefulImmutableGenerate().subscribe(elements::add);

		assertThat(elements).hasSize(101);
		assertThat(elements).contains("2 + 1 = 3", "2 + 101 = 103");
	}

	@Test
	void givenFluxWithStatefulMutableGenerate_whenSubscribeAddItemsToCollect_thenAllItemsAreCollectedByTheSubscriber()
			throws InterruptedException {
		List<String> elements = new ArrayList<>();
		ProgrammaticSequences producer = new ProgrammaticSequences();
		producer.statefulMutableGenerate().subscribe(elements::add);

		assertThat(elements).hasSize(102);
		assertThat(elements).contains("2 + 0 = 2", "2 + 101 = 103");
	}

	@Test
	void givenFluxWithHandle_whenSubscribeAddItemsToCollect_thenAllItemsAreCollectedByTheSubscriber()
			throws InterruptedException {
		List<String> elements = new ArrayList<>();
		ProgrammaticSequences producer = new ProgrammaticSequences();
		producer.handle().subscribe(elements::add);

		assertThat(elements).hasSize(5);
		assertThat(elements).contains("Elephant nr 2", "Elephant nr 10");
	}
}