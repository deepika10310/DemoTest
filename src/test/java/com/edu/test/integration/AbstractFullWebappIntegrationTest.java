package com.edu.test.integration;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.edu.yati.auth.configuration.EducationConfiguration;


@WebAppConfiguration
@ContextConfiguration(classes = { EducationConfiguration.class })
// @TestPropertySource(properties = { "create-db-script=db/create.sql" })

public abstract class AbstractFullWebappIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    protected WebApplicationContext webAppContext;

    @Autowired
    @Qualifier("mongoTemplate")
    protected MongoTemplate template;

    protected MockMvc mockMvc;

    private void cleanUp(final MongoTemplate mongoTemplate) {
        for (final String collectionName : mongoTemplate.getCollectionNames()) {
            if (!collectionName.startsWith("system.")) {
                mongoTemplate.dropCollection(collectionName);
            }
        }
    }

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
        cleanUp(template);
    }

}
