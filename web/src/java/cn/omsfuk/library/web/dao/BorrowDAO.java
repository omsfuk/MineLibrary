package cn.omsfuk.library.web.dao;

import cn.omsfuk.library.web.model.Book;
import cn.omsfuk.library.web.model.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Mapper
public interface BorrowDAO {

    List<Book> listBorrowByUsername(@Param("username") String username, @Param("page") PageRequest page);

    Integer borrowBookById(@Param("ids") List<Integer> ids, @Param("userId")int userId);

    Integer returnBookById(@Param("ids") List<Integer> ids, @Param("userId")int userId);

    Integer renewBorrowById(@Param("book_id") int id, @Param("user_id") Integer userId);

    Integer countBorrowByUserId(@Param("userId") int userId);

    Integer countOverdueSoonByUserId(@Param("userId") int userId);

    Integer isBorrowed(@Param("ids") List<Integer> ids, @Param("userId") Integer userId);

    List<Borrow> listBorrowedByUserId(@Param("userId") int id, @Param("page") PageRequest page);

}
