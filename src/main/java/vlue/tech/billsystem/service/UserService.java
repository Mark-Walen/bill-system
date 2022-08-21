package vlue.tech.billsystem.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vlue.tech.billsystem.mapper.SessionMapper;
import vlue.tech.billsystem.mapper.UserMapper;
import vlue.tech.billsystem.pojo.Session;
import vlue.tech.billsystem.pojo.Users;
import vlue.tech.billsystem.util.Utils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserMapper userMapper;
    private SessionMapper sessionMapper;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public Session getSessionById(String sessionId) {
        return sessionMapper.getSessionById(sessionId);
    }

    public String makeAuthNonce(String key) {
        String authNonce = Utils.immutableString();
        redisTemplate.opsForValue().set(key, authNonce, 30, TimeUnit.SECONDS);
        return authNonce;
    }

    public Map<String, String> restfulLogin(String username, String key) {
        String authNonce = getAuthNonce(username);
        Integer userId = getUserIdByLogin(username, key, authNonce);
        if (userId == null) return null;
        return createSession(userId);
    }

    public Integer restfulVerify(String sessionId, String key) {
        String authNonce = getAuthNonce(sessionId);
        return getUserIdBySession(sessionId, key, authNonce);
    }

    // TODO need to be done after include redis.
    private String getAuthNonce(String username) {
        return redisTemplate.opsForValue().get(username);
    }

    private Integer getUserIdByLogin(String username, String key, String authNonce) {
        Users user = userMapper.getUserByUsername(username);
        if (user == null) return null;
        Integer userId = user.getId();
        String passwordMd5 = user.getPasswdMd5();
        List<Object> strings = Arrays.asList(username, passwordMd5, authNonce);

        logger.info(JSON.toJSONString(strings));
        logger.info(Utils.sha256(JSON.toJSONString(strings)));
        if (!Utils.sha256(JSON.toJSONString(strings)).equals(key)) return null;
        return userId;
    }

    private Map<String, String> createSession(Integer userId) {
        String sessionId, token;
        while (true) {
            sessionId = Utils.immutableString();
            token = Utils.immutableString();
            Session session = sessionMapper.getSessionByIds(userId, sessionId);
            if (session == null) break;
        }
        sessionMapper.insertSession(userId, sessionId, token);
        Map<String, String> sessionMap = new HashMap<>();
        sessionMap.put("sessionId", sessionId);
        sessionMap.put("token", token);
        return sessionMap;
    }

    private Integer getUserIdBySession(String sessionId, String key, String authNonce) {
        Session session = sessionMapper.getSessionById(sessionId);
        Integer userId = session.getUserId();
        String token = session.getToken();
        List<Object> list = Arrays.asList(session, token, authNonce);
        if (!Utils.sha256(JSON.toJSONString(list)).equals(key)) return null;
        return userId;
    }

    @Autowired
    public UserService(UserMapper userMapper, SessionMapper sessionMapper) {
        this.userMapper = userMapper;
        this.sessionMapper = sessionMapper;
    }
}
