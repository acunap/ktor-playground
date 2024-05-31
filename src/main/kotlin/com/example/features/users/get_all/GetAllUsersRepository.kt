package com.example.features.users.get_all

import com.example.features.users.User
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import java.util.*

class GetAllUsersRepository(private val jdbi: Jdbi) {
    fun getAll() {
        return jdbi.withHandleUnchecked { handle ->
            handle.createQuery(
                """
                    select id, name, age 
                    from users
                """.trimIndent()
            )
                .map { rs, _ ->
                    User(
                        id = UUID.fromString(rs.getString("id")),
                        name = rs.getString("name"),
                        age = rs.getInt("age")
                    )
                }
                .list()
        }
    }
}