package com.example.addressbook.controllers

import com.example.addressbook.createInternalServerErrorResponseEntity
import com.example.addressbook.createValidationErrorResponseEntity
import com.example.addressbook.dto.ResponseDTO
import com.example.addressbook.exceptions.NotAcceptableDataException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class MvcExceptionHandler : ResponseEntityExceptionHandler() {

    companion object {
        private const val INTERNAL_SERVER_ERROR = "Internal server error"
    }

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> =
            ex.createValidationErrorResponseEntity()

    @ExceptionHandler(NotAcceptableDataException::class)
    fun handleNotAcceptableDataException(ex: NotAcceptableDataException): ResponseEntity<ResponseDTO<Any>> {
        logger.error("Validation error.", ex)
        return ex.createValidationErrorResponseEntity()
    }

    @ExceptionHandler(Throwable::class)
    fun handleThrowable(t: Throwable): ResponseEntity<*> {
        logger.error(INTERNAL_SERVER_ERROR, t)
        return createInternalServerErrorResponseEntity("$INTERNAL_SERVER_ERROR. Error: ${t.message ?: "Unknown error"}")
    }

}