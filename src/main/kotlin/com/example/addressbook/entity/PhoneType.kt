package com.example.addressbook.entity

import com.example.addressbook.enum.PhoneType
import javax.persistence.*

@Entity
@Table(name = "phone_types")
open class PhoneTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int = 0

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 64, unique = true)
    open var type: PhoneType = PhoneType.MOBILE

}