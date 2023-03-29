package com.guiodes.multifunctionalcalculator.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class PitagorasController(

) {
    @PostMapping("/pitagoras")
    fun calculatePitagoras(@RequestBody)
}