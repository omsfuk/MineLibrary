package cn.omsfuk.library.web.dao;

import cn.omsfuk.library.web.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookDAO {

    Book selectBookById(@Param("id") int id);

    Integer insertBook(@Param("book") Book book);

    Integer lastInsertId();

    List<Book> listAllBook();

    Integer updateBookCountById(@Param("delta") int delta, @Param("ids") List<Integer> id);

    Integer selectBookCountById(@Param("bookId") int bookId);

    List<Book> listRecentBook();
}
