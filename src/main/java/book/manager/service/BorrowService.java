package book.manager.service;

import book.manager.entity.BorrowInfo;

import java.util.List;

public interface BorrowService {

    void addBorrow(int bid,int sid);
    void returnBook(int bid,int uid);
    List<BorrowInfo> getAllBorrowInfo();
}
