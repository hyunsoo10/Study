package com.example.kotlin2

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
    @DisplayName("1-1유저 조회 - 성공")
    fun test1_1() {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/1")
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

    @Test
    @DisplayName("1-2유저 조회 - 실패")
    fun test1_2() {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/999")

                )
                .andExpect(MockMvcResultMatchers.status().isNotFound)
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                            {
                              "message": "해당 유저를 찾을 수 없습니다."
                            } 
                        """.trimIndent()
                ))
    }

}