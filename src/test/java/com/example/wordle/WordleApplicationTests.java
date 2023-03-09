package com.example.wordle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 * Test 'sanity check' que comprueba que la clase main existe y funciona.
 */
@SpringBootTest
class WordleApplicationTests {

    @Autowired
    private WordleApplication application;

    @Test
    void contextLoads() throws Exception {
        assertThat(application).isNotNull();
    }

}
