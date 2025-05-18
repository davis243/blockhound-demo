package dev.davidsarmiento.blockhounddemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class BlockingController {

    @GetMapping("/blocking")
    public Mono<String> blockingEndpoint() {
        return Mono.just("Starting blocking code...")
                .doOnNext(s -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                })
                .map(s -> s + "Finishing blocking code...");
    }

    @GetMapping("/non-blocking")
    public Mono<String> nonBlockingEndpoint() {
        return Mono.just("Non-blocking code");
    }
}
