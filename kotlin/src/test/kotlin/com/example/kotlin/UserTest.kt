package com.example.kotlin

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class UserTest(@Autowired val mockMvc: MockMvc) {


    @Test
    @DisplayName("유저 조회")
    fun test1_1() {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/1")
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                            {
                              "seq": 1,
                              "name": "현수",
                              "age": 30
                            }
                        """.trimIndent()
                ));
    }

}