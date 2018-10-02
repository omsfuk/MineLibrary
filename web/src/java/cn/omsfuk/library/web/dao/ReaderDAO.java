package cn.omsfuk.library.web.dao;

import cn.omsfuk.library.web.model.Reader;

public interface ReaderDAO {

    Integer insertUser(Reader reader);

    Reader selectReaderByUsername(String username);

    Reader updateReader(Reader reader);
}
