package com.example.addressbook.exceptions

class ContactNotFoundException(val details: Map<String, Any>) : Exception() {
    override val message: String
        get() {
            return "Contact not found"
        }
}