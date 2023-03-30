package controller

import com.guiodes.multifunctionalcalculator.request.CalculateThreeFieldsRequest
import com.guiodes.multifunctionalcalculator.response.BhaskaraFormulaResponse
import com.guiodes.multifunctionalcalculator.service.BhaskaraService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class BhaskaraController(
    val bhaskaraService: BhaskaraService
) {
    @PostMapping("/bhaskara")
    fun calculateBhaskara(@RequestBody calculateBhaskaraRequest: CalculateThreeFieldsRequest):BhaskaraFormulaResponse{
        return bhaskaraService.calculateBhaskaraService(calculateBhaskaraRequest)
    }
}