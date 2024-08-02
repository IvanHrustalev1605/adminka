package com.example.adminkawebportal.feign

import com.example.adminkawebportal.dto.ApplicationDto
import com.example.adminkawebportal.dto.CommentDto
import com.example.adminkawebportal.dto.DictSeaDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient(name = "web-portal-client")
interface WebPortalFeignClient {
    @GetMapping("dict/sea/sea-list")
    fun getList() : MutableList<DictSeaDto>

    @GetMapping("application/all-applications")
    fun getApplicationList(@RequestHeader("Authorization") token:String) : MutableList<ApplicationDto>

    @GetMapping("application/by-plicante-id/{plicanteId}")
    fun findByPlicanteId(@PathVariable plicanteId: Long?): MutableList<ApplicationDto>

    @GetMapping("application/by-plicante-id/comments/{plicanteApplicationId}")
    fun getAllComments(@PathVariable plicanteApplicationId: Long?): MutableList<CommentDto>

    @PutMapping("application/external/{applicationPlicanteId}/comment/{commentPlicanteId}/answer")
    fun answerToComment(
        @RequestHeader("Authorization") token:String,
        @PathVariable applicationPlicanteId: Long,
        @PathVariable commentPlicanteId: Long,
        @RequestBody answer: String?
    ): Any

}