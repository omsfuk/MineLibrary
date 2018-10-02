package cn.omsfuk.library.web.dao;

import cn.omsfuk.library.web.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Mapper
public interface CommentDAO {

    List<Comment> listCommentByBookId(@Param("bookId") int id, @Param("page") PageRequest page);

    Integer insertComment(@Param("comment") Comment comment);

    Integer deleteCommentById(@Param("id") int id);

    List<Comment> listUnexaminedComment(@Param("page") PageRequest of);

    Integer permitComment(@Param("commentId") int commentId, @Param("valid") Integer valid);

    Integer countCommentToExamine();
}
