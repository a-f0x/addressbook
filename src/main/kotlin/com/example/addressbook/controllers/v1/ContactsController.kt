package com.example.addressbook.controllers.v1

import com.example.addressbook.createSuccessResponseEntity
import com.example.addressbook.dto.ContactDTO
import com.example.addressbook.dto.CreateContactDTO
import com.example.addressbook.dto.ResponseDTO
import com.example.addressbook.dto.UpdateContactDTO
import com.example.addressbook.services.IContactService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/v1/contacts"])
class ContactsController(private val service: IContactService) {

    @PostMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)], consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun add(@RequestBody @Valid contact: CreateContactDTO): ResponseEntity<ResponseDTO<ContactDTO>> =
            createSuccessResponseEntity(service.add(contact))

    @GetMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getAllContacts(): ResponseEntity<ResponseDTO<List<ContactDTO>>> = createSuccessResponseEntity(service.getAll())

    @PutMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)], consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun updateContact(
            @RequestBody @Valid contact: UpdateContactDTO): ResponseEntity<ResponseDTO<ContactDTO>> =
            createSuccessResponseEntity(service.update(contact))

    @DeleteMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)], consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun deleteContacts(@RequestBody ids: List<Int>): ResponseEntity<ResponseDTO<String>> {
        service.delete(ids)
        return createSuccessResponseEntity("success")
    }

    @GetMapping(path = ["/search"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun searchContacts(@RequestParam("query") query: String): ResponseEntity<ResponseDTO<List<ContactDTO>>> =
            createSuccessResponseEntity(service.search(query))

}