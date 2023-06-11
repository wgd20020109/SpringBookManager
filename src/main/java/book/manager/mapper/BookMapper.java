package book.manager.mapper;

import book.manager.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select * from book")
    List<Book> allBooks();

    @Select("select title from book where bid = #{bid}")
    String getTitleByBid(int bid);

    @Delete("delete from book where bid = #{bid}")
    void deleteBook(@Param("bid") int bid);

    @Insert("insert into book(title,price,`desc`) values(#{title},#{price},#{desc})")
    void addBook(@Param("title") String title, @Param("price") double price, @Param("desc") String desc);

    @Update("update book set status = #{status} where bid = #{bid}")
    void updateBookStatus(@Param("status") String status,@Param("bid") int bid);

    @Select("select count(*) from book")
    int getBookCount();

    @Select("select count(*) from book where status = '已借出'")
    int getBorrowingCount();

    @Select("select count(*) from borrow")
    int getBorrowCount();
}
