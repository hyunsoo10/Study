package com.example.kotlin2

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class OrderTest (@Autowired val mockMvc: MockMvc){

    @Test
    @DisplayName("1-1. 주문 생성 - 성공")
    @Throws(Exception::class)
    fun test1_1() {
        val body = """
                [
                    {
                        "id": 1,
                        "amount": 1
                    },
                    {
                        "id": 3,
                        "amount": 1
                    }
                ]
                
                """.trimIndent().toByteArray()

        mockMvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                         {
                           "id": 1,
                           "orderItems": [
                               {
                                   "id": 1,
                                   "name": "아이템1",
                                   "price": 10000,
                                   "amount": 1
                               },
                               {
                                   "id": 3,
                                   "name": "아이템3",
                                   "price": 30000,
                                   "amount": 1
                               }
                           ],
                           "totalPrice": 40000,
                           "totalAmount": 2,
                           "status": "CREATED"
                        }        
                        
                        """.trimIndent()))
    }

    @Test
    @DisplayName("1-2 주문 생성 - 실패(수량 부족)")
    @Throws(Exception::class)
    fun test1_2() {
        val body = """
                [
                	{
                		"id": 1,
                		"amount": 99999
                	},
                	{
                		"id": 3,
                		"amount": 1
                	}
                ] 
                 
                 """.trimIndent().toByteArray()
        mockMvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        )
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                    {
                        "message": "1번 상품의 수량이 부족합니다."
                    }
                    
                    """.trimIndent()
                ))
    }

    @Test
    @DisplayName("1-3. 주문 생성 - 실패(Item 찾지 못함)")
    @Throws(Exception::class)
    fun test1_3() {
        val body = """
                [
                    {
                        "id": 99999,
                        "amount": 1
                    },
                    {
                        "id": 3,
                        "amount": 1
                    }
                ]
                
                """.trimIndent().toByteArray()

        mockMvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)

        )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        {
                            "message": "Item을 찾지 못했습니다."
                        }
                        
                        """.trimIndent()
                ))
    }

    @Test
    @DisplayName("2-1 주문 상태 강제 변경 - 성공")
    @Throws(Exception::class)
    fun test2_1() {
//        test1_1();
        val body = """
            {
                "status": "SHIPPING"
            }
            
            """.trimIndent().toByteArray()

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                    {
                        "id": 1,
                        "orderItems": [
                            {
                                "id": 1,
                                "name": "아이템1",
                                "price": 10000,
                                "amount": 1
                            },
                            {
                                "id": 3,
                                "name": "아이템3",
                                "price": 30000,
                                "amount": 1
                            }
                        ],
                        "totalPrice": 40000,
                        "totalAmount": 2,
                        "status": "SHIPPING"
                    }
                    
                    """.trimIndent()
                ))
        test2_1_init()
    }

    @Throws(Exception::class)
    fun test2_1_init() {
        val body = """
				{
				    "status": "CREATED"
				}
				
				""".trimIndent().toByteArray()

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        )
    }

    @Test
    @DisplayName("2-2 주문 상태 변경 (실패)")
    @Throws(Exception::class)
    fun test2_2() {
        val body = """
            {
                "status": "SHIPPING"
            }
            
            """.trimIndent().toByteArray()
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/orders/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)

        )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                    {
                        "message": "Order를 찾지 못했습니다."
                    }
                    
                    """.trimIndent()
                ))
    }

    @Test
    @DisplayName("3-1 주문 번호로  조회 - 성공")
    @Throws(Exception::class)
    fun test3_1() {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders/1")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                {
                    "id": 1,
                    "orderItems": [
                        {
                            "id": 1,
                            "name": "아이템1",
                            "price": 10000,
                            "amount": 1
                        },
                        {
                            "id": 3,
                            "name": "아이템3",
                            "price": 30000,
                            "amount": 1
                        }
                    ],
                    "totalPrice": 40000,
                    "totalAmount": 2,
                    "status": "CREATED"
                }
                
                """.trimIndent()
                ))
    }

    @Test
    @DisplayName("3-2주문 번호로 조회 - 실패(Order 찾지 못함)")
    fun test3_2() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/orders/99999")
        )
            .andExpect(status().isNotFound())
            .andExpect(content().json("""
						{
						    "message": "Order를 찾지 못했습니다."
						}
						"""));
    }
    @Test
    @DisplayName("4-1주문 상태로 조회 - 요청 바디 존재")
    @Throws(Exception::class)
    fun test4_1() {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders?status=CREATED")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                
            [
                    {
                        "id": 1,
                        "orderItems": [
                            {
                                "id": 1,
                                "name": "아이템1",
                                "price": 10000,
                                "amount": 1
                            },
                            {
                                "id": 3,
                                "name": "아이템3",
                                "price": 30000,
                                "amount": 1
                            }
                        ],
                        "totalPrice": 40000,
                        "totalAmount": 2,
                        "status": "CREATED"
                    }
                ]
                
                """.trimIndent()
                ))
    }

    @Test
    @DisplayName("4-2주문 상태로 조회 - 요청 바디 없음")
    @Throws(Exception::class)
    fun test4_2() {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders?status=COMPLETED")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                    
                [

                 ]
                 
                 """.trimIndent()
                ))
    }

    @Test
    @DisplayName("5-1 주문 취소 - 성공")
    @Throws(Exception::class)
    fun test5_1() {
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/orders/1/cancel")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                {
                    "id": 1,
                    "orderItems": [
                        {
                            "id": 1,
                            "name": "아이템1",
                            "price": 10000,
                            "amount": 1
                        },
                        {
                            "id": 3,
                            "name": "아이템3",
                            "price": 30000,
                            "amount": 1
                        }
                    ],
                    "totalPrice": 40000,
                    "totalAmount": 2,
                    "status": "CANCELED"
                }
                
                """.trimIndent()
                ))
    }

    @Test
    @DisplayName("5-2 주문 취소 - 실패")
    @Throws(Exception::class)
    fun test5_2() {
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/orders/1/cancel")
        )
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                {
                "message": "이미 취소되었거나 취소할 수 없는 주문상태입니다."
                }
                
                """.trimIndent()
                ))
    }
}