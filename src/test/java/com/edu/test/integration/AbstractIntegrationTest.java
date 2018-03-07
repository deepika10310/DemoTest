package com.edu.test.integration;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.edu.test.integration.config.TestFrameworkConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/integration-test.properties")
@ContextConfiguration(classes = { TestFrameworkConfig.class })
public abstract class AbstractIntegrationTest {
}
