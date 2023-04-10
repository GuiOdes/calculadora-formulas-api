package com.guiodes.multifunctionalcalculator.controller

import com.guiodes.multifunctionalcalculator.request.CalculateThreeFieldsRequest
import com.guiodes.multifunctionalcalculator.response.PitagorasFormulaResponse
import com.guiodes.multifunctionalcalculator.service.PitagorasService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping
@RestController("/pitagoras")
class PitagorasController(val pitagorasService: PitagorasService) {
    @PostMapping
    fun calculatePitagoras(@RequestBody calculatePitagorasRequest: CalculateThreeFieldsRequest): PitagorasFormulaResponse {
        return pitagorasService.calcutePitagoras(calculatePitagorasRequest)
    }
}