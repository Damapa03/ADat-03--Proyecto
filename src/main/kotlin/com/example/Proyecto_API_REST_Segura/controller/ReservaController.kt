package com.example.Proyecto_API_REST_Segura.controller

import com.example.Proyecto_API_REST_Segura.model.Reserva
import com.example.Proyecto_API_REST_Segura.repository.ReservaRepository
import com.example.Proyecto_API_REST_Segura.service.ReservaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("/reserva")
class ReservaController {

    @Autowired
    private lateinit var reservaService: ReservaService

    @GetMapping()
    fun getReservas() : List<Reserva>{
        return reservaService.getReservas()
    }

    @GetMapping("/{idReserva}")
    fun getReservaById(@PathVariable idReserva : Int) : Reserva {
        var idL: Long = 0
        try {
            idL = idReserva.toLong()
        }catch (e : Exception){

        }
        return reservaService.getReservaById(idL)
    }

    @PostMapping()
    fun addReserva(@RequestBody user: String, idVuelo: Int){
        var idVueloL: Long = 0
        try {
            idVueloL = idVuelo.toLong()
        }catch (e : Exception){

        }

        reservaService.postReserva(user, idVueloL)
    }

    @PutMapping()
    fun updateReserva(@RequestBody reserva : Reserva){
        reservaService.putReserva(reserva)
    }

    @DeleteMapping("/{idReserva}")
    fun deleteReserva(@PathVariable idReserva : Int){
        var idL: Long = 0
        try {
            idL = idReserva.toLong()
            reservaService.deleteReserva(idL)
        }catch (e : Exception){

        }

    }
}