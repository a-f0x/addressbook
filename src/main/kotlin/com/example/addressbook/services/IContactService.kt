package com.example.addressbook.services

import com.example.addressbook.dto.ContactDTO

interface IContactService {
    fun add(dto: ContactDTO): ContactDTO
    fun getAll(): List<ContactDTO>
}