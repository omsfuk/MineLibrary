package cn.omsfuk.library.web.controller;

import cn.omsfuk.library.web.dao.BookDAO;
import cn.omsfuk.library.web.dao.BookESDAO;
import cn.omsfuk.library.web.dao.UserDAO;
import cn.omsfuk.library.web.model.Book;
import cn.omsfuk.library.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("tests")
public class TestController {

    @Autowired
    private BookESDAO bookESDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BookDAO bookDAO;

    @RequestMapping("es")
    public String elasticsearch() {
        return "wwww";
    }

    @RequestMapping("save")
    public String save() {
        Book book = new Book();
        book.setTitle("Java从入门到精通");
        book.setCarrier("哈哈哈");
        book.setId(1000000);
        book.setPress("山大出版社");
        bookESDAO.save(book);
        return "success";
    }

    @RequestMapping("key")
    public String keyGene() {
        User user = new User();
        user.setUsername("www");
        user.setPassword("www");
        userDAO.insertUser(user);
        return "success";
    }

    @RequestMapping("init")
    public String init() {
        List<Book> books = bookDAO.listAllBook();
        books.forEach(bookESDAO::save);
        return "/common/success";
    }
}
