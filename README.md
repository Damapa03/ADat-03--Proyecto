# **Viajando Ando**

## **Idea del proyecto**
La idea del proyecto es la creación de una API que gestione las reservas de vuelos de una agencia de vuelos

## **Justificación del proyecto**
El proyecto busca crear una API que centralice la gestión de vuelos, usuarios y reservas, mejorando la eficiencia y experiencia del cliente.

## **Descripción de las tablas**

![Entidad relacion](Proyecto_API.png)

### **Tabla Usuario**
- Representa a los usuario en el sistema
- Propiedades:
  - `id`: Este campo es id único de cada usuario
  - `user`: Este campo es el nombre de usuario con el que se identificara el usuario
  - `password`: Este campo es la contraseña con la que se identificara el usuario
  - `rol`: Este campo indicará el rol que presenta el usuario dentro de la aplicación

```kotlin
data class Usuario(
    var id: Long? = null,
    var username: String,
    var password: String,
    var ROL: String
)
```

### **Tabla Reserva**
- Representa las reservas asociadas a un usuario y vuelo
- Propiedades:
  - `id`: Este campo es el identificador de la reserva
  - `usuario`: Este campo es el usuario al que le pertenece la reserva
  - `vuelo`: Este campo es el vuelo que está asociado a la reserva

```kotlin
data class Reserva(
    var id: Long? = null,
    var usuario: Usuario,
    var vuelo: Vuelo
)
```
### **Tabla Vuelo**
- Representa los vuelos que pueden ser reservados
- Propiedades:
  - `id`: Este campo es el identificador del vuelo
  - `destino`: Este campo indica el lugar de destino del vuelo
  - `plazas`: Este campo indica el número de plazas que quedan en el vuelo
  - `fechaSalida`: Este campo indica la fecha en la que el vuelo va a despegar
  - `horaSalida`: Este campo indica la hora en la que el vuelo va a despegar
  - `fechaLlegada`: Este campo indica la fecha en la que el vuelo va a aterrizar
  - `horaLlegada`: Este campo indica la hora en la que el vuelo va a aterrizar

```kotlin
data class Vuelo(
    var id: Long? = null,
    var destino: String,
    var plazas: Int,
    var fechaSalida: LocalDate,
    var horaSalida: LocalTime,
    var fechaLlegada: LocalDate,
    var horaLlegada: LocalTime
)
```
## **Endpoints para cada tabla**

### **Usuario**
- **POST** `/usuario/login` 
  - Lo usuarios acceden para iniciar sesión en el sistema
- **POST** `/usuario/register`  
  - Los usuarios accederán para registrarse en el sistema

### **Reserva**
- **GET** `/reserva`
  - Se obtendrán todas las reservas del sistema
- **GET** `/reserva/{idReserva}`
  - Se obtendrán las reservas con el mismo id
- **POST** `/reserva`
  - Se crearán nuevas reservas
- **PUT** `/reserva/{idReserva}`
  - Se modifica la reserva con el mismo id
- **DELETE** `/reserva/{idReserva}`
  - Se eliminará del sistema la reserva con el mismo id
### **Vuelo**
- **GET** `/vuelo`
  - Se obtendrán todos los vuelos del sistema
- **GET** `/vuelo/{idVuelo}`
  - Se obtendrán los vuelos con el mismo id
- **POST** `/vuelo`
  - Se crearán nuevos vuelos
- **PUT** `/vuelos/{idVuelos}`
  - Se modifica el vuelo con el mismo id
- **DELETE** `/vuelo/{idVuelo}`
  - Se eliminará del sistema el vuelo con el mismo id

## **Lógica de negocio**
Como lógica de negocio se van a seguir los siguientes puntos:
- Ninguno de los datos de los vuelos puede ser nulo
- Los vuelos que sean eliminados, eliminaran a su vez la reserva en el que esté asociado
- Las contraseñas de los usuarios han de ser mínimo de 8 caracteres y contener al menos un número

## **Excepciones y códigos de estado**
Las excepciones y códigos de estados son:
- `ValidationException` - 400 Bad Request
- `ResourceNotFoundException` - 404 Not Found

## **Restricciones de seguridad**

- Los usuarios no autenticados solo podrán acceder a las rutas de registro e inicio de sesión
- Los usuarios que estén registrados
  - Si su rol es user:
    - Podrá acceder solo a las reservas a las que esté asociado
    - Podrán crear reservas
    - Podrá modificar las reservas a las que esté asociado
    - Podrá eliminar las reservas a las que esté asociado
    - Podrá ver todos los vuelos existentes
  - Si su rol es admin:
    - Podrá ver todas las reservas
    - Podrá crear reservas
    - Podrá modificar cualquier reservas
    - Podrá eliminar cualquier reserva
    - Podrá ver todos los vuelos
    - Podrá crear vuelos
    - Podrá modificar cualquier vuelo
    - Podrá eliminar cualquier vuelo
