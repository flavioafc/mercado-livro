package com.mercadolivro.controller.response

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.CustomerModel
import java.math.BigDecimal

data class  BookResponse (
    var id : Int?,
    var name: String,
    var price: BigDecimal,
    var customer: CustomerModel?,
    var status: BookStatus?
)