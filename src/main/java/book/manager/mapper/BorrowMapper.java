package book.manager.mapper;

import book.manager.entity.Borrow;
import book.manager.entity.BorrowInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BorrowMapper {

    @Select("select * from borrow")
    List<Borrow> allBorrowList();

    @Select("select * from borrow where sid = #{sid}")
    List<Borrow> getBorrowListBySid(int sid);

    @Select("select * from borrow where status = #{status}")
    List<Borrow> getBorrowListByStatus(String status);

    @Insert("insert into borrow(bid,sid,`time`) values(#{bid},#{sid},NOW())")
    void addBorrow(@Param("bid") int bid, @Param("sid") int sid);

    @Update("update borrow set status = '已归还' where bid = #{bid} and sid = #{sid} ")
    void returnBook(@Param("bid") int bid, @Param("sid") int sid);


    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "bid",property = "bid"),
            @Result(column = "sid",property = "sid"),
            @Result(column = "bTitle",property = "title"),
            @Result(column = "uName",property = "name"),
            @Result(column = "time",property = "time"),
            @Result(column = "status",property = "status")
    })
    @Select("SELECT id,borrow.bid,student.sid,book.title,student.`name`,time,borrow.`status` FROM borrow LEFT JOIN book ON book.bid = borrow.bid LEFT JOIN student ON borrow.sid = student.sid")
    List<BorrowInfo> getAllBorrowInfo();
}
