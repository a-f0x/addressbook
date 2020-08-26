package com.example.addressbook.exceptions

class ContactNotFoundException(val details: Map<String, String>) : Exception() {
    override val message: String
        get() {
            return "Contact not found"
        }
}