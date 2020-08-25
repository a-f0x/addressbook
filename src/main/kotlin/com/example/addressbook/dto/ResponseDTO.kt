package com.example.addressbook.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ResponseDTO<T : Any>(
        val data: T?,
)