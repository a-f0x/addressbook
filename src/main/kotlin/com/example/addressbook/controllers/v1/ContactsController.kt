package com.example.addressbook.controllers.v1

import com.example.addressbook.dto.ContactDTO
import com.example.addressbook.dto.ResponseDTO
import com.example.addressbook.services.IContactService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/v1/contacts"])
class ContactsController(private val service: IContactService) {

    @PostMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)], consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun add(@RequestBody contact: ContactDTO): ResponseEntity<ResponseDTO<ContactDTO>> {
        return ResponseEntity.ok(ResponseDTO(service.add(contact)))
    }

    @GetMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getAllContacts(): ResponseEntity<ResponseDTO<List<ContactDTO>>> {
        return ResponseEntity.ok(ResponseDTO(service.getAll()))
    }

}