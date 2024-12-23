package com.example.Proyecto_API_REST_Segura.controller

import com.example.Proyecto_API_REST_Segura.model.Vuelo
import com.example.Proyecto_API_REST_Segura.service.VueloService
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
@RequestMapping("/vuelo")
class VueloController {

    @Autowired
    private lateinit var vueloService: VueloService
    @Autowired
    private lateinit var reservaController: ReservaController

    @GetMapping()
    fun getVuelo(): List<Vuelo>{
        return vueloService.getVuelos()
    }
    @GetMapping("/{idVuelo}")
    fun getVueloById(@PathVariable idVuelo: Int): Any {
        var idL: Long = 0
        try {
            idL = idVuelo.toLong()
            return vueloService.getByID(idL)
        }catch (e : Exception){
            return ResponseEntity(mapOf("mensaje" to "Vuelo no encontrado"), HttpStatus.NOT_FOUND)
        }

    }

    @PostMapping()
    fun postVuelo(@RequestBody vuelo: Vuelo): ResponseEntity<Any> {
        return vueloService.insertVuelo(vuelo)
    }

    @PutMapping("/{idVuelo}")
    fun putVuelo(@PathVariable idVuelo: Int, @RequestBody newVuelo: Vuelo): Any {
        var idL: Long = 0
        try {
            idL = idVuelo.toLong()
            var vuelo = vueloService.getByID(idL) as Vuelo
            newVuelo.id = vuelo.id
            return vueloService.updateVuelo(newVuelo)
        }catch (e : Exception){
            return ResponseEntity(mapOf("message" to "Vuelo no encontrado"), HttpStatus.BAD_REQUEST)
        }


    }

    @DeleteMapping("/{idVuelo}")
    fun deleteVuelo(@PathVariable idVuelo: Int): ResponseEntity<Map<String, String>> {
        var idL: Long = 0
        try {
            idL = idVuelo.toLong()
        }catch (e : Exception){

        }
        val reservas = reservaController.getReservas()
        reservas.forEach { reserva ->
            if (reserva.vuelo.id == idL){
                reservaController.deleteReserva(reserva.id!!.toInt())
            }
        }
        return vueloService.deleteVuelo(idL)
    }

}