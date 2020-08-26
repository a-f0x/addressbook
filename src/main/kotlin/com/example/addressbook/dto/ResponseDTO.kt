package com.example.addressbook.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class ResponseDTO<T : Any>(
        val data: T?,
        val errors: List<ErrorDTO>?
)

@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorDTO(
        val code: ErrorCode,
        val message: String,
        val details: Map<String, Any>?
) {

    enum class ErrorCode {
        @JsonProperty("validation")
        VALIDATION,

        @JsonProperty("server")
        SERVER
    }

}