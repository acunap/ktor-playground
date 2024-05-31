package com.example.common

import io.ktor.server.application.*
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.jdbi.v3.core.statement.Slf4JSqlLogger

fun createJdbi(): Jdbi {
    val jdbi = Jdbi.create("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;")
        .setSqlLogger(Slf4JSqlLogger())

    jdbi.withHandleUnchecked { handle ->
        handle.createUpdate(
            """
                create table if not exists users(
                    id uuid primary key,
                    name varchar(255) not null,
                    age integer not null
                )
            """.trimIndent()
        )
            .execute()
    }

    return jdbi
}