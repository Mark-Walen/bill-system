package vlue.tech.billsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlue.tech.billsystem.exception.AuthenticationException;
import vlue.tech.billsystem.exception.RequestParameterException;
import vlue.tech.billsystem.service.UserService;
import vlue.tech.billsystem.util.ResultGenerator;
import vlue.tech.billsystem.vo.SessionVO;
import vlue.tech.billsystem.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);
    private UserService userService;

    @PostMapping("/make-nonce")
    public ResponseEntity makeAuthNonce(HttpServletRequest request) {
//        logger.info(request.getParameterNames().hasMoreElements() + ": " + username);
        String username = request.getParameter("auth");
        if (StringUtils.isAnyBlank(username)) throw new RequestParameterException();
        return ResponseEntity.ok(ResultGenerator.genSuccessResult(null, userService.makeAuthNonce(username)));
    }

    @PostMapping("/login")
    public ResponseEntity authLogin(@RequestBody UserVO userVO, HttpSession httpSession) {
        String username =userVO.getUsername();
        String key = userVO.getKey();
//        logger.info(userVO.toString());
        if (StringUtils.isAnyBlank(username, key)) throw new RequestParameterException();
        Map<String, String> res = userService.restfulLogin(username, key);
        if (res == null) throw new AuthenticationException("用户名或密码错误");
        httpSession.setAttribute("session", res.get("sessionId"));
        httpSession.setAttribute("token", res.get("token"));
        return ResponseEntity.ok(ResultGenerator.genSuccessResult(null, res));
    }

    @PostMapping("/verify")
    public ResponseEntity authVerify(@RequestBody SessionVO sessionVO) {
        String session = sessionVO.getSession();
        String key = sessionVO.getKey();
        if (StringUtils.isAnyBlank(session, key)) throw new RequestParameterException();
        Integer userId = userService.restfulVerify(session, key);
        if (userId == null) throw new AuthenticationException("Authentication Error");
        return ResponseEntity.ok(ResultGenerator.genSuccessResult());
    }

    /*@PostMapping("/logout")
    public ResponseEntity logout() {
        return ResponseEntity.ok("{}");
    }*/

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }
}
