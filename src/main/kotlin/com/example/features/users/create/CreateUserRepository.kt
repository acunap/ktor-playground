package com.example.features.users.create

import com.example.features.users.User
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked

class CreateUserRepository(private val jdbi: Jdbi) {
    fun create(user: User) {
        jdbi.withHandleUnchecked { handle ->
            handle.createUpdate(
                """
                    insert into users(id, name, age)
                    values (:id, :name, :age)
                """.trimIndent()
            )
                .bind("id", user.id)
                .bind("name", user.name)
                .bind("age", user.age)
                .execute()
        }
    }
}