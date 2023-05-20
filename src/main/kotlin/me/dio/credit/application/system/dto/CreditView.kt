package me.dio.credit.application.system.dto


import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.enummeration.Status
import java.math.BigDecimal
import java.util.UUID

data class CreditView(
    val creditCode: UUID,
    val creditVaule: BigDecimal,
    val numberOfInstallment: Int,
    val status: Status,
    val emailCustomer: String?,
    val incomeCustomer: BigDecimal?
) {

    constructor(credit: Credit): this(
        creditCode = credit.creditCode,
        creditVaule = credit.creditVaule,
        numberOfInstallment = credit.numberOfInstallments,
        status = credit.status,
        emailCustomer = credit.customer?.email,
        incomeCustomer = credit.customer?.income
    )
}
