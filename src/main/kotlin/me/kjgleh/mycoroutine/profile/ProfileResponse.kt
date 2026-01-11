package me.kjgleh.mycoroutine.profile

data class ProfileResponse(
    val memberId: Long,
    val member: MemberDto,
    val orders: List<OrderDto>,
    val coupons: List<CouponDto>,
)
