package com.example.addressbook

import com.example.addressbook.dto.ErrorDTO
import com.example.addressbook.dto.ResponseDTO
import com.example.addressbook.exceptions.ContactNotFoundException
import com.example.addressbook.exceptions.NotAcceptableDataException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException


fun String?.isBlank(): Boolean {
    this ?: return false
    return this.isEmpty()
}

fun <T : Any> createSuccessResponseEntity(data: T?): ResponseEntity<ResponseDTO<T>> = ResponseEntity.ok(
        ResponseDTO(
                data = data,
                error = null
        )
)


fun NotAcceptableDataException.createValidationErrorResponseEntity(): ResponseEntity<ResponseDTO<Any>> {

    return ResponseEntity(
            ResponseDTO(
                    data = null,
                    error = ErrorDTO(
                            code = ErrorDTO.ErrorCode.VALIDATION,
                            message = message,
                            details = details
                    )
            ),
            HttpStatus.UNPROCESSABLE_ENTITY
    )

}

fun ContactNotFoundException.createNotFoundErrorResponseEntity(): ResponseEntity<ResponseDTO<Any>> {
    return ResponseEntity(
            ResponseDTO(
                    data = null,
                    error = ErrorDTO(
                            code = ErrorDTO.ErrorCode.NOT_FOUND,
                            message = message,
                            details = details
                    )
            ),
            HttpStatus.NOT_FOUND
    )
}

fun HttpMessageNotReadableException.createValidationErrorResponseEntity(): ResponseEntity<Any> {
    return ResponseEntity(
            ResponseDTO(
                    data = null,
                    error = ErrorDTO(
                            code = ErrorDTO.ErrorCode.VALIDATION,
                            message = message!!,
                            details = null
                    )
            ),
            HttpStatus.UNPROCESSABLE_ENTITY
    )

}


fun createInternalServerErrorResponseEntity(errorMessage: String): ResponseEntity<Any> = ResponseEntity(
        ResponseDTO<Any>(
                data = null,
                error = ErrorDTO(
                        code = ErrorDTO.ErrorCode.SERVER,
                        message = errorMessage,
                        details = null
                )
        ),
        HttpStatus.INTERNAL_SERVER_ERROR
)


inline fun <T> Iterable<T>.containsByPredicate(predicate: (T) -> Boolean): Boolean {
    if (this is Collection && isEmpty()) return false
    for (element in this) if (predicate(element)) return true
    return false
}
