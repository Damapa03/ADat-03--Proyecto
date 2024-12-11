package com.example.Proyecto_API_REST_Segura.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(unique = true, nullable = false)
    var username: String,
    @Column(nullable = false)
    var password: String,
    @OneToMany(mappedBy = "usuario", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var reservas: MutableList<Reserva>? = mutableListOf(),

    var ROL: String? = "USER"
) {
}