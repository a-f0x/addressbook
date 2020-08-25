package com.example.addressbook.services

import com.example.addressbook.dto.AddressDTO

interface IAddressService {

    fun addAddress(dto: AddressDTO): AddressDTO

    fun getAllAddresses(): List<AddressDTO>

}