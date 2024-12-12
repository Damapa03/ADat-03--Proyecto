package com.example.Proyecto_API_REST_Segura.service

import com.example.Proyecto_API_REST_Segura.controller.ReservaController
import com.example.Proyecto_API_REST_Segura.model.Vuelo
import com.example.Proyecto_API_REST_Segura.repository.VueloRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import kotlin.reflect.full.memberProperties

@Service
class VueloService {

    @Autowired
    private lateinit var vueloRepository: VueloRepository

    fun getVuelos(): List<Vuelo>{
        return vueloRepository.findAll()
    }
    fun getByID(id: Long): Vuelo {
        return vueloRepository.findById(id).get()
    }

    fun insertVuelo(vuelo: Vuelo): ResponseEntity<Any> {
        return if (checkNull(vuelo)){
            ResponseEntity(vueloRepository.save(vuelo), HttpStatus.CREATED)
        }else ResponseEntity(mapOf("message" to "Error al crear el vuelo"), HttpStatus.BAD_REQUEST)
    }

    fun updateVuelo(vuelo: Vuelo){
        if (checkNull(vuelo)){
            vueloRepository.save(vuelo)
        }
    }

    fun deleteVuelo(id: Long): ResponseEntity<Map<String, String>> {
        if (vueloRepository.findById(id).isPresent){
            vueloRepository.deleteById(id)
            return ResponseEntity(mapOf("message" to "Vuelo eliminado"), HttpStatus.OK)
        }else return ResponseEntity(mapOf("message" to "Vuelo no encontrado"), HttpStatus.BAD_REQUEST)
    }

    fun checkNull(vuelo: Vuelo): Boolean {
        var flag = true

        //Comprueba cada propiedad de la clase para saber si es nula o no
        Vuelo::class.memberProperties.forEach { property ->
            if (property.get(vuelo) == null && property.get(vuelo) != vuelo.id){
                flag = false
            }
        }
        return flag
    }
}