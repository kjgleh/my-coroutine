package me.kjgleh.mycoroutine.profile

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProfileServiceTests {

    @Autowired
    private lateinit var profileService: ProfileService

    @Test
    fun `프로필 집계`() = runTest {
        val memberId = 1L

        val result = profileService.load(memberId)

        assertThat(result.member.id).isEqualTo(memberId)
        assertThat(result.member.name).isEqualTo("member-$memberId")
        assertThat(result.orders).hasSize(1)
        assertThat(result.orders[0].id).isEqualTo(1L)
        assertThat(result.orders[0].amount).isEqualTo(10_000)
        assertThat(result.coupons).hasSize(1)
        assertThat(result.coupons[0].id).isEqualTo(1L)
        assertThat(result.coupons[0].discountAmount).isEqualTo(10_000)
    }

    @Test
    fun `프로필 집계 - 집계 시간 로그 확인`() {
        runBlocking {
            profileService.load(1L)
        }
    }
}
