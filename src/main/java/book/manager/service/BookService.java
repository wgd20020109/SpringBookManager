package book.manager.service;

import book.manager.entity.Book;
import book.manager.entity.BorrowInfo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService {
    List<Book> getAllBook();
    public List<Book> getAllBookWithOutBorrow();
    public List<BorrowInfo> getBorrowBookById(int id);
    void deleteBook(int bid);
    void addBook(String title,double price,String desc);

}
