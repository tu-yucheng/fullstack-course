package cn.tuyucheng.taketoday.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NetworkTrafficProducerPush {

	Consumer<String> listener;

	public void subscribe(Consumer<String> consumer) {
		Flux<String> flux = Flux.push(sink ->
				NetworkTrafficProducerPush.this.listener = sink::next, FluxSink.OverflowStrategy.DROP);
		flux.subscribe(consumer);
	}

	public void onPacket(String packet) {
		listener.accept(packet);
	}
}