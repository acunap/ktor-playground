package com.example

import com.example.common.createJdbi
import com.example.features.users.create.CreateUserHandler
import com.example.features.users.create.CreateUserRepository
import com.example.features.users.get_all.GetAllUsersHandler
import com.example.features.users.get_all.GetAllUsersRepository
import com.example.features.users.get_by_id.GetUserByIdHandler
import com.example.features.users.get_by_id.GetUserByIdRepository
import io.ktor.server.application.*
import io.ktor.util.pipeline.*

fun getAllUsersHandler(): suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit {
    return {
        val getAllUsersRepository = GetAllUsersRepository(createJdbi())
        val getAllUsersHandler = GetAllUsersHandler(getAllUsersRepository)
        getAllUsersHandler(call)
    }
}

fun getUserByIdHandler(): suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit {
    return {
        val getUserByIdRepository = GetUserByIdRepository(createJdbi())
        val getUserByIdHandler = GetUserByIdHandler(getUserByIdRepository)
        getUserByIdHandler(call)
    }
}

fun createUserHandler(): suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit {
    return {
        val createUserRepository = CreateUserRepository(createJdbi())
        val createUserHandler = CreateUserHandler(createUserRepository)
        createUserHandler(call)
    }
}