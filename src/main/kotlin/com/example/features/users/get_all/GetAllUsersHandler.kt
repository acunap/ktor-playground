package com.example.features.users.get_all

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class GetAllUsersHandler(private val getAllUsersRepository: GetAllUsersRepository) {
    suspend operator fun invoke(call: ApplicationCall) {
        val users = getAllUsersRepository.getAll()
        call.respond(HttpStatusCode.OK, users)
    }
}
