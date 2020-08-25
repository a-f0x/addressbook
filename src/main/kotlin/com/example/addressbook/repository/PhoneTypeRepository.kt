package com.example.addressbook.repository

import com.example.addressbook.entity.PhoneTypeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PhoneTypeRepository : JpaRepository<PhoneTypeEntity, Int>