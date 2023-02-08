package pl.coderslab.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class BookConverter implements Converter<String, Book> {
    @Autowired
    private BookDao bookDao;

    @Override
    public Book convert(String source) {
        return bookDao.findById(Long.parseLong(source));
    }
}