package cn.omsfuk.library.web.service.impl;

import cn.omsfuk.library.web.dao.BookDAO;
import cn.omsfuk.library.web.dao.BookESDAO;
import cn.omsfuk.library.web.dao.BorrowDAO;
import cn.omsfuk.library.web.model.Book;
import cn.omsfuk.library.web.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private BookESDAO bookESDAO;

    @Autowired
    private BorrowDAO borrowDAO;

    @Override
    public Book selectBook(int id) {
        return bookDAO.selectBookById(id);
    }

    @Override
    public List<Book> listBookByTitle(String title, PageRequest page) {
        return bookESDAO.queryBooksByTitle(title, page);
    }

    @Override
    @Transactional
    public Integer newBook(Book book) {
        // TODO elasticsearch
        Integer res = bookDAO.insertBook(book);
        book.setId(bookDAO.lastInsertId());
        bookESDAO.save(book);
        return res;
    }

    @Override
    public List<Book> recentBook() {
        return bookDAO.listRecentBook();
    }
}
