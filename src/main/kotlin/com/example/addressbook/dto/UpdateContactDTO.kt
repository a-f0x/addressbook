package com.example.addressbook.dto

import com.example.addressbook.validators.CorrectUpdateContact

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
