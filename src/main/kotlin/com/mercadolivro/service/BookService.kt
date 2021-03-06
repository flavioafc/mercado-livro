package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService (var bookRepository: BookRepository){
    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun  findAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun findByStatus(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun findById(id : Int): BookModel {
        return bookRepository.findById(id).orElseThrow()
    }

    fun cancel(id: Int) {
        var book = findById(id)
        book.status = BookStatus.CANCELADO

        update(book)
    }

    fun update(book: BookModel) {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
       var books = bookRepository.findByCustomer(customer)
        for (book in books){
            book.status = BookStatus.DELETADO
        }

        bookRepository.saveAll(books)
    }


}
