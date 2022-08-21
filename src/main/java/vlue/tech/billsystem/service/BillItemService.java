package vlue.tech.billsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlue.tech.billsystem.mapper.BillItemMapper;
import vlue.tech.billsystem.pojo.BillItem;

import java.util.List;

@Service
public class BillItemService {

    private BillItemMapper billItemMapper;

    public List<BillItem> findAllBill() {
        return billItemMapper.findAllBillItem();
    }

    public BillItem findAllBillItemById(Integer billId, Integer userId) {
        return billItemMapper.findAllBillItemById(billId, userId);
    }

    public List<BillItem> findBillItemByUserId(Integer userId) {
        return billItemMapper.findBillItemByUserId(userId);
    }

    @Autowired
    public void setBillItemMapper(BillItemMapper billItemMapper) {
        this.billItemMapper = billItemMapper;
    }
}
