package com.example.addressbook.entity

import javax.persistence.*


@Entity
@Table(name = "people")
open class PersonEntity() {

    constructor(firstName: String, lastName: String, phone: String?) : this() {
        this.firstName = firstName
        this.lastName = lastName
        this.phone = phone
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0

    @Column(name = "first_name", length = 255, nullable = false)
    open lateinit var firstName: String


    @Column(name = "last_name", length = 255, nullable = false)
    open lateinit var lastName: String

    @Column(name = "phone", length = 11)
    open var phone: String? = null
}