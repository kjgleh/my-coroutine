package me.kjgleh.mycoroutine.profile

import org.springframework.stereotype.Service

@Service
class ProfileService(
    private val memberClient: MemberClient,
    private val orderClient: OrderClient,
) {

    fun load(memberId: Long): ProfileAggregate {
        val memberDeferred = memberClient.getMember(memberId)
        val ordersDeferred = orderClient.getOrders(memberId)

        return ProfileAggregate(
            member = memberDeferred,
            orders = ordersDeferred,
        )
    }
}
