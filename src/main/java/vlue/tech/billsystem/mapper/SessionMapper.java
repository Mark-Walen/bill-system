package vlue.tech.billsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import vlue.tech.billsystem.pojo.Session;

@Mapper
public interface SessionMapper extends BaseMapper<Session> {

    @Select("select * from session where user_id = #{userId} and session_id = #{sessionId} limit 1;")
    Session getSessionByIds(@Param("userId") Integer userId, @Param("sessionId") String sessionId);

    @Select("select s.id, s.session_id, s.user_id, s.token from session s join users u on u.id = s.user_id where s.session_id = #{sessionId} limit 1;")
    Session getSessionById(@Param("sessionId") String sessionId);

    @Insert({"insert into session(user_id, session_id, token) values(#{userId}, #{sessionId}, #{token})"})
    int insertSession(Integer userId, String sessionId, String token);
}
