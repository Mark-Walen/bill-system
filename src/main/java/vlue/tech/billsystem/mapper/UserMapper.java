package vlue.tech.billsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import vlue.tech.billsystem.pojo.Users;

@Mapper
public interface UserMapper extends BaseMapper<Users> {

    @Select("select * from users where username = #{username} limit 1;")
    Users getUserByUsername(@Param("username") String username);
}
