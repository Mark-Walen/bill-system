package vlue.tech.billsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlue.tech.billsystem.mapper.CardMapper;
import vlue.tech.billsystem.pojo.Card;

import java.util.List;
import java.util.Map;

@Service
public class CardService {

    private CardMapper cardMapper;

    public List<Card> findAllCard(Integer userId) {
        QueryWrapper<Card> cardQueryWrapper = new QueryWrapper<>();
        cardQueryWrapper.eq("user_id", userId);

        return cardMapper.selectList(cardQueryWrapper);
    }

    public int insertCard(Card card) {
        return cardMapper.insert(card);
    }

    public int updateCardById(Card card) {
        return cardMapper.updateById(card);
    }

    public int updateCardSelective(Integer id, Integer userId, Map<String, Object> searchMap) {
        UpdateWrapper<Card> carUpdateWrapper = new UpdateWrapper<>();
        carUpdateWrapper.and(wrapper -> wrapper.eq("id", id).eq("user_id", userId));

        searchMap.forEach(carUpdateWrapper::set);
        return cardMapper.update(null, carUpdateWrapper);
    }

    public int deleteById(Map<String, Object> searchMap) {
        return cardMapper.deleteByMap(searchMap);
    }

    public int deleteById(Integer id, Integer userId) {
        QueryWrapper<Card> cardQueryWrapper = new QueryWrapper<>();
        cardQueryWrapper.and(wrapper -> wrapper.eq("id", id).eq("userId", userId));

        return cardMapper.delete(cardQueryWrapper);
    }

    @Autowired
    public CardService(CardMapper cardMapper) {
        this.cardMapper = cardMapper;
    }
}
