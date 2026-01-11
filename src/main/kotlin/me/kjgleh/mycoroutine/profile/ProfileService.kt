package me.kjgleh.mycoroutine.profile

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProfileService(
    private val memberClient: MemberClient,
    private val orderClient: OrderClient,
    private val couponClient: CouponClient,
) {

    suspend fun load(memberId: Long): ProfileAggregate = coroutineScope {
        val totalStart = System.nanoTime()

        val memberDeferred = async(Dispatchers.IO) {
            val start = System.nanoTime()
            val member = memberClient.getMember(memberId)
            val durationMs = (System.nanoTime() - start) / 1_000_000
            member to durationMs
        }

        val ordersDeferred = async(Dispatchers.IO) {
            val start = System.nanoTime()
            val orders = orderClient.getOrders(memberId)
            val durationMs = (System.nanoTime() - start) / 1_000_000
            orders to durationMs
        }

        val couponsDeferred = async(Dispatchers.IO) {
            val start = System.nanoTime()
            val coupons = couponClient.getCoupons(memberId)
            val durationMs = (System.nanoTime() - start) / 1_000_000
            coupons to durationMs
        }

        val (member, memberDurationMs) = memberDeferred.await()
        val (orders, ordersDurationMs) = ordersDeferred.await()
        val (coupons, couponsDurationMs) = couponsDeferred.await()
        val totalDurationMs = (System.nanoTime() - totalStart) / 1_000_000

        logger.info(
            "profile.load memberId={} totalMs={} memberMs={} ordersMs={} couponsMs={}",
            memberId,
            totalDurationMs,
            memberDurationMs,
            ordersDurationMs,
            couponsDurationMs,
        )

        ProfileAggregate(
            member = member,
            orders = orders,
            coupons = coupons,
        )
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ProfileService::class.java)
    }

}
