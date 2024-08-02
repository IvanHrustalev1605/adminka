package com.example.adminkawebportal.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

class PageParam(
    val limit: @Min(1) Int? = null,
    val page: @Min(1) Int? = null) {
}