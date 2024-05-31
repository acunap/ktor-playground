package com.example.features.users.create

import com.example.features.users.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.UUID

class CreateUserHandler(private val createUserRepository: CreateUserRepository) {
    suspend operator fun invoke(call: ApplicationCall) {
        val createUserRequest = call.receive<CreateUserRequest>()
        val newUser = User(
            id = UUID.randomUUID(),
            name = createUserRequest.name,
            age = createUserRequest.age
        )
        createUserRepository.create(newUser)
        call.respond(HttpStatusCode.Created, newUser)
    }
}

data class CreateUserRequest(val name: String, val age: Int)