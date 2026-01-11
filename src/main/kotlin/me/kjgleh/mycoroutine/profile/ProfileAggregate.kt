package me.kjgleh.mycoroutine.profile

data class ProfileAggregate(
    val member: MemberDto,
    val orders: List<OrderDto>,
    val coupons: List<CouponDto>,
)
