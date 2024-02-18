package com.example.orderapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderApiTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("1-1. 주문 생성 - 성공")
    void test1_1() throws Exception {

        byte[] body = """
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
                """.getBytes();

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andExpect(status().isOk())
                .andExpect(content().json("""
                         {
                           "id": 1,
                           "orderItemDtoList": [
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
                        """));
    }

    @Test
    @DisplayName("1-2 주문 생성 - 실패(수량 부족)")
    void test1_2() throws Exception {
        byte[] body = """
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
                 """.getBytes();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isInternalServerError())
                .andExpect(content().json(
        """
                    {
                        "message": "1번 상품의 수량이 부족합니다."
                    }
                    """
                ));

    }

    @Test
    @DisplayName("1-3. 주문 생성 - 실패(Item 찾지 못함)")
    void test1_3 () throws Exception {
        byte[] body = """
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
                """.getBytes();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)

                )
                .andExpect(status().isNotFound())
                .andExpect(content().json(
                """
                        {
                            "message": "Item을 찾지 못했습니다."
                        }
                        """
                ));
    }

}
