package myj.test;

import myj.pojo.Book;
import myj.pojo.Page;
import myj.service.BookService;
import myj.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author myj
 * @date 2021/6/10 - 17:03
 */
public class BookServiceImplTest {

    private BookService bookService=new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"甘肃不大","炎阳",new BigDecimal(500),500,600,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(23);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"创造神话","炎阳",new BigDecimal(500),500,600,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(16));
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        books.forEach(System.out::println);
    }

    @Test
    public void page(){
        System.out.println(bookService.page(5,2));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,50,100));
    }
}