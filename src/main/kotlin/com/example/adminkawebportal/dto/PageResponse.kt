package com.example.adminkawebportal.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.v3.oas.annotations.media.Schema
@JsonIgnoreProperties(ignoreUnknown = true)
class PageResponse(
     val page: Int = 0,
     val totalPages: Int = 0,
     val totalCount: Long = 0,
     val data: List<ApplicationDto>? = null
)