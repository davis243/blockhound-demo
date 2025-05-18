package dev.davidsarmiento.blockhounddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class BlockhoundDemoApplication {

	public static void main(String[] args) {
		BlockHound.install();
		SpringApplication.run(BlockhoundDemoApplication.class, args);
	}

}
