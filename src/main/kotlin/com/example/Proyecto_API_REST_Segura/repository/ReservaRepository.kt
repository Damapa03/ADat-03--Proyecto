package com.example.Proyecto_API_REST_Segura.repository

import com.example.Proyecto_API_REST_Segura.model.Reserva
import org.springframework.data.jpa.repository.JpaRepository

interface ReservaRepository: JpaRepository<Reserva, Long> {
}