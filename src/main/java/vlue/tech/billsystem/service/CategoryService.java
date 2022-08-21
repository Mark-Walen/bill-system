package vlue.tech.billsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import vlue.tech.billsystem.pojo.Category;

import java.util.List;
import java.util.Map;

public class CategoryService<T extends Category, E extends BaseMapper<T>> {

    private E e;

    public List<T> findAllT(Integer userId) {
        QueryWrapper<T> tQueryWrapper = new QueryWrapper<>();
        tQueryWrapper.eq("user_id", userId);

        return e.selectList(tQueryWrapper);
    }

    public int insertT(T t) {
        return e.insert(t);
    }

    public int updateTById(T t) {
        return e.updateById(t);
    }

    public int updateTSelective(Integer id, Integer userId, Map<String, Object> searchMap) {
        UpdateWrapper<T> cardCategoryUpdateWrapper = new UpdateWrapper<>();
        cardCategoryUpdateWrapper.and(wrapper -> wrapper.eq("id", id).eq("user_id", userId));

        searchMap.forEach(cardCategoryUpdateWrapper::set);
        return e.update(null, cardCategoryUpdateWrapper);
    }

    /**
     * 根据条件删除
     * @param searchMap 条件对象
     * @return 执行是否成功标志
     */
    public int deleteById(Map<String, Object> searchMap) {
        return e.deleteByMap(searchMap);
    }

    /**
     * 根据主键 id 和用户 id 删除。
     * @param id 主键 id
     * @param userId 用户 id
     * @return 执行是否成功标志
     */
    public int deleteById(Integer id, Integer userId) {
        QueryWrapper<T> tQueryWrapper = new QueryWrapper<>();
        tQueryWrapper.and(wrapper -> wrapper.eq("id", id).eq("user_id", userId));

        return e.delete(tQueryWrapper);
    }

    public CategoryService(E e) {
        this.e = e;
    }
}
