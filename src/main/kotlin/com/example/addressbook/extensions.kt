package com.example.addressbook

import com.example.addressbook.dto.ErrorDTO
import com.example.addressbook.dto.ResponseDTO
import com.example.addressbook.exceptions.NotAcceptableDataException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException


fun <T : Any> createSuccessResponseEntity(data: T?): ResponseEntity<ResponseDTO<T>> = ResponseEntity.ok(
        ResponseDTO(
                data = data,
                errors = null
        )
)


fun NotAcceptableDataException.createValidationErrorResponseEntity(): ResponseEntity<ResponseDTO<Any>> {

    return ResponseEntity(
            ResponseDTO(
                    data = null,
                    errors = listOf(
                            ErrorDTO(
                                    code = ErrorDTO.ErrorCode.VALIDATION,
                                    message = message,
                                    details = details
                            )
                    )
            ),
            HttpStatus.UNPROCESSABLE_ENTITY
    )

}

fun HttpMessageNotReadableException.createValidationErrorResponseEntity(): ResponseEntity<Any> {
    return ResponseEntity(
            ResponseDTO(
                    data = null,
                    errors = listOf(
                            ErrorDTO(
                                    code = ErrorDTO.ErrorCode.VALIDATION,
                                    message = message!!,
                                    details = null
                            )
                    )
            ),
            HttpStatus.UNPROCESSABLE_ENTITY
    )

}


fun createInternalServerErrorResponseEntity(errorMessage: String): ResponseEntity<Any> = ResponseEntity(
        ResponseDTO<Any>(
                data = null,
                errors = listOf(
                        ErrorDTO(
                                code = ErrorDTO.ErrorCode.SERVER,
                                message = errorMessage,
                                details = null
                        )
                )
        ),
        HttpStatus.INTERNAL_SERVER_ERROR
)
