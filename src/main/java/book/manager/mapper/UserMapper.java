package book.manager.mapper;

import book.manager.entity.AuthUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from users where name = #{name}")
    AuthUser getPasswordByUsername(String name);

    @Select("select name from student where uid = #{uid}")
    String getSNameByUid(int uid);

    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert("insert into users(name,role,password) values(#{name},#{role},#{password})")
    int registerUser(AuthUser user);

    @Insert("insert into student(uid,name,grade,sex) values(#{uid},#{name},#{grade},#{sex})")
    int addStudentInfo(@Param("uid") int uid, @Param("name") String name, @Param("grade") String grade, @Param("sex") String sex);

    @Select("select sid from student where uid = #{uid}")
    Integer getSidByUid(int uid);

    @Select("select count(*) from student")
    int getStudentCount();
}
