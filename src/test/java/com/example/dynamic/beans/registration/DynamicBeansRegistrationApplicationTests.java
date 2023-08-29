package com.example.dynamic.beans.registration;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DynamicBeansRegistrationApplicationTests {

	@Autowired
	private List<Server> servers;

	@Test
	void contextLoads() {
		this.servers.stream().map(Server::toString).forEach(log::info);
		Assertions.assertEquals(2, this.servers.size());
	}
}
