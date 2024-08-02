package com.example.adminkawebportal.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CommentDto {
    var question: String = ""
    var answer: String = ""
    var plicanteId: Long = 0
    var id: Long = 0
}