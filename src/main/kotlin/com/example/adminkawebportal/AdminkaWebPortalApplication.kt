package com.example.adminkawebportal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class AdminkaWebPortalApplication

fun main(args: Array<String>) {
    runApplication<AdminkaWebPortalApplication>(*args)
}
