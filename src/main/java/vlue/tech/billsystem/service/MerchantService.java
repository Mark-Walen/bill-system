package vlue.tech.billsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlue.tech.billsystem.mapper.MerchantMapper;
import vlue.tech.billsystem.pojo.Merchant;

import java.util.List;
import java.util.Map;

@Service
public class MerchantService {

    private MerchantMapper merchantMapper;

    public List<Merchant> findAllMerchant(Integer userId) {
        QueryWrapper<Merchant> merchantQueryWrapper = new QueryWrapper<>();
        merchantQueryWrapper.eq("user_id", userId);

        return merchantMapper.selectList(merchantQueryWrapper);
    }

    public int insertMerchant(Merchant merchant) {
        return merchantMapper.insert(merchant);
    }

    public int updateMerchantById(Merchant merchant) {
        return merchantMapper.updateById(merchant);
    }

    public int updateMerchantSelective(Integer id, Integer userId, Map<String, Object> searchMap) {
        UpdateWrapper<Merchant> merchantUpdateWrapper = new UpdateWrapper<>();
        merchantUpdateWrapper.and(wrapper -> wrapper.eq("id", id).eq("user_id", userId));

        searchMap.forEach(merchantUpdateWrapper::set);
        return merchantMapper.update(null, merchantUpdateWrapper);
    }

    public int deleteById(Map<String, Object> searchMap) {
        return merchantMapper.deleteByMap(searchMap);
    }

    public int deleteById(Integer id, Integer userId) {
        QueryWrapper<Merchant> merchantQueryWrapper = new QueryWrapper<>();
        merchantQueryWrapper.and(wrapper -> wrapper.eq("id", id).eq("userId", userId));

        return merchantMapper.delete(merchantQueryWrapper);
    }

    @Autowired
    public MerchantService(MerchantMapper merchantMapper) {
        this.merchantMapper = merchantMapper;
    }
}
