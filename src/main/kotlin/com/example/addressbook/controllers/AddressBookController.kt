package com.example.addressbook.controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/v1/address"])
class AddressBookController() {

    @PostMapping(produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun add
}