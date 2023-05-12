package com.guiodes.multifunctionalcalculator.controller

import com.guiodes.multifunctionalcalculator.request.InterestCalculateRequest
import com.guiodes.multifunctionalcalculator.response.InterestFormulaResponse
import com.guiodes.multifunctionalcalculator.service.InterestService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/interest")
class InterestCalculateController(
    val interestService: InterestService
) {
    @PostMapping("/simple")
    fun calculateInterestSimple(@RequestBody calculateInterestSimple: InterestCalculateRequest):InterestFormulaResponse {
        return interestService.calculeteInterestSimple(calculateInterestSimple)
    }
}