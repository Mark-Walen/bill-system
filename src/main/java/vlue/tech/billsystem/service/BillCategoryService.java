package vlue.tech.billsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlue.tech.billsystem.mapper.BillCategoryMapper;
import vlue.tech.billsystem.pojo.BillCategory;

import java.util.List;
import java.util.Map;

@Service
public class BillCategoryService {

    private BillCategoryMapper billCategoryMapper;

    /**
     * 根据用户的 id 查出自己创建的分类
     * @param userId 用户 id
     * @return 返回查询结果集
     */
    public List<BillCategory> findAllBillCateGory(Integer userId) {
        QueryWrapper<BillCategory> billCategoryQueryWrapper = new QueryWrapper<>();
        billCategoryQueryWrapper.eq("user_id", userId);

        return billCategoryMapper.selectList(billCategoryQueryWrapper);
    }

    public int insertBillCategory(BillCategory billCategory) {
        return billCategoryMapper.insert(billCategory);
    }

    public int updateBillCategoryById(BillCategory billCategory) {
        return billCategoryMapper.updateById(billCategory);
    }

    public int updateBillCategorySelective(Integer id, Integer userId, Map<String, Object> searchMap) {
        UpdateWrapper<BillCategory> billCategoryUpdateWrapper = new UpdateWrapper<>();
        billCategoryUpdateWrapper.and(wrapper -> wrapper.eq("id", id).eq("user_id", userId));
        
        searchMap.forEach(billCategoryUpdateWrapper::set);
        return billCategoryMapper.update(null, billCategoryUpdateWrapper);
    }

    /**
     * 根据条件删除
     * @param searchMap 条件对象
     * @return 执行是否成功标志
     */
    public int deleteById(Map<String, Object> searchMap) {
        return billCategoryMapper.deleteByMap(searchMap);
    }

    /**
     * 根据主键 id 和用户 id 删除。
     * @param id 主键 id
     * @param userId 用户 id
     * @return 执行是否成功标志
     */
    public int deleteById(Integer id, Integer userId) {
        QueryWrapper<BillCategory> billCategoryQueryWrapper = new QueryWrapper<>();
        billCategoryQueryWrapper.and(wrapper -> wrapper.eq("id", id).eq("user_id", userId));

        return billCategoryMapper.delete(billCategoryQueryWrapper);
    }

    @Autowired
    public void setBillCategoryMapper(BillCategoryMapper billCategoryMapper) {
        this.billCategoryMapper = billCategoryMapper;
    }
}
