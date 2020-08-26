package com.example.addressbook.dto

import com.example.addressbook.enum.PhoneType

data class PhoneDTO(
        val number: String,
        val type: PhoneType
)


