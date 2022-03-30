package com.mercadolivro.extension

import com.mercadolivro.controller.PostCustomerRequest
import com.mercadolivro.controller.PutCustomerRequest
import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import java.awt.print.Book

fun PostCustomerRequest.toCustomerModel() : CustomerModel {
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(previewsValue: CustomerModel) : CustomerModel {
    return CustomerModel(id = previewsValue.id, name = this.name, email = this.email, status = previewsValue.status)
}

fun PostBookRequest.toBookModel(customer: CustomerModel) : BookModel {
   var book = BookModel(
        name = this.name,
        price = this.price,
        customer = customer
    )
    book.status = BookStatus.ATIVO

    return book

}

fun PutBookRequest.toBookModel(previewsValue: BookModel) : BookModel {
    return BookModel(
        id = previewsValue.id,
        name = this.name ?: previewsValue.name,
        price = this.price ?: previewsValue.price,
        customer = previewsValue.customer
    )
}