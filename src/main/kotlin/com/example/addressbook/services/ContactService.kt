package com.example.addressbook.services

import com.example.addressbook.dto.ContactDTO
import com.example.addressbook.dto.PhoneDTO
import com.example.addressbook.entity.ContactEntity
import com.example.addressbook.entity.PhoneEntity
import com.example.addressbook.entity.PhoneTypeEntity
import com.example.addressbook.enum.PhoneType
import com.example.addressbook.repository.ContactRepository
import com.example.addressbook.repository.PhoneTypeRepository
import org.springframework.stereotype.Service

@Service
class ContactService(
        private val phoneRepository: ContactRepository,
        phoneTypeRepository: PhoneTypeRepository
) : IContactService {

    private val typeCache: Map<PhoneType, PhoneTypeEntity> by lazy {
        phoneTypeRepository.findAll()
                .map {
                    it.type to it
                }.toMap()
    }

    override fun add(dto: ContactDTO): ContactDTO = dto.let {
        ContactEntity(
                it.firstName,
                it.lastName,
                it.address
        ).apply {
            phones = it.phones.map { phoneDto ->
                PhoneEntity(
                        phoneDto.number,
                        typeCache[phoneDto.type] ?: error("Invalid phone type"),
                        this
                )
            }.toMutableSet()

        }
    }.let {
        phoneRepository.save(it)
    }.let {
        ContactDTO(
                it.id,
                it.firstName,
                it.lastName,
                it.address,
                it.phones.map { phoneEntity ->
                    PhoneDTO(
                            phoneEntity.id,
                            phoneEntity.number,
                            phoneEntity.phoneType.type
                    )
                }.toList()
        )
    }

    override fun getAll(): List<ContactDTO> {
        return phoneRepository.findAll().map {
            ContactDTO(
                    it.id,
                    it.firstName,
                    it.lastName,
                    it.address,
                    it.phones.map { phoneEntity ->
                        PhoneDTO(
                                phoneEntity.id,
                                phoneEntity.number,
                                phoneEntity.phoneType.type
                        )
                    }.toList()
            )
        }
    }
}