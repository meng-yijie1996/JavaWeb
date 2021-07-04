package myj.test;

import myj.dao.BookDao;
import myj.dao.impl.BookDaoImpl;
import myj.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author myj
 * @date 2021/6/10 - 16:13
 */
public class BookDaoTest {

    private BookDao bookDao=new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"李颖为啥这么好看","191125",new BigDecimal(9999),100000,0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"dajia都可以","191125",new BigDecimal(9999),100000,0,null));

    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(15);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        books.forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        List<Book> list = bookDao.queryForPageItems(0, 5);
        list.forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
    }

    @Test
    public void queryForPageItemsByPrice() {
        List<Book> list = bookDao.queryForPageItemsByPrice(0, 4, 50, 100);
        list.forEach(System.out::println);
    }

}