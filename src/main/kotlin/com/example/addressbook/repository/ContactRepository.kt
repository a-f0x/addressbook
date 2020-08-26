package com.example.addressbook.repository

import com.example.addressbook.entity.ContactEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ContactRepository : JpaRepository<ContactEntity, Int> {
    fun deleteAllByIdIn(ids: List<Int>)

    @Query("SELECT c from ContactEntity as c WHERE LOWER(c.firstName) LIKE LOWER (CONCAT ('%',:f_name,'%')) AND LOWER(c.lastName) LIKE LOWER (CONCAT ('%',:l_name,'%'))")
    fun findAllByLastNameAndFirstName(
            @Param("l_name") lastName: String,
            @Param("f_name") firstName: String
    ): List<ContactEntity>

    @Query("SELECT c from ContactEntity as c WHERE LOWER(c.firstName) LIKE LOWER (CONCAT ('%',:f_name,'%')) ")
    fun findAllByFirstName(
            @Param("f_name") firstName: String
    ): List<ContactEntity>

}