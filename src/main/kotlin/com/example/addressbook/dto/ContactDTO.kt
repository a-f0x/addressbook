package com.example.addressbook.dto

import com.example.addressbook.validators.CorrectCreateContact
import com.example.addressbook.validators.CorrectUpdateContact


open class ContactDTO(
        open val id: Int?,
        val firstName: String,
        val lastName: String?,
        val address: String?,
        val phones: List<PhoneDTO>
)

@CorrectCreateContact
class CreateContactDTO(
        firstName: String,
        lastName: String?,
        address: String?,
        phones: List<PhoneDTO>
) : ContactDTO(
        null,
        firstName,
        lastName,
        address,
        phones

)

@CorrectUpdateContact
class UpdateContactDTO(
        override val id: Int,
        firstName: String,
        lastName: String?,
        address: String?,
        phones: List<PhoneDTO>
) : ContactDTO(
        id,
        firstName,
        lastName,
        address,
        phones

)



