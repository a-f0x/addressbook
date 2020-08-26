package com.example.addressbook.validators

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

abstract class AbstractValidator<A : Annotation, DTO : Any> : ConstraintValidator<A, DTO> {

    override fun isValid(value: DTO?, context: ConstraintValidatorContext?): Boolean {
        if (value == null)
            return false
        return validate(value)
    }

    protected fun errorsMap() = mutableMapOf<String, String>()

    abstract fun validate(dto: DTO): Boolean
}