package com.guiodes.multifunctionalcalculator.request


data class InterestCalculateRequest(
  val interest:Double?,
  val amount:Double?,
  var capital:Double?,
  val rate:Double?,
  val duration: Double?
)
