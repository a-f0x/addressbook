package com.example.addressbook.services

import com.example.addressbook.dto.PersonDTO
import com.example.addressbook.entity.PersonEntity
import com.example.addressbook.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(private val repository: PersonRepository) : IPersonService {

    override fun addPerson(dto: PersonDTO): PersonDTO = dto.let {
        PersonEntity(
                it.firstName,
                it.lastName,
                it.phone
        )
    }.let {
        repository.save(it)
    }.let {
        PersonDTO(
                it.id,
                it.firstName,
                it.lastName,
                it.phone
        )
    }

    override fun getAllPeople(): List<PersonDTO> {
        return repository.findAll().map {
            PersonDTO(
                    it.id,
                    it.firstName,
                    it.lastName,
                    it.phone
            )
        }
    }
}