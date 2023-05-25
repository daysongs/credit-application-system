package me.dio.credit.application.system.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import me.dio.credit.application.system.entity.Address
import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.entity.Customer
import me.dio.credit.application.system.repository.CreditRepository
import me.dio.credit.application.system.service.impl.CreditService
import me.dio.credit.application.system.service.impl.CustomerService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CreditServiceTest {
    @MockK lateinit var creditRepository: CreditRepository
    @InjectMockKs lateinit var creditService: CreditService

    @Test
    fun `should save credit`(){
        //given
        val customer:Customer = buildCustomer()
        val fakeCredit: Credit = buildCredit(customer = customer)
        every { creditRepository.save(any()) } returns fakeCredit
        //when
        val atual: Credit = creditService.save(fakeCredit)
        //then
        Assertions.assertThat(atual).isNotNull


    }
    @Test
    fun `should find credit by credit code`() {
        //given
        val customer:Customer = buildCustomer()
        val credit1: Credit = buildCredit(customer=customer)
        val credit2: Credit = buildCredit(customer=customer)

        val creditCode1 = UUID.fromString("aa547c0f-9a6a-451f-8c89-afddce916a29")
        val creditCode2 = UUID.fromString("49f740be-46a7-449b-84e7-ff5b7986d7ef")
        credit1.creditCode = creditCode1
        credit2.creditCode = creditCode2
        //when
        val fakeCredit1: Credit = creditRepository.findByCreditCode(creditCode1)!!
        val fakeCredit2: Credit = creditRepository.findByCreditCode(creditCode2)!!
        //then
        Assertions.assertThat(fakeCredit1).isNotNull
        Assertions.assertThat(fakeCredit2).isNotNull
        Assertions.assertThat(fakeCredit1).isSameAs(credit1)
        Assertions.assertThat(fakeCredit2).isSameAs(credit2)
    }
    @Test
    fun `should find all by customer id`(){
        //given
        val customer:Customer = buildCustomer()
        val credit1: Credit = buildCredit(customer=customer)
        val credit2: Credit = buildCredit(customer=customer)
        val customerId: Long = 1L
        //when
        val crediList: List<Credit> = creditRepository.findAllByCustomerId(customerId)

        //then
        Assertions.assertThat(crediList).isNotEmpty
        Assertions.assertThat(crediList.size).isEqualTo(2)
        Assertions.assertThat(crediList).contains(credit1,credit2)
    }
        private fun buildCredit(
            creditValue: BigDecimal = BigDecimal.valueOf(500.0),
            dayFirstInstallment: LocalDate = LocalDate.of(2023, Month.JULY, 22),
            numberOfInstallments: Int = 5,
            customer: Customer
        ): Credit = Credit(
            creditVaule = creditValue,
            dayFristInstallment = dayFirstInstallment,
            numberOfInstallments = numberOfInstallments,
            customer = customer
        )

        private fun buildCustomer(
            firstName: String = "Cami",
            lastName: String = "Cavalcante",
            cpf: String = "28475934625",
            email: String = "camila@gmail.com",
            password: String = "12345",
            zipCode: String = "12345",
            street: String = "Rua da Cami",
            income: BigDecimal = BigDecimal.valueOf(1000.0),
        ) = Customer(
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            password = password,
            address = Address(
                zipCode = zipCode,
                street = street,
            ),
            income = income,
        )
    }