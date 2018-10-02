package cn.omsfuk.library.web.service;

import cn.omsfuk.library.web.model.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    Integer insertComment(Comment comment);

    Integer deleteComment(int id);

    List<Comment> listCommentByBookId(int page, int bookId);

    List<Comment> listUnexaminedComment(int page);

    Integer permitComment(int commentId, Integer valid);

    Map<String, Object> getAbstract();
}
