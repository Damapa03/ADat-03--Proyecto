package com.example.Proyecto_API_REST_Segura.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "reservas")
data class Reserva(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToOne(cascade = [(CascadeType.MERGE)])
    @JoinColumn(name = "usuario_id")
    var usuario: Usuario,
    @ManyToOne(cascade = [(CascadeType.MERGE)])
    @JoinColumn(name = "vuelo_id")
    var vuelo: Vuelo
) {
}