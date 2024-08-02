package com.example.adminkawebportal.service

import com.example.adminkawebportal.dto.ApplicationDto
import com.example.adminkawebportal.dto.CommentDto
import com.example.adminkawebportal.dto.DictSeaDto
import com.example.adminkawebportal.feign.WebPortalFeignClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class WebPortalService(private val webPortalFeignClient: WebPortalFeignClient) {
    @Value("\${token}")
    lateinit var token: String

    fun gelList() : MutableList<DictSeaDto> {
        return webPortalFeignClient.getList()
    }
    fun getApplication() : MutableList<ApplicationDto> {
        return webPortalFeignClient
            .getApplicationList(token)
    }
    fun findByPlicanteId(plicanteId: Long) : MutableList<ApplicationDto> {
        return webPortalFeignClient.findByPlicanteId(plicanteId)
    }
    fun findAllCommnetsByApplicationPlicanteId(appPlicanteId: Long) : MutableList<CommentDto> {
        return webPortalFeignClient.getAllComments(appPlicanteId)
    }
    fun setAnswerToComment(applicationPlicanteId: Long, commentPlicanteId: Long, answer: String) {
        webPortalFeignClient.answerToComment(token, applicationPlicanteId, commentPlicanteId, answer)
    }
}