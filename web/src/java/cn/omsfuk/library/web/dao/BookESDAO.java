package cn.omsfuk.library.web.dao;

import cn.omsfuk.library.web.model.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookESDAO extends ElasticsearchRepository<Book, Integer> {

    List<Book> queryBooksByTitle(String title, PageRequest page);

    Book save(Book book);

}
