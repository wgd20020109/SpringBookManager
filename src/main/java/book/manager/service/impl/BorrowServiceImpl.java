package book.manager.service.impl;

import book.manager.entity.BorrowInfo;
import book.manager.mapper.BookMapper;
import book.manager.mapper.BorrowMapper;
import book.manager.mapper.UserMapper;
import book.manager.service.BorrowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Resource
    BorrowMapper mapper;
    @Resource
    UserMapper userMapper;
    @Resource
    BookMapper bookMapper;

    @Override
    public void addBorrow(int bid, int uid) {
        Integer sid = userMapper.getSidByUid(uid);
        if (sid == null) return;
        bookMapper.updateBookStatus("已借出",bid);
        mapper.addBorrow(bid,sid);
    }

    @Override
    public void returnBook(int bid, int uid) {
        Integer sid = userMapper.getSidByUid(uid);
        if (sid == null) return;
        bookMapper.updateBookStatus("可借阅",bid);
        mapper.returnBook(bid,sid);
    }

    @Override
    public List<BorrowInfo> getAllBorrowInfo() {
        return mapper.getAllBorrowInfo();
    }
}
