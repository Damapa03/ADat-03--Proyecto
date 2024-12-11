package com.example.Proyecto_API_REST_Segura.controller

import com.example.Proyecto_API_REST_Segura.model.Vuelo
import com.example.Proyecto_API_REST_Segura.service.VueloService
import org.springframework.beans.factory.annotation.Autowired
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


    @GetMapping()
    fun getVuelo(): List<Vuelo>{
        return vueloService.getVuelos()
    }
    @GetMapping("/{idVuelo}")
    fun getVueloById(@PathVariable idVuelo: Int):Vuelo{
        var idL: Long = 0
        try {
            idL = idVuelo.toLong()
        }catch (e : Exception){

        }

        return vueloService.getByID(idL) as Vuelo
    }

    @PostMapping()
    fun postVuelo(@RequestBody vuelo: Vuelo){
        vueloService.insertVuelo(vuelo)
    }

    @PutMapping()
    fun putVuelo(@RequestBody vuelo: Vuelo){
        vueloService.updateVuelo(vuelo)
    }

    @DeleteMapping("/{idVuelo}")
    fun deleteVuelo(@PathVariable idVuelo: Int){
        var idL: Long = 0
        try {
            idL = idVuelo.toLong()
        }catch (e : Exception){

        }
        vueloService.deleteVuelo(idL)
    }

}