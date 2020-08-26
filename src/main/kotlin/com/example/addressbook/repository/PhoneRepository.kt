package com.example.addressbook.repository

import com.example.addressbook.entity.PhoneEntity
import org.springframework.data.repository.CrudRepository

interface PhoneRepository : CrudRepository<PhoneEntity, Int> {
    fun findAllByNumberIn(numbers: List<String>): List<PhoneEntity>
}
