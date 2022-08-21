package vlue.tech.billsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public ResponseEntity getUserInfo() {
        String mock = "{\n" +
                "    id: '4291d7da9005377ec9aec4a71ea837f',\n" +
                "    name: '天野远子',\n" +
                "    username: 'admin',\n" +
                "    password: '',\n" +
                "    avatar: '/avatar2.jpg',\n" +
                "    status: 1,\n" +
                "    telephone: '',\n" +
                "    lastLoginIp: '27.154.74.117',\n" +
                "    lastLoginTime: 1534837621348,\n" +
                "    creatorId: 'admin',\n" +
                "    createTime: 1497160610259,\n" +
                "    merchantCode: 'TLif2btpzg079h15bk',\n" +
                "    deleted: 0,\n" +
                "    roleId: 'admin',\n" +
                "    role: {}\n" +
                "  }";
        return ResponseEntity.ok(mock);
    }
}
