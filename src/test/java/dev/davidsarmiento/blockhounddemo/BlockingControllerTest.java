package dev.davidsarmiento.blockhounddemo;

import dev.davidsarmiento.blockhounddemo.controller.BlockingController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.blockhound.BlockHound;

@WebFluxTest(BlockingController.class)
public class BlockingControllerTest {

    @Autowired
    private WebTestClient webClient;

    @BeforeAll
    static void setupBlockHound() {
        BlockHound.install();
    }

    @Test
    void shouldDetectBlockingCall() {
        webClient.get().uri("/blocking")
                .exchange()
                .expectStatus().is5xxServerError() // (BlockingOperationError)
                .expectBody(String.class);
    }

    @Test
    void shouldAllowNonBlockingCall() {
        webClient.get().uri("/non-blocking")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Non-blocking code");
    }
}