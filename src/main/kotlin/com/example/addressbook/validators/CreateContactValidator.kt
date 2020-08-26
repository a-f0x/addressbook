package com.example.addressbook.validators

import com.example.addressbook.dto.CreateContactDTO
import com.example.addressbook.repository.PhoneRepository
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [CreateContactValidator::class])
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CorrectCreateContact(
        val message: String = "Invalid value",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [])

class CreateContactValidator(repo: PhoneRepository,
                             phoneValidator: IPhoneValidator
) : BaseContactValidator<CorrectCreateContact, CreateContactDTO>(repo, phoneValidator) {


    override fun validate(dto: CreateContactDTO): Boolean {
        validateContact(dto)

        val phones = getPhonesByNumbers(dto.phones.map { it.number })

        if (phones.isNotEmpty()) {
            return successResultOrException(
                    createNotHisPhonesError(phones),
                    dto
            )
        }

        return true
    }
}