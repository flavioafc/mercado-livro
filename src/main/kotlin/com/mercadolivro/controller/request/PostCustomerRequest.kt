package com.mercadolivro.controller

import com.mercadolivro.model.CustomerModel

data class PostCustomerRequest (
    var name: String,
    var email: String
)