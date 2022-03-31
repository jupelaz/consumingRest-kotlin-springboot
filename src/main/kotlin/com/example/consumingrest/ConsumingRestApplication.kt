package com.example.consumingrest

import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

private val log = KotlinLogging.logger {}

@SpringBootApplication
class ConsumingRestApplication {
    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder.build()
    }

    @Bean
    fun run(restTemplate: RestTemplate): CommandLineRunner {
        return CommandLineRunner {
            val quote = restTemplate.getForObject(
                "https://animechan.vercel.app/api/random", Quote::class.java
            )
            log.info(quote.toString())
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ConsumingRestApplication>(*args)
}
