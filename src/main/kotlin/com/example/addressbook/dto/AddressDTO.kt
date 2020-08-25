package com.example.addressbook.dto

data class AddressDTO(
        val id: Int?,
        val city: String,
        val street: String,
        val house: String,
        val flat: String
)