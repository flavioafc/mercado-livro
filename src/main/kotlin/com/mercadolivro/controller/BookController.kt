package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.model.BookModel
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("book")
class BookController(
    var bookService: BookService,
    var customerService: CustomerService
)
{
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request : PostBookRequest){
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun findAll() : List<BookModel> {
        return bookService.findAll()
    }

    @GetMapping
    @RequestMapping("/active")
    fun findActives() : List<BookModel> = bookService.findByStatus()

    @GetMapping("/{id}")
    fun findById(@PathVariable id : Int) : BookModel {
        return bookService.findById(id)
    }

    @DeleteMapping("/{id}")
    fun cancel(@PathVariable id : Int) {
        bookService.cancel(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id : Int, @RequestBody book : PutBookRequest) {
        var  bookSaved = bookService.findById(id)
        bookService.update(book.toBookModel(bookSaved))
    }
}