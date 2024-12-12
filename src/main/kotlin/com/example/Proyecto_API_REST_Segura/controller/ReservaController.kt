package com.example.Proyecto_API_REST_Segura.controller

import com.example.Proyecto_API_REST_Segura.model.Reserva
import com.example.Proyecto_API_REST_Segura.service.ReservaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    fun getReservaById(@PathVariable idReserva : Int) : Any {
        var idL: Long = 0
        try {
            idL = idReserva.toLong()
        }catch (e : Exception){
        }
        return reservaService.getReservaById(idL)
    }

    @PostMapping()
    fun addReserva(@RequestBody userVuelo: Map<String, Any>): ResponseEntity<out Any> {

        val user = userVuelo["user"] as? String ?: return ResponseEntity(mapOf("message" to "Usuario no proporcionado"), HttpStatus.BAD_REQUEST)
        val idVueloL: Long = try {
            (userVuelo["idVuelo"] as? Int)?.toLong() ?: return ResponseEntity(mapOf("message" to "ID de vuelo inválido"), HttpStatus.BAD_REQUEST)
        } catch (e: Exception) {
            return ResponseEntity(mapOf("message" to "Error en el usuario o vuelo"), HttpStatus.BAD_REQUEST)
        }

        return reservaService.postReserva(user, idVueloL)
    }

    @PutMapping("/{idReserva}")
    fun updateReserva(
        @RequestBody userVuelo: Map<String, Any>,
        @PathVariable idReserva: Int
    ): ResponseEntity<out Any> {
        // Convertir idReserva a Long
        val idReservaL: Long = try {
            idReserva.toLong()
        } catch (e: Exception) {
            return ResponseEntity(mapOf("message" to "ID de reserva inválido"), HttpStatus.BAD_REQUEST)
        }

        // Buscar la reserva existente
        val reserva: Reserva =try {
            reservaService.getReservaById(idReservaL) as Reserva
        } catch (e: Exception) {
            return ResponseEntity(mapOf("message" to "Reserva no encontrada"), HttpStatus.BAD_REQUEST)
        }

        // Obtener y validar los datos del JSON
        val user = userVuelo["user"].toString()
        val idVueloL: Long? = try {
            (userVuelo["idVuelo"] as? Int)?.toLong()
        } catch (e: Exception) {
            null
        }

        return ResponseEntity(reservaService.putReserva(reserva,user,idVueloL), HttpStatus.OK)
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