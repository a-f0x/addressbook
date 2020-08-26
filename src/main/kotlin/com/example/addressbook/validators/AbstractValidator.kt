package com.example.addressbook.validators

import com.example.addressbook.exceptions.NotAcceptableDataException
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
/**
 * Валидация объекта целеком а не по полям используется потому что баг https://youtrack.jetbrains.com/issue/KT-13228
 * */
abstract class AbstractValidator<A : Annotation, DTO : Any> : ConstraintValidator<A, DTO> {
    companion object {
        const val SHOULD_BE_NOT_EMPTY_MESSAGE = "should be not empty"
    }

    override fun isValid(value: DTO?, context: ConstraintValidatorContext?): Boolean {
        if (value == null)
            return false
        return validate(value)
    }

    protected fun errorsMap() = mutableMapOf<String, Any>()

    @Throws(NotAcceptableDataException::class)
    protected open fun successResultOrException(errors: Map<String, Any>, dto: DTO): Boolean {
        if (errors.isEmpty())
            return true
        throw NotAcceptableDataException(dto, errors)
    }

    abstract fun validate(dto: DTO): Boolean
}