package vlue.tech.billsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vlue.tech.billsystem.exception.AuthenticationException;
import vlue.tech.billsystem.pojo.CardCategory;
import vlue.tech.billsystem.pojo.Session;
import vlue.tech.billsystem.service.CardCService;
import vlue.tech.billsystem.service.UserService;
import vlue.tech.billsystem.util.BeanUtil;
import vlue.tech.billsystem.util.ResultGenerator;
import vlue.tech.billsystem.vo.CardCategoryVO;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/card-category")
public class CardCategoryController {

    private CardCService cardCService;
    private UserService userService;

    @GetMapping("/findAllCardCategory")
    public ResponseEntity findAllCardCategory(HttpSession httpSession) {
        String sessionId = (String) httpSession.getAttribute("sessionId");
        if (sessionId == null) throw new AuthenticationException();
        Session session = userService.getSessionById(sessionId);
        return ResponseEntity.ok(ResultGenerator.genSuccessResult(null, cardCService.findAllT(session.getUserId())));
    }

    @PostMapping("/insert")
    public ResponseEntity insertCardCategory(@RequestBody CardCategoryVO cardCategoryVO) {
        CardCategory cardCategory = new CardCategory();
        BeanUtil.copyProperties(cardCategoryVO, cardCategory);
        cardCService.insertT(cardCategory);
        return ResponseEntity.ok(ResultGenerator.genSuccessResult());
    }

    @Autowired
    public CardCategoryController(CardCService cardCService, UserService userService) {
        this.cardCService = cardCService;
        this.userService = userService;
    }
}
