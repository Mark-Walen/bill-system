package vlue.tech.billsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("card")
public class Card {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer card_categoryId;
    private Integer balance;
    private String cardSno;
    private String publisher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCard_categoryId() {
        return card_categoryId;
    }

    public void setCard_categoryId(Integer card_categoryId) {
        this.card_categoryId = card_categoryId;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getCardSno() {
        return cardSno;
    }

    public void setCardSno(String cardSno) {
        this.cardSno = cardSno;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
