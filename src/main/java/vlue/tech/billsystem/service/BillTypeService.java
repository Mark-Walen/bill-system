package vlue.tech.billsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlue.tech.billsystem.mapper.BillTypeMapper;
import vlue.tech.billsystem.pojo.BillType;

import java.util.Arrays;
import java.util.List;

@Service
public class BillTypeService {

    private BillTypeMapper billTypeMapper;

    public List<BillType> findAllBillType() {
        return billTypeMapper.selectList(null);
    }

    public int insertBillType(BillType billType) {
        return billTypeMapper.insert(billType);
    }

    public int updateBillType(BillType billType) {
        return billTypeMapper.updateById(billType);
    }

    public int deleteBillTypeById(Integer id) {
        return billTypeMapper.deleteById(id);
    }

    public int deleteBillTypeByIds(Integer[] ids) {
        return billTypeMapper.deleteBatchIds(Arrays.asList(ids));
    }
    public int deleteBillTypeById(BillType billType) {
        return billTypeMapper.deleteById(billType);
    }

    @Autowired
    public BillTypeService(BillTypeMapper billTypeMapper) {
        this.billTypeMapper = billTypeMapper;
    }
}
