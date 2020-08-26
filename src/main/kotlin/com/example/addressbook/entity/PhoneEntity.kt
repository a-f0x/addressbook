package com.example.addressbook.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "phones")
open class PhoneEntity() {
    constructor(
            number: String,
            phoneType: PhoneTypeEntity,
            contact: ContactEntity
    ) : this() {
        this.number = number
        this.phoneType = phoneType
        this.contact = contact

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0

    @Column(name = "number", length = 11, nullable = false, unique = true)
    open lateinit var number: String

    @OneToOne
    open lateinit var phoneType: PhoneTypeEntity

    @ManyToOne
    @JsonBackReference
    open lateinit var contact: ContactEntity

}