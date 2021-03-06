package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.extension.toResponse
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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
    fun findAll(@PageableDefault(page = 0, size = 10) pageable : Pageable) : Page<BookResponse> {
        return bookService.findAll(pageable).map { it.toResponse() }
    }

    @GetMapping
    @RequestMapping("/active")
    fun findActives(@PageableDefault(page = 0, size = 10) pageable : Pageable) : Page<BookResponse> =
        bookService.findByStatus(pageable).map { it.toResponse() }

    @GetMapping("/{id}")
    fun findById(@PathVariable id : Int) : BookResponse {
        return bookService.findById(id).toResponse()
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