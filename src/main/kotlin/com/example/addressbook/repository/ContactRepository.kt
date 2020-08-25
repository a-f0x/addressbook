package com.example.addressbook.repository

import com.example.addressbook.entity.ContactEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ContactRepository : JpaRepository<ContactEntity, Int>