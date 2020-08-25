package com.example.addressbook.services

import com.example.addressbook.dto.PersonDTO

interface IPersonService {
    fun addPerson(dto: PersonDTO): PersonDTO
    fun getAllPeople(): List<PersonDTO>
}