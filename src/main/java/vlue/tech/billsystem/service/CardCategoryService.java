package vlue.tech.billsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlue.tech.billsystem.mapper.CardCategoryMapper;
import vlue.tech.billsystem.pojo.CardCategory;

import java.util.List;
import java.util.Map;

@Service
public class CardCategoryService {

    private CardCategoryMapper cardCategoryMapper;

    /**
     * 根据用户的 id 查出自己创建的分类
     * @param userId 用户 id
     * @return 返回查询结果集
     */
    public List<CardCategory> findAllCardCategory(Integer userId) {
        QueryWrapper<CardCategory> cardCategoryQueryWrapper = new QueryWrapper<>();
        cardCategoryQueryWrapper.eq("user_id", userId);

        return cardCategoryMapper.selectList(cardCategoryQueryWrapper);
    }

    public int insertCardCategory(CardCategory cardCategory) {
        return cardCategoryMapper.insert(cardCategory);
    }

    public int updateCardCategoryById(CardCategory cardCategory) {
        return cardCategoryMapper.updateById(cardCategory);
    }

    public int updateCardCategorySelective(Integer id, Integer userId, Map<String, Object> searchMap) {
        UpdateWrapper<CardCategory> cardCategoryUpdateWrapper = new UpdateWrapper<>();
        cardCategoryUpdateWrapper.and(wrapper -> wrapper.eq("id", id).eq("user_id", userId));

        searchMap.forEach(cardCategoryUpdateWrapper::set);
        return cardCategoryMapper.update(null, cardCategoryUpdateWrapper);
    }

    /**
     * 根据条件删除
     * @param searchMap 条件对象
     * @return 执行是否成功标志
     */
    public int deleteById(Map<String, Object> searchMap) {
        return cardCategoryMapper.deleteByMap(searchMap);
    }

    /**
     * 根据主键 id 和用户 id 删除。
     * @param id 主键 id
     * @param userId 用户 id
     * @return 执行是否成功标志
     */
    public int deleteById(Integer id, Integer userId) {
        QueryWrapper<CardCategory> cardCategoryQueryWrapper = new QueryWrapper<>();
        cardCategoryQueryWrapper.and(wrapper -> wrapper.eq("id", id).eq("user_id", userId));

        return cardCategoryMapper.delete(cardCategoryQueryWrapper);
    }

    @Autowired
    public void setCardCategoryMapper(CardCategoryMapper cardCategoryMapper) {
        this.cardCategoryMapper = cardCategoryMapper;
    }
}
