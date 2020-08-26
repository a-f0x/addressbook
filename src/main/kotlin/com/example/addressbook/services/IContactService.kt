package com.example.addressbook.services

import com.example.addressbook.dto.ContactDTO
import com.example.addressbook.dto.CreateContactDTO
import com.example.addressbook.dto.UpdateContactDTO
import com.example.addressbook.exceptions.ContactNotFoundException

interface IContactService {
    fun add(dto: CreateContactDTO): ContactDTO

    @Throws(ContactNotFoundException::class)
    fun update(dto: UpdateContactDTO): ContactDTO

    fun getAll(): List<ContactDTO>

    fun delete(ids: List<Int>)

    fun search(firstName: String, lastName: String?): List<ContactDTO>
}