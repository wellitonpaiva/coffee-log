package com.thescientist.coffeelog;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {CoffeeLogController.class})
public class CoffeeLogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CoffeeLogRepo repo;

    @Test
    void add() throws Exception {
        Recipe recipe = Recipe.builder()
                .water(BigDecimal.ONE)
                .coffee(BigDecimal.ONE)
                .method("V60")
                .build();

        when(repo.add(any())).thenReturn(recipe);

        String expected = objectMapper.writeValueAsString(recipe);
        System.out.println(expected);
        MvcResult result = mockMvc.perform(post("/coffee")
                .contentType(APPLICATION_JSON_VALUE)
                .content(expected))
                .andExpect(status().isCreated())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertThat(content, is(expected));
    }

    @Test
    void list() throws Exception {
        Recipe recipe = Recipe.builder()
                .id(0)
                .water(BigDecimal.ONE)
                .coffee(BigDecimal.ONE)
                .method("V60")
                .build();
        String expected = objectMapper.writeValueAsString(singletonList(recipe));

        when(repo.getAll()).thenReturn(singletonList(recipe));
        MvcResult result = mockMvc.perform(get("/coffees")
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        assertThat(content, is(expected));
    }
}
