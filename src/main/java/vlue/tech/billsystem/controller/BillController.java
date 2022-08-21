package vlue.tech.billsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlue.tech.billsystem.pojo.BillItem;
import vlue.tech.billsystem.service.BillItemService;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    private BillItemService billItemService;

    @GetMapping("/findAllBill")
    public List<BillItem> findAllBill() {
        return billItemService.findAllBill();
    }

    @GetMapping("/findAllBillItemById")
    public ResponseEntity<BillItem> findAllBillItemById(Integer billId, Integer userId) {
        if (billId == null || userId == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        BillItem billItem = billItemService.findAllBillItemById(billId, userId);
        return ResponseEntity.ok(billItem);
    }


    @Autowired
    public void setBillItemService(BillItemService billItemService) {
        this.billItemService = billItemService;
    }
}
