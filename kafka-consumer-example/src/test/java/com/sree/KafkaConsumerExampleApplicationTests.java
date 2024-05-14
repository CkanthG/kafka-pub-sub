package com.sree;

import com.sree.dto.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class KafkaConsumerExampleApplicationTests {

	Logger log = LoggerFactory.getLogger(KafkaConsumerExampleApplicationTests.class);

	@Container
	static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

	@DynamicPropertySource
	public static void initKafkaProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.consumer.bootstrap-servers", kafka::getBootstrapServers);
	}

	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	@Value("${kafka.topic}")
	private String kafkaTopic;

	@Test
	void consumeUserMessage() {
		log.info("consumeUserMessage started ...");
		User user = new User(1, "sree", "s@hotmail.com", "68687687687");
		kafkaTemplate.send(kafkaTopic, user);
		log.info("consumeUserMessage ended ...");
		await().pollInterval(Duration.ofSeconds(3)).atMost(10, TimeUnit.SECONDS).untilAsserted(
				() -> {

				}
		);

	}

}
