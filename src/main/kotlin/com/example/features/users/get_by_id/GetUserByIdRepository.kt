package com.example.features.users.get_by_id

import com.example.features.users.User
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import java.util.*

class GetUserByIdRepository(private val jdbi: Jdbi) {
    fun getById(id: UUID): User? {
        return jdbi.withHandleUnchecked { handle ->
            handle.createQuery(
                """
                    select id, name, age
                    from users
                    where id = :id
                """.trimIndent()
            )
                .bind("id", id)
                .map { rs, _ ->
                    User(
                        id = UUID.fromString(rs.getString("id")),
                        name = rs.getString("name"),
                        age = rs.getInt("age")
                    )
                }
                .singleOrNull()
        }
    }
}