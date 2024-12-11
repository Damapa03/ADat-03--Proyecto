package com.example.Proyecto_API_REST_Segura.service

import com.example.Proyecto_API_REST_Segura.model.Reserva
import com.example.Proyecto_API_REST_Segura.repository.ReservaRepository
import org.springframework.beans.factory.annotation.Autowired
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
    fun getReservaById(id:Long):Reserva{
        return reservaRepository.findById(id).get()
    }

    fun postReserva(userUsuario: String, idVuelo: Long){
        var reserva = Reserva(null, usuarioService.getUsuarioByUser(userUsuario), vueloService.getByID(idVuelo))
        reservaRepository.save(reserva)
    }

    fun putReserva(reserva: Reserva){
        reservaRepository.save(reserva)
    }

    fun deleteReserva(id:Long){
        reservaRepository.deleteById(id)
    }
}