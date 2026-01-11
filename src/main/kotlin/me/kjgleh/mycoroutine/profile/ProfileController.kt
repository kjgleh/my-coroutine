package me.kjgleh.mycoroutine.profile

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfileController(
    private val profileService: ProfileService,
) {

    @GetMapping("/profiles/{memberId}")
    fun getProfile(@PathVariable memberId: Long): ProfileResponse {
        val result = profileService.load(memberId)

        return ProfileResponse(
            memberId = memberId,
            member = result.member,
            orders = result.orders,
        )
    }

}
