package me.dio.credit.application.system.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.credit.application.system.entity.Customer
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field:NotEmpty val firstName: String,
    @field:NotEmpty val lastName: String,
    @field:NotNull(message = "invalid input") val income: BigDecimal,
    @field:NotEmpty val zipCode: String,
    @field:NotEmpty val street: String
) {

    fun toEtity(customer: Customer): Customer{
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.street = this.street
        customer.address.zipCode = this.zipCode
        return  customer

    }
}
