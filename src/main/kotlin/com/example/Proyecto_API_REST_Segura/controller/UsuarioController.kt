package com.example.Proyecto_API_REST_Segura.controller

import com.example.Proyecto_API_REST_Segura.model.Usuario
import com.example.Proyecto_API_REST_Segura.service.TokenService
import com.example.Proyecto_API_REST_Segura.service.UsuarioService
import org.apache.catalina.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.naming.AuthenticationException

@RestController
@RequestMapping("/usuario")
class UsuarioController {

    @Autowired
    private lateinit var usuarioService: UsuarioService

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var tokenService: TokenService

    @PostMapping("/login")
    fun login(@RequestBody user: Usuario): ResponseEntity<Any> {
        var authentication: Authentication
        try {
            authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    user.user,
                    user.password
                )
            )
        } catch (e: AuthenticationException) {
            return ResponseEntity(mapOf("mensaje" to "Credencialess incorrectas"), HttpStatus.UNAUTHORIZED)
        }


        // Si pasamos la autentificacion, significa que estamos bien autentificados
        // Pasamos a generar el token

        var token = ""
        token = tokenService.generarToken(authentication)


        return ResponseEntity(mapOf("token" to token), HttpStatus.CREATED)

    }

    @PostMapping("/register")
    fun register(@RequestBody user: Usuario): ResponseEntity<Any> {

        // Comprobación mínima
        if (usuarioService.checkUser(user)){
            // Llamar al UsuarioService para insertar un usuario
            usuarioService.registerUsuario(user)
        }else return ResponseEntity(mapOf("mensaje" to "La contraseña debe tener minimo 8 caracteres y un numero y el usuario no puede estar vacio"), HttpStatus.UNAUTHORIZED)

        // Devolver el usuario insertado
        return ResponseEntity(user, HttpStatus.CREATED)
    }
}