package cn.omsfuk.library.web.service;

import cn.omsfuk.library.web.model.Book;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BookService {

    Book selectBook(int id);

    List<Book> listBookByTitle(String title, PageRequest page);

    Integer newBook(Book book);

    List<Book> recentBook();
}
