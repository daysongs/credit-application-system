package me.dio.credit.application.system.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "invalid input") val creditVaule: BigDecimal,
    @field:Future val dayFirstOfIstallment: LocalDate,
    val numberOFInstallments: Int,
    val customerId: Long
) {

    fun toEntity(): Credit = Credit(
        creditVaule = this.creditVaule,
        dayFristInstallment = this.dayFirstOfIstallment,
        numberOfInstallments = this.numberOFInstallments,
        customer = Customer(id = this.customerId)

    )

}
