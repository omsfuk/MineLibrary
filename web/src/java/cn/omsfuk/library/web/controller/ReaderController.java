package cn.omsfuk.library.web.controller;

import cn.omsfuk.library.web.config.AppConfig;
import cn.omsfuk.library.web.model.Book;
import cn.omsfuk.library.web.model.Borrow;
import cn.omsfuk.library.web.model.Comment;
import cn.omsfuk.library.web.model.User;
import cn.omsfuk.library.web.service.BookService;
import cn.omsfuk.library.web.service.BorrowService;
import cn.omsfuk.library.web.service.CommentService;
import cn.omsfuk.library.web.service.UserService;
import cn.omsfuk.library.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("main")
    public String main(Model model) {
        model.addAllAttributes(borrowService.getBorrowAbstract());
        model.addAttribute("books", bookService.recentBook());
        return "reader/main";
    }

    @RequestMapping("user")
    public String user(Model model) {
        model.addAttribute("user", SessionUtil.getCurrentUser());
        return "reader/user";
    }

    @RequestMapping(value = "borrow", method = RequestMethod.GET)
    public String borrowPage(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        model.addAttribute("borrows", borrowService.listBorrow(page));
        model.addAttribute("page", page);
        return "reader/borrow";
    }

    @RequestMapping("renew")
    public String renew(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("back_url", "/reader/borrow");
        if (id == null) return "redirect:/reader/borrow";
        if (borrowService.renewBorrow(id)) {
            return "common/success";
        }
        return "common/failure";
    }

    @RequestMapping(value = "borrowBook", method = RequestMethod.POST)
    public String borrowBook(@RequestParam("id") Integer book_id, Model model) {
        if (book_id == null) return "redirect:/reader/borrow";
        if (borrowService.borrowBook(Arrays.asList(book_id)) > 0) {
            return "redirect:/reader/borrow";
        }
        model.addAttribute("back_url", "/reader/book_detail?id=" + book_id);
        return "/common/failure";
    }

    @RequestMapping("book_detail")
    public String bookDetail(@RequestParam(value = "id") int id, Model model, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        model.addAttribute("comments", commentService.listCommentByBookId(id, page));
        model.addAttribute("book", bookService.selectBook(id));
        model.addAttribute("page", page);
        return "reader/book_detail";
    }

    @RequestMapping("search")
    public String search(String title, String item, Model model, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        if (title == null && item == null) {
            return "reader/search";
        }
        List<Book> books = bookService.listBookByTitle(title, PageRequest.of(0, AppConfig.PAGE_SIZE));
        model.addAttribute("books", books);
        model.addAttribute("title", title);
        model.addAttribute("page", page);
        return "reader/result";
    }

    @RequestMapping("returnBook")
    public String returnBook(@RequestParam(value = "id") Integer[] ids, Model model) {
        if (ids == null || ids.length == 0) return "common/failure";
        if (borrowService.returnBook(Arrays.asList(ids)) < 0) {
            return "common/failure";
        }
        model.addAttribute("back_url", "/reader/borrow");
        return "common/success";
    }

    @RequestMapping(value = "user/update", method = RequestMethod.POST)
    public String updateUser(User user, Model model) {
        model.addAttribute("back_url", "/reader/main");
        if (userService.updateUser(user)) {
            return "common/success";
        } else {
            return "common/failure";
        }
    }

    @RequestMapping(value = "user/update", method = RequestMethod.GET)
    public String updateUser(Model model) {
        model.addAttribute("user", SessionUtil.getCurrentUser());
        return "reader/user_update";
    }

    @RequestMapping(value = "history")
    public String history(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        List<Borrow> books = borrowService.history(page);
        model.addAttribute("borrows", books);
        model.addAttribute("page", page);
        return "reader/history";
    }

    @RequestMapping("new_comment")
    public String newComment(Comment comment) {
        if (commentService.insertComment(comment) == 1) {
            return "redirect:/reader/book_detail?id=" + comment.getBookId();
        } else {
            return "common/failure";
        }
    }

    @RequestMapping("delete_comment")
    public String deleteComment(@RequestParam("comment_id") Integer id) {
        if (commentService.deleteComment(id) == 1) {
            return "common/success";
        } else {
            return "common/failure";
        }
    }

}
