package me.kjgleh.mycoroutine.profile

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.request
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class ProfileControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `프로필 조회`() {
        val memberId = 1L

        val mvcResult = mockMvc.perform(
            get("/profiles/$memberId")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(request().asyncStarted())
            .andReturn()

        mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.memberId").value(memberId))
            .andExpect(jsonPath("$.member.id").value(memberId))
            .andExpect(jsonPath("$.member.name").value("member-$memberId"))
            .andExpect(jsonPath("$.orders").isArray)
            .andExpect(jsonPath("$.orders[0].id").value(1))
            .andExpect(jsonPath("$.orders[0].amount").value(10000))
            .andExpect(jsonPath("$.coupons").isArray)
            .andExpect(jsonPath("$.coupons[0].id").value(1))
            .andExpect(jsonPath("$.coupons[0].discountAmount").value(10000))
    }
}
