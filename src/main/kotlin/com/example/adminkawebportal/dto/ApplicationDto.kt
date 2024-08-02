package com.example.adminkawebportal.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
class ApplicationDto {
    @JsonProperty(value = "status")
    var status: String = ""
    @JsonProperty(value = "createdAt")
    var createdAt: LocalDate = LocalDate.now()
    @JsonProperty(value = "id")
    var id: Long = 0
    @JsonProperty(value = "plicanteId")
    var plicanteId: Long = 0
}