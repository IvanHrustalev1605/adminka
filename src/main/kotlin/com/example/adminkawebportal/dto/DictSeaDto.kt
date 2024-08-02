package com.example.adminkawebportal.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class DictSeaDto {
    @JsonProperty(value = "name")
    val name: String = ""
    @JsonProperty(value = "plicanteId")
    val  plicanteId: Int = 0
}