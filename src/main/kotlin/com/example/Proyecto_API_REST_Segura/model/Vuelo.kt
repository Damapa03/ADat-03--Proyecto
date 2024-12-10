package com.example.Proyecto_API_REST_Segura.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "vuelos")
data class Vuelo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var destino: String,
    @Column(nullable = false)
    var plazas: Int,
    @Column(nullable = false)
    var fechaSalida: LocalDate,
    @Column(nullable = false)
    var horaSalida: LocalTime,
    @Column(nullable = false)
    var fechaLlegada: LocalDate,
    @Column(nullable = false)
    var horaLlegada: LocalTime,
    @OneToMany(mappedBy = "vuelo", cascade = [CascadeType.ALL])
    var reservas: MutableList<Reserva>? = mutableListOf()
) {
}