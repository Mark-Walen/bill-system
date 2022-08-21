package vlue.tech.billsystem.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vlue.tech.billsystem.exception.RequestParameterException;
import vlue.tech.billsystem.pojo.BillType;
import vlue.tech.billsystem.service.BillTypeService;
import vlue.tech.billsystem.util.ResultGenerator;

@RestController
@RequestMapping("/bill-type")
public class BillTypeController {

    private BillTypeService billTypeService;

    @GetMapping("/findAll")
    public ResponseEntity findAllBillType() {
        return ResponseEntity.ok(ResultGenerator.genSuccessResult(null, billTypeService.findAllBillType()));
    }

    @PostMapping("/insert")
    public ResponseEntity insertBillType(@RequestBody BillType billType) {

        if (StringUtils.isBlank(billType.getName())) throw new RequestParameterException();
        int result = billTypeService.insertBillType(billType);
        if (result == 0)
            return new ResponseEntity(ResultGenerator.genFailResult("操作失败，请稍后再试！"), HttpStatus.INTERNAL_SERVER_ERROR);
        else return ResponseEntity.ok(ResultGenerator.genSuccessResult("操作成功。", result));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateBillType(@RequestBody BillType billType) {
        if (billType.getId() == null || StringUtils.isBlank(billType.getName())) throw new RequestParameterException();

        int result = billTypeService.updateBillType(billType);
        if (result == 0)
            return new ResponseEntity<>(ResultGenerator.genFailResult("操作失败，请稍后再试！"), HttpStatus.INTERNAL_SERVER_ERROR);
        else return ResponseEntity.ok(ResultGenerator.genSuccessResult("操作成功。", result));
    }

    @PostMapping("/delete")
    public ResponseEntity deleteBillType(Integer id) {
        return ResponseEntity.ok(ResultGenerator.genSuccessResult(null, billTypeService.deleteBillTypeById(id)));
    }


    @PostMapping("/delete-batch")
    public ResponseEntity deleteBillTypeByIds(Integer[] ids) {
        return ResponseEntity.ok(ResultGenerator.genSuccessResult(null, billTypeService.deleteBillTypeByIds(ids)));
    }

    @Autowired
    public BillTypeController(BillTypeService billTypeService) {
        this.billTypeService = billTypeService;
    }
}
