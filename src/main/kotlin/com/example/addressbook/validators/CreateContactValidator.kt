package com.example.addressbook.validators

import com.example.addressbook.dto.ContactDTO
import com.example.addressbook.dto.CreateContactDTO
import com.example.addressbook.entity.PhoneEntity
import com.example.addressbook.exceptions.NotAcceptableDataException
import com.example.addressbook.repository.PhoneRepository
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [CreateContactValidator::class])
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CorrectCreateContact(
        val message: String = "Invalid value",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [])

class CreateContactValidator(private val repo: PhoneRepository) : ConstraintValidator<CorrectCreateContact, CreateContactDTO> {

    override fun isValid(value: CreateContactDTO, context: ConstraintValidatorContext?): Boolean {
        val phones = repo.findAllByNumberIn(value.phones.map { it.number })

        if (phones.isEmpty())
            return true
        throw buildException(value, phones)

    }

    private fun buildException(value: ContactDTO, phones: List<PhoneEntity>): NotAcceptableDataException {

        val errors = mutableMapOf<String, String>()
        phones.forEach { p ->
            errors[p.number] = "Belongs to contactId ${p.contact.id}"
        }
        return NotAcceptableDataException(value, errors)
    }
}