package cn.omsfuk.library.web.service.impl;

import cn.omsfuk.library.web.config.AppConfig;
import cn.omsfuk.library.web.dao.CommentDAO;
import cn.omsfuk.library.web.model.Comment;
import cn.omsfuk.library.web.service.CommentService;
import cn.omsfuk.library.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public Integer insertComment(Comment comment) {
        comment.setUserId(SessionUtil.getCurrentUser().getId());
        return commentDAO.insertComment(comment);
    }

    @Override
    public Integer deleteComment(int id) {
        return commentDAO.deleteCommentById(id);
    }

    @Override
    public List<Comment> listCommentByBookId(int bookId, int page) {
        return commentDAO.listCommentByBookId(bookId, PageRequest.of(page * AppConfig.COMMENT_PAGE_SIZE, AppConfig.COMMENT_PAGE_SIZE));
    }

    @Override
    public List<Comment> listUnexaminedComment(int page) {
        return commentDAO.listUnexaminedComment(
                PageRequest.of(page * AppConfig.COMMENT_EXAMINE_PAGE_SIZE, AppConfig.COMMENT_EXAMINE_PAGE_SIZE));
    }

    @Override
    public Integer permitComment(int commentId, Integer valid) {
        return commentDAO.permitComment(commentId, valid);
    }

    @Override
    public Map<String, Object> getAbstract() {
        Map<String, Object> result = new HashMap<>();
        result.put("commentToExamine", commentDAO.countCommentToExamine());
        return result;
    }
}
