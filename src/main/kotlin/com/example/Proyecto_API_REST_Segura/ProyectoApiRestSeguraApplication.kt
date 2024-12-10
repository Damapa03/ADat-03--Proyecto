package com.example.Proyecto_API_REST_Segura

import com.es.jwtSecurityKotlin.security.RSAKeysProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(RSAKeysProperties::class)
class ProyectoApiRestSeguraApplication

fun main(args: Array<String>) {
	runApplication<ProyectoApiRestSeguraApplication>(*args)
}
