package com.unlivoid.chaoswebflux;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

@SpringBootApplication
public class ChaosWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChaosWebfluxApplication.class, args);
	}

}

@Slf4j
@RestController
@RequestMapping("/api/test")
class TestController {

	Flux<String> names = Flux.just("Harry\n", "Ron\n", "Hermione\n", "Neville\n", "Malfoy\n", "Tonks\n", "Lupin\n", "Sirius\n");

	@GetMapping("/mono")
	public Mono<String> monoTest() {
		log.info("Called /api/test/mono");
		return Mono.just("Mono Test: " + LocalDateTime.now());
	}

	@GetMapping("/flux")
	public Flux<String> fluxTest() {
		log.info("Called /api/test/flux");
		return names
				.delayElements(Duration.ofSeconds(1));
	}

	@PostMapping("/data")
	public Mono<Map<String, String>> jsonTest(@RequestBody Map<String, String> data) {
		log.info("Called /api/test/data");
		return Mono.just(data);
	}

}
