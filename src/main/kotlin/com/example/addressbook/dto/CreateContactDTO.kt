package com.example.addressbook.dto

import com.example.addressbook.validators.CorrectCreateContact

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