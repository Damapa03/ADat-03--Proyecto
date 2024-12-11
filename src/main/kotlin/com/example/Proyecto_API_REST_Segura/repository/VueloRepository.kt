package com.example.Proyecto_API_REST_Segura.repository

import com.example.Proyecto_API_REST_Segura.model.Vuelo
import org.springframework.data.jpa.repository.JpaRepository

interface VueloRepository: JpaRepository<Vuelo, Long> {
}