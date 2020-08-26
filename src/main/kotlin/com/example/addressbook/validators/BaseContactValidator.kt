package com.example.addressbook.validators

import com.example.addressbook.dto.ContactDTO
import com.example.addressbook.entity.PhoneEntity
import com.example.addressbook.exceptions.PhoneValidationException
import com.example.addressbook.isBlank
import com.example.addressbook.repository.PhoneRepository

abstract class BaseContactValidator<A : Annotation, DTO : ContactDTO>(
        private val repo: PhoneRepository,
        private val phoneValidator: IPhoneValidator
) : AbstractValidator<A, DTO>() {

    protected fun validateContact(dto: DTO): Boolean {

        val errors = errorsMap()

        if (dto.firstName.isEmpty())
            errors["first_name"] = SHOULD_BE_NOT_EMPTY_MESSAGE

        if (dto.lastName.isBlank())
            errors["last_name"] = SHOULD_BE_NOT_EMPTY_MESSAGE

        if (dto.address.isBlank())
            errors["address"] = SHOULD_BE_NOT_EMPTY_MESSAGE

        if (dto.phones.isEmpty()) {
            errors["phones"] = SHOULD_BE_NOT_EMPTY_MESSAGE
            return successResultOrException(errors, dto)
        }

        val nonUnique = dto.phones.groupBy { it.number }
                .filter { it.value.size > 1 }
                .values
                .flatten()
                .distinctBy { it.number }

        if (nonUnique.isNotEmpty()) {
            errors["phones"] = nonUnique.map {
                it.number to "Non unique"
            }.toMap()
            return successResultOrException(errors, dto)
        }

        val invalidPhoneErrors = errorsMap()

        dto.phones.forEach {
            try {
                phoneValidator.validatePhone(it.number)
            } catch (pe: PhoneValidationException) {
                invalidPhoneErrors[it.number] = pe.message
            }
        }
        if (invalidPhoneErrors.isNotEmpty()) {
            errors["phones"] = invalidPhoneErrors
            return successResultOrException(errors, dto)
        }

        return successResultOrException(errors, dto)
    }

    protected fun getPhonesByNumbers(numbers: List<String>): List<PhoneEntity> {
        return repo.findAllByNumberIn(numbers)
    }


    protected fun createNotHisPhonesError(phones: List<PhoneEntity>): MutableMap<String, Any> {
        return errorsMap().apply {
            put("phones", phones.map {
                it.number to "Belongs to contactId ${it.contact.id}"
            }.toMap())
        }
    }

}