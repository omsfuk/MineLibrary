package cn.omsfuk.library.web.service;

import cn.omsfuk.library.web.model.Book;
import cn.omsfuk.library.web.model.Borrow;

import java.util.List;
import java.util.Map;

public interface BorrowService {

    List<Book> listBorrow(int page);

    Integer returnBook(List<Integer> id);

    Integer borrowBook(List<Integer> id);

    Boolean renewBorrow(Integer id);

    /**
     * 获得借阅信息概览。包括当前借阅数量，即将超期数量
     * @return
     */
    Map<String, Object> getBorrowAbstract();

    List<Borrow> history(int page);
}
