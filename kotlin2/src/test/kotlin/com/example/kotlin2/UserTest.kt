package com.example.kotlin2

import com.example.kotlin2.domain.user.Role
import com.example.kotlin2.domain.user.User
import com.example.kotlin2.presentation.dto.user.UserSignDto
import com.example.kotlin2.service.UserService
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.*
import org.springframework.security.test.context.support.WithAnonymousUser
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.awt.PageAttributes.MediaType

@SpringBootTest
@AutoConfigureMockMvc
class UserTest(@Autowired val mockMvc: MockMvc) {



    @Test
    @WithMockUser(username = "user", roles = ["ADMIN"] )
    @DisplayName("1-1유저 조회 - 성공")
    fun test1_1() {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/1")
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                          {
                              "userId": "hscho",
                              "name": "조현수",
                              "email": "hyunsoo@naver.com"
                          }
                        """.trimIndent()
                ));
    }

    @Test
    @WithMockUser(username = "user", roles = ["ADMIN"] )
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

    @Test
    @WithMockUser(username = "user", roles = ["ADMIN"] )
    @DisplayName("2-1회원가입 - 성공")
    fun test2_1() {

        val body = """
                {
                    "userId": "toss",
                    "name": "토스",
                    "password": 1234,
                    "email": "toss@toss.com",
                    "age": 3
                }
                """.trimIndent().toByteArray()

        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/signup")
                        .contentType(APPLICATION_JSON)
                        .content(body)

                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        {
                            "data": {
                                "userId": "toss",
                                "name": "토스",
                                "email": "toss@toss.com"
                            },
                            "msg": "회원가입 성공"
                        }
                        """.trimIndent()
                ))
    }
    @Test
    @WithMockUser(username = "user", roles = ["ADMIN"] )
    @DisplayName("2-2회원가입 - 아이디 사용 가능")
    fun test2_2() {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/duplicate?userId=tosspass")
                 )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        {
                            "data": null,
                            "msg": "사용 가능한 아이디입니다."
                        }
                        """.trimIndent()
                ))
    }
    @Test
    @WithMockUser(username = "user", roles = ["ADMIN"] )
    @DisplayName("2-3회원가입 - 아이디 중복")
    fun test2_3() {
        test2_1()
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/duplicate?userId=toss")
                 )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        {
                            "data": null,
                            "msg": "이미 존재하는 아이디입니다."
                        }
                        """.trimIndent()
                ))
    }
    @Test
    @WithMockUser(username = "user", roles = ["ADMIN"] )
    @DisplayName("3-1 일반 로그인 성공")
    fun test3_1() {
        val body = """
                {
                    "userId": "hscho",
                    "password": 1234
                }
                """.trimIndent().toByteArray()
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/login")
                        .contentType(APPLICATION_JSON)
                        .content(body)
                 )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(
                        """
                        true
                        """.trimIndent()
                ))
    }
    @Test
    @WithMockUser(username = "user", roles = ["ADMIN"] )
    @DisplayName("3-2 일반 로그인 실패 - 아이디 없음")
    fun test3_2() {
        val body = """
                {
                    "userId": "nobody",
                    "password": 12345
                }
                """.trimIndent().toByteArray()
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/login")
                        .contentType(APPLICATION_JSON)
                        .content(body)
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
    @Test
    @WithMockUser(username = "user", roles = ["ADMIN"] )
    @DisplayName("3-3 일반 로그인 실패 - 비밀번호 불일치")
    fun test3_3() {
        val body = """
                {
                    "userId": "hscho",
                    "password": 12345
                }
                """.trimIndent().toByteArray()
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/login")
                        .contentType(APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        {
                            "message": "비밀번호가 일치하지 않습니다."
                        }
                        """.trimIndent()
                ))
    }

    @WithMockUser(username = "user", roles = ["ADMIN"] )
    @Throws(Exception::class)
    fun test4_init() {
        val body = """
                {
                    "userId": "toss2",
                    "name": "토스2",
                    "password": "1234",
                    "email": "toss2@toss.com",
                    "age": 3
                }
				""".trimIndent().toByteArray()

        mockMvc.perform(
                MockMvcRequestBuilders.post("/auth/signup")
                        .contentType(APPLICATION_JSON)
                        .content(body)
        )
    }

    @Test
    @WithMockUser(username = "user", roles = ["ADMIN"] )
    @DisplayName("4-1 JWT 로그인 성공")
    fun test4_1() {
        test4_init()
        val body = """
                {
                    "userId": "toss2",
                    "password": "1234"
                }
                """.trimIndent().toByteArray()
        mockMvc.perform(
                MockMvcRequestBuilders.post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("refresh-token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("access-token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("token_type").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("expires_in").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("refresh-token").value(containsString("eyJhbGciOiJIUzI1NiJ9")))
                .andExpect(MockMvcResultMatchers.jsonPath("access-token").value(containsString("eyJhbGciOiJIUzI1NiJ9")));
    }
    @Test
    @WithMockUser(username = "user", roles = ["ADMIN"] )
    @DisplayName("4-2 JWT 로그인 실패 - 아이디 없음")
    fun test4_2() {
        val body = """
                {
                    "userId": "nobody",
                    "password": "1234"
                }
                """.trimIndent().toByteArray()
        mockMvc.perform(
                MockMvcRequestBuilders.post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(body)
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
    @Test
    @WithMockUser(username = "user", roles = ["ADMIN"] )
    @DisplayName("4-3 JWT 로그인 실패 - 비밀번호 불일치(자격 증명 실패)")
    fun test4_3() {
        test4_init()
        val body = """
                {
                    "userId": "toss2",
                    "password": "12345"
                }
                """.trimIndent().toByteArray()
        mockMvc.perform(
                MockMvcRequestBuilders.post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(MockMvcResultMatchers.status().isUnauthorized)
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        {
                            "message": "자격 증명에 실패하였습니다."
                        }
                        """.trimIndent()
                ))
    }

}