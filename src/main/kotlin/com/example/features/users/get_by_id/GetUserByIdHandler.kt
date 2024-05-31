package com.example.features.users.get_by_id

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import java.util.*

class GetUserByIdHandler(private val getUserByIdRepository: GetUserByIdRepository) {
    suspend operator fun invoke(call: ApplicationCall) {
        val id = UUID.fromString(call.parameters["id"])
        val user = getUserByIdRepository.getById(id) ?: throw NotFoundException()
        call.respond(HttpStatusCode.OK, user)
    }
}