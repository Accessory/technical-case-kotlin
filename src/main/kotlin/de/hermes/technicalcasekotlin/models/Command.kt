package de.hermes.technicalcasekotlin.models

import io.swagger.v3.oas.annotations.media.Schema


data class Command (@field:Schema( type = "String" , example = "north") val direction:String, val steps:Int)