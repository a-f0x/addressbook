package com.example.addressbook.entity

import javax.persistence.*

@Entity
@Table(name = "people")
open class AddressEntity() {
    constructor(city: String, street: String, house: String, flat: String) : this() {
        this.city = city
        this.street = street
        this.house = house
        this.flat = flat
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0

    @Column(name = "city", length = 255, nullable = false)
    open lateinit var city: String

    @Column(name = "street", length = 255, nullable = false)
    open lateinit var street: String

    @Column(name = "house", length = 10, nullable = false)
    open lateinit var house: String

    @Column(name = "flat", length = 255, nullable = false)
    open lateinit var flat: String

}