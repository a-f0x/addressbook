package com.example.addressbook

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class AddressBookApplication : CommandLineRunner {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(AddressBookApplication::class.java, *args)
		}
	}

	override fun run(vararg args: String?) {
	}
}
