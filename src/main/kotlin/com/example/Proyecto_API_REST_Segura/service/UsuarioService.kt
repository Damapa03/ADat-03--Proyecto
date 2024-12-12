package com.example.Proyecto_API_REST_Segura.service

import com.example.Proyecto_API_REST_Segura.model.Usuario
import com.example.Proyecto_API_REST_Segura.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsuarioService: UserDetailsService {

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    @Autowired
    private lateinit var passwordEncoder:PasswordEncoder

    override fun loadUserByUsername(username: String?): UserDetails {
        var usuario: Usuario = usuarioRepository
            .findByUsername(username!!)
            .orElseThrow()

        return User.builder()
            .username(usuario.username)
            .password(usuario.password)
            .roles(usuario.roles)
            .build()
    }


    /*
    MÉTODO PARA INSERTAR UN USUARIO
     */
    fun registerUsuario(usuario: Usuario) : ResponseEntity<Any> {

        // Comprobamos que el usuario no existe en la base de datos
        var newUsuario: Usuario
        try {
            newUsuario = usuarioRepository.findByUsername(usuario.username).orElseThrow()
        }catch (ex:Exception){}


        newUsuario = usuario

        /*
         La password del newUsuario debe estar hasheada, así que usamos el passwordEncoder que tenemos definido.
         */
        newUsuario.password = passwordEncoder.encode(newUsuario.password)
        // Guardamos el newUsuario en la base de datos

        usuarioRepository.save(newUsuario)


        // Devolvemos el Usuario insertado en la BDD
        return ResponseEntity(newUsuario, HttpStatus.CREATED)
    }

    fun getUsuarioByUser(username: String): Usuario{
        return usuarioRepository.findByUsername(username).get()
    }
    // Comprobamos que la contraseña del usuario encaja con nuestra logica de negocio
    fun checkUser(user: Usuario): Boolean {
        val regex = "^(?=.*\\d).{8,}$".toRegex()
        return user.password.matches(regex) && user.username.isNotBlank()
    }
}