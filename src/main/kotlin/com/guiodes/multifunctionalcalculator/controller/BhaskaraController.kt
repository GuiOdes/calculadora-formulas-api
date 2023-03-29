package com.guiodes.multifunctionalcalculator.controller

import com.guiodes.multifunctionalcalculator.request.CalculateBhaskaraRequest
import com.guiodes.multifunctionalcalculator.response.BhaskaraFormulaResponse
import com.guiodes.multifunctionalcalculator.service.BhaskaraService
import org.springframework.stereotype.Controller
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
    fun calculateBhaskara(@RequestBody calculateBhaskaraRequest: CalculateBhaskaraRequest):BhaskaraFormulaResponse{
        return bhaskaraService.calculateBhaskaraService(calculateBhaskaraRequest)
    }
}