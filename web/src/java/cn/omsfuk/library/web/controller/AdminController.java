package cn.omsfuk.library.web.controller;


import cn.omsfuk.library.web.model.Book;
import cn.omsfuk.library.web.service.BookService;
import cn.omsfuk.library.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("main")
    public String main(Model model) {
        model.addAllAttributes(commentService.getAbstract());
        return "admin/main";
    }

    @RequestMapping("new_book")
    public String newBook(Book book, Model model) {
        if (book == null || book.getTitle() == null) {
            return "admin/new_book";
        }
        model.addAttribute("back_url", "/admin/main");
        if (bookService.newBook(book) == 1) {
            return "/common/success";
        }
        return "/common/failure";
    }

    @RequestMapping("manage_book")
    public String manage_book() {
        return "admin/manage_book";
    }

    @RequestMapping(value = "examine_comment", method = RequestMethod.GET)
    public String examine_comment(Model model,
        @RequestParam(value = "page", defaultValue = "0") Integer page) {
        model.addAttribute("comments", commentService.listUnexaminedComment(page));
        model.addAttribute("page", page);
        return "admin/examine_comment";
    }


    @RequestMapping(value = "examine_comment", method = RequestMethod.POST)
    public String examine_comment(@RequestParam("commentId") Integer commentId,
                                  @RequestParam("valid") Integer valid) {
        if (commentId == null || valid == null) {
            return "common/failure";
        }
        if (commentService.permitComment(commentId, valid) != 1) {
            return "common/failure";
        }
        return "redirect:/admin/examine_comment";
    }
}
