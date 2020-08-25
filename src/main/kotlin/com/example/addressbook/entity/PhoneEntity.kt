package com.example.addressbook.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "phones")
open class PhoneEntity() {
    constructor(
            phone: String,
            phoneType: PhoneTypeEntity,
            contact: ContactEntity
    ) : this() {
        this.phone = phone
        this.phoneType = phoneType
        this.contact = contact

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0

    @Column(name = "phone", length = 11, nullable = false, unique = true)
    open lateinit var phone: String

    @OneToOne
    open lateinit var phoneType: PhoneTypeEntity

    @ManyToOne
    @JsonBackReference
    open lateinit var contact: ContactEntity

}