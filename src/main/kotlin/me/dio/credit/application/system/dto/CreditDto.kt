package me.dio.credit.application.system.dto

import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    val creditVaule: BigDecimal,
    val dayFirstOfIstallment: LocalDate,
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
