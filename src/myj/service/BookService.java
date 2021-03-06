package myj.service;

import myj.pojo.Book;
import myj.pojo.Page;

import java.util.List;

/**
 * @author myj
 * @date 2021/6/10 - 16:24
 */
public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
