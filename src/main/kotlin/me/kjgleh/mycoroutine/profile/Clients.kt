package me.kjgleh.mycoroutine.profile

import org.springframework.stereotype.Component

@Component
class MemberClient {
    fun getMember(memberId: Long): MemberDto {
        return MemberDto(id = memberId, name = "member-$memberId")
    }
}

@Component
class OrderClient {
    fun getOrders(memberId: Long): List<OrderDto> {
        return listOf(OrderDto(id = 1L, amount = 10_000))
    }
}
