package controller

import com.guiodes.multifunctionalcalculator.service.BhaskaraService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@Controller
class BhaskaraController(
    val bhaskaraService: BhaskaraService
) {
    @PostMapping("/calcular")
    fun calculateBhaskara(@RequestParam("a") a: Double,
                          @RequestParam("b") b: Double,
                          @RequestParam("c") c : Double){
        bhaskaraService.calculateBhaskaraService(a, b, c)
    }
}