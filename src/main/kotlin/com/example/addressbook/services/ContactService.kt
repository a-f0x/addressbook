package com.example.addressbook.services

import com.example.addressbook.containsByPredicate
import com.example.addressbook.dto.ContactDTO
import com.example.addressbook.dto.CreateContactDTO
import com.example.addressbook.dto.PhoneDTO
import com.example.addressbook.dto.UpdateContactDTO
import com.example.addressbook.entity.ContactEntity
import com.example.addressbook.entity.PhoneEntity
import com.example.addressbook.entity.PhoneTypeEntity
import com.example.addressbook.enum.PhoneType
import com.example.addressbook.exceptions.ContactNotFoundException
import com.example.addressbook.repository.ContactRepository
import com.example.addressbook.repository.PhoneRepository
import com.example.addressbook.repository.PhoneTypeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ContactService(
        private val contactRepository: ContactRepository,
        private val phoneRepository: PhoneRepository,
        phoneTypeRepository: PhoneTypeRepository
) : IContactService {

    private val typeCache: Map<PhoneType, PhoneTypeEntity> by lazy {
        phoneTypeRepository.findAll()
                .map {
                    it.type to it
                }.toMap()
    }

    @Transactional
    override fun add(dto: CreateContactDTO): ContactDTO = dto.let {
        ContactEntity(
                it.firstName,
                it.lastName,
                it.address
        ).apply {
            phones = mapPhoneDTO2Entity(dto.phones, this)

        }
    }.let {
        save(it)
    }

    @Throws(ContactNotFoundException::class)
    @Transactional
    override fun update(dto: UpdateContactDTO): ContactDTO {
        val old = contactRepository.findById(dto.id)
        if (old.isPresent.not())
            throw ContactNotFoundException(dto.id)
        return updateContact(old.get(), dto)

    }

    override fun getAll(): List<ContactDTO> = contactRepository.findAll().map {
        ContactDTO(
                it.id,
                it.firstName,
                it.lastName,
                it.address,
                mapPhoneEntity2Dto(it.phones)
        )
    }

    @Transactional
    override fun delete(ids: List<Int>) {
        contactRepository.deleteAllByIdIn(ids)
    }

    override fun search(firstName: String, lastName: String?): List<ContactDTO> {
        return if (lastName != null) contactRepository.findAllByLastNameAndFirstName(lastName, firstName).map {
            ContactDTO(
                    it.id,
                    it.firstName,
                    it.lastName,
                    it.address,
                    mapPhoneEntity2Dto(it.phones)
            )
        } else
            contactRepository.findAllByFirstName(firstName).map {
                ContactDTO(
                        it.id,
                        it.firstName,
                        it.lastName,
                        it.address,
                        mapPhoneEntity2Dto(it.phones)
                )
            }
    }

    private fun save(entity: ContactEntity): ContactDTO = contactRepository.save(entity).let {
        ContactDTO(
                it.id,
                it.firstName,
                it.lastName,
                it.address,
                mapPhoneEntity2Dto(it.phones)
        )
    }

    private fun updateContact(old: ContactEntity, new: UpdateContactDTO): ContactDTO {

        val forRemoving = old.phones.filterNot { pe ->
            new.phones.containsByPredicate { it.number == pe.number }
        }

        val remainingPhones = (old.phones - forRemoving).toMutableSet().apply {
            addAll(
                    mapPhoneDTO2Entity(new.phones, old)
            )
        }

        old.apply {
            firstName = new.firstName
            lastName = new.lastName
            address = new.address
            phones = remainingPhones
        }

        val entity = save(old)
        phoneRepository.deleteAll(forRemoving)
        return entity
    }

    private fun mapPhoneDTO2Entity(dtos: List<PhoneDTO>, contactEntity: ContactEntity): MutableSet<PhoneEntity> =
            dtos.map { phoneDto ->
                PhoneEntity(
                        phoneDto.number,
                        typeCache[phoneDto.type] ?: error("Invalid phone type"),
                        contactEntity
                )
            }.toMutableSet()

    private fun mapPhoneEntity2Dto(entities: Set<PhoneEntity>): List<PhoneDTO> = entities.map { phoneEntity ->
        PhoneDTO(
                phoneEntity.id,
                phoneEntity.number,
                phoneEntity.phoneType.type
        )
    }.toList()
}

