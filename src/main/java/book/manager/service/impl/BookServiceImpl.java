package book.manager.service.impl;

import book.manager.entity.Book;
import book.manager.entity.Borrow;
import book.manager.entity.BorrowInfo;
import book.manager.mapper.BookMapper;
import book.manager.mapper.BorrowMapper;
import book.manager.mapper.UserMapper;
import book.manager.service.AuthService;
import book.manager.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper mapper;
    @Resource
    BorrowMapper borrowMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public List<Book> getAllBook() {
        return mapper.allBooks();
    }

    @Override
    public List<Book> getAllBookWithOutBorrow(){
        List<Book> books = mapper.allBooks();
        List<Integer> borrows = borrowMapper.getBorrowListByStatus("借阅中")
                .stream()
                .map(Borrow::getBid)
                .collect(Collectors.toList());

        return books.stream()
                .filter(book -> !borrows.contains(book.getBid()))
                .collect(Collectors.toList());
    }
    @Override
    public List<BorrowInfo> getBorrowBookById(int id){      //stream流使用，完全可简化在mapper层操作，懒得改了
        Integer sid = userMapper.getSidByUid(id);
        if (sid == null) return Collections.emptyList();

        return borrowMapper.getBorrowListBySid(sid)
                .stream()
                .map(borrow -> {
                    String bookTitle = mapper.getTitleByBid(borrow.getBid());
                    if (bookTitle == null) bookTitle = "[该书已被删除]";
                    return new BorrowInfo(borrow.getId(),borrow.getBid(),sid,bookTitle, userMapper.getSNameByUid(id), borrow.getTime(), borrow.getStatus());
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBook(int bid) {
        mapper.deleteBook(bid);
    }

    @Override
    public void addBook(String title, double price, String desc) {
        mapper.addBook(title,price,desc);
    }

}
