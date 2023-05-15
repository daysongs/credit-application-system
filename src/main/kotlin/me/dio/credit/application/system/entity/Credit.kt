package me.dio.credit.application.system.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
import me.dio.credit.application.system.enummeration.Status
@Entity
@Table(name="Credit")
data class Credit(
    @Column(nullable = false, unique = true) val creditCode: UUID = UUID.randomUUID(),
    @Column(nullable = false) val creditVaule: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) val dayFristInstallment: LocalDate,
    @Column(nullable = false) val numberOfInstallments: Int = 0,
    @Enumerated val status: Status = Status.IN_PROGRESS,
    @ManyToOne val customer: Customer? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null


)
