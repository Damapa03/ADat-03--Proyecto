package com.example.Proyecto_API_REST_Segura.service

import com.example.Proyecto_API_REST_Segura.model.Reserva
import com.example.Proyecto_API_REST_Segura.repository.ReservaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ReservaService{

    @Autowired
    private lateinit var reservaRepository: ReservaRepository
    @Autowired
    private lateinit var usuarioService: UsuarioService
    @Autowired
    private lateinit var vueloService: VueloService

    fun getReservas(): List<Reserva>{
        return reservaRepository.findAll()
    }
    fun getReservaById(id:Long): Any {
        try{ return reservaRepository.findById(id).get()
        }catch(ex:Exception){
            return ResponseEntity(mapOf("mensaje" to "Reserva no encontrado"), HttpStatus.NOT_FOUND)
        }
    }

    fun postReserva(userUsuario: String, idVuelo: Long): ResponseEntity<Reserva> {
        var reserva = Reserva(null, usuarioService.getUsuarioByUser(userUsuario), vueloService.getByID(idVuelo))
        return ResponseEntity(reservaRepository.save(reserva), HttpStatus.CREATED)
    }

    fun putReserva(reserva: Reserva, userUsuario: String?, idVuelo: Long?): ResponseEntity<Reserva> {
        if (userUsuario != null){
            reserva.usuario = usuarioService.getUsuarioByUser(userUsuario)
        }
        if (idVuelo != null){
            reserva.vuelo = vueloService.getByID(idVuelo)
        }
        return ResponseEntity(reservaRepository.save(reserva), HttpStatus.CREATED)
    }

    fun deleteReserva(id:Long): ResponseEntity<Map<String, String>> {
        if (reservaRepository.findById(id).isPresent){
            reservaRepository.deleteById(id)
            return ResponseEntity(mapOf("message" to "Reserva eliminada"), HttpStatus.OK)
        }else return ResponseEntity(mapOf("message" to "Reserva no encontrada"), HttpStatus.BAD_REQUEST)
    }
}