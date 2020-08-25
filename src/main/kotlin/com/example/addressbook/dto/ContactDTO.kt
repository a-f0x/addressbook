package com.example.addressbook.dto

data class ContactDTO(
        val id: Int?,
        val firstName: String,
        val lastName: String?,
        val address: String?,
        val phones: List<PhoneDTO>
)