package cn.omsfuk.library.web.service.impl;

import cn.omsfuk.library.web.config.AppConfig;
import cn.omsfuk.library.web.dao.BookDAO;
import cn.omsfuk.library.web.dao.BorrowDAO;
import cn.omsfuk.library.web.model.Book;
import cn.omsfuk.library.web.model.Borrow;
import cn.omsfuk.library.web.service.BorrowService;
import cn.omsfuk.library.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowDAO borrowDAO;

    @Autowired
    private BookDAO bookDAO;

    @Override
    public List<Book> listBorrow(int page) {
        return borrowDAO.listBorrowByUsername(SessionUtil.getCurrentUser().getUsername(), PageRequest.of(page * AppConfig.PAGE_SIZE, AppConfig.PAGE_SIZE));
    }

    @Override
    public Integer returnBook(List<Integer> ids) {
        bookDAO.updateBookCountById(1, ids);
        return borrowDAO.returnBookById(ids, SessionUtil.getCurrentUser().getId());
    }

    @Override
    @Transactional
    public Integer borrowBook(List<Integer> ids) {
        if (borrowDAO.isBorrowed(ids, SessionUtil.getCurrentUser().getId()) > 0) {
            return 0;
        }
        if (!ids.stream().allMatch((id) -> bookDAO.selectBookCountById(id) > 0)) {
            return 0;
        }
        bookDAO.updateBookCountById(-1, ids);
        return borrowDAO.borrowBookById(ids, SessionUtil.getCurrentUser().getId());
    }

    @Override
    public Boolean renewBorrow(Integer id) {
        return borrowDAO.renewBorrowById(id, SessionUtil.getCurrentUser().getId()) == 1 ? true : false;
    }

    @Override
    public Map<String, Object> getBorrowAbstract() {
        Integer borrowCount = borrowDAO.countBorrowByUserId(SessionUtil.getCurrentUser().getId());
        Integer overdueSoonCount = borrowDAO.countOverdueSoonByUserId(SessionUtil.getCurrentUser().getId());
        Map<String, Object> result = new HashMap<>(2);
        result.put("borrowCount", borrowCount);
        result.put("overdueSoonCount", overdueSoonCount);
        return result;
    }

    @Override
    public List<Borrow> history(int page) {
        return borrowDAO.listBorrowedByUserId(SessionUtil.getCurrentUser().getId(), PageRequest.of(page * AppConfig.PAGE_SIZE, AppConfig.PAGE_SIZE));
    }


}
