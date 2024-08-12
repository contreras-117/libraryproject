package com.libraryproject.springbootlibrary.controller.model;

import com.libraryproject.springbootlibrary.entity.Book;
import lombok.Data;

@Data
public class ShelfCurrentLoansResponse {
    private Book book;
    private int daysLeft;

    public ShelfCurrentLoansResponse(Book book, int daysLeft) {
        this.book = book;
        this.daysLeft = daysLeft;
    }
}
