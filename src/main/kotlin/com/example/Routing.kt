package com.example

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/users") {
            get(getAllUsersHandler())
            get("{id}", getUserByIdHandler())
            post(createUserHandler())
        }
    }
}