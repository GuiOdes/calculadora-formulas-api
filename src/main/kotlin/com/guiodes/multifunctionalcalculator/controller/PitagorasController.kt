package com.guiodes.multifunctionalcalculator.controller

import com.guiodes.multifunctionalcalculator.request.CalculateThreeFieldsRequest
import com.guiodes.multifunctionalcalculator.response.PitagorasFormulaResponse
import com.guiodes.multifunctionalcalculator.service.PitagorasService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class PitagorasController(val pitagorasService: PitagorasService) {
    @PostMapping("/pitagoras")
    fun calculatePitagoras(@RequestBody calculatePitagorasRequest: CalculateThreeFieldsRequest): PitagorasFormulaResponse {
        return pitagorasService.


}