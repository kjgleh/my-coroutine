package me.kjgleh.mycoroutine.profile

import kotlinx.coroutines.delay
import org.springframework.stereotype.Component

@Component
class MemberClient {
    suspend fun getMember(memberId: Long): MemberDto {
        delay(50)
        return MemberDto(id = memberId, name = "member-$memberId")
    }
}

@Component
class OrderClient {
    suspend fun getOrders(memberId: Long): List<OrderDto> {
        delay(80)
        return listOf(OrderDto(id = 1L, amount = 10_000))
    }
}


@Component
class CouponClient {
    fun getCoupons(memberId: Long): List<CouponDto> {
        Thread.sleep(100)
        return listOf(CouponDto(id = 1L, discountAmount = 10_000))
    }
}

data class MemberDto(val id: Long, val name: String)
data class OrderDto(val id: Long, val amount: Long)
data class CouponDto(val id: Long, val discountAmount: Long)
