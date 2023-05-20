package me.dio.credit.application.system.dto

import me.dio.credit.application.system.entity.Credit
import java.math.BigDecimal
import java.util.UUID

data class CreditViewList(
    val creditCode: UUID,
    val creditVaule: BigDecimal,
    val numberOfInstallments: Int
) {
    constructor(credit:Credit): this(
        creditCode = credit.creditCode,
        creditVaule = credit.creditVaule,
        numberOfInstallments = credit.numberOfInstallments
    )

}
