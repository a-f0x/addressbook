package com.example.addressbook.repository

import com.example.addressbook.entity.PersonEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<PersonEntity, Int>