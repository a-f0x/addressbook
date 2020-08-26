package com.example.addressbook.validators

import com.example.addressbook.dto.UpdateContactDTO
import com.example.addressbook.exceptions.ContactNotFoundException
import com.example.addressbook.repository.ContactRepository
import com.example.addressbook.repository.PhoneRepository
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [CreateContactValidator::class])
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CorrectUpdateContact(
        val message: String = "Invalid value",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [])

class UpdateContactValidator(
        private val contactRepository: ContactRepository,
        private val phoneRepository: PhoneRepository

) : ConstraintValidator<CorrectUpdateContact, UpdateContactDTO> {

    override fun isValid(value: UpdateContactDTO, context: ConstraintValidatorContext?): Boolean {
        val exist = contactRepository.existsById(value.id)
        if (exist.not())
            throw ContactNotFoundException()

    }
}