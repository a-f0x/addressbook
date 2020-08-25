package com.example.addressbook.services

import com.example.addressbook.dto.AddressDTO
import com.example.addressbook.entity.AddressEntity
import com.example.addressbook.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressService(private val repository: AddressRepository) : IAddressService {

    override fun addAddress(dto: AddressDTO): AddressDTO = dto.let {

        AddressEntity(
                it.city,
                it.street,
                it.house,
                it.flat
        )
    }.let {
        repository.save(it)
    }.let {
        AddressDTO(
                it.id,
                it.city,
                it.street,
                it.house,
                it.flat
        )
    }

    override fun getAllAddresses(): List<AddressDTO> = repository.findAll().map {
        AddressDTO(
                it.id,
                it.city,
                it.street,
                it.house,
                it.flat
        )
    }
}