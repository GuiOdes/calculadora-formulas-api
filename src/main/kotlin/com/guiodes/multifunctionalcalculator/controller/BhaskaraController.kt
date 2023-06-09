package com.guiodes.multifunctionalcalculator.controller

import com.guiodes.multifunctionalcalculator.request.CalculateThreeFieldsRequest
import com.guiodes.multifunctionalcalculator.response.BhaskaraFormulaResponse
import com.guiodes.multifunctionalcalculator.service.BhaskaraService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bhaskara")
class BhaskaraController(
    val bhaskaraService: BhaskaraService
) {
    @PostMapping
    fun calculateBhaskara(@RequestBody calculateBhaskaraRequest: CalculateThreeFieldsRequest): BhaskaraFormulaResponse {
        return bhaskaraService.calculateBhaskaraService(calculateBhaskaraRequest)
    }
}