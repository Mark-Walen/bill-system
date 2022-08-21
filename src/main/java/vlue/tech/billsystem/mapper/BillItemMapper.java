package vlue.tech.billsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import vlue.tech.billsystem.pojo.BillItem;

import java.util.List;

@Mapper
public interface BillItemMapper extends BaseMapper<BillItem> {

    @Select("select * from bill_item;")
    List<BillItem> findAllBillItem();

    @Select("select * from bill_item where id = #{billId} and user_id = #{userId}")
    BillItem findAllBillItemById(@Param("billId") Integer billId, @Param("userId") Integer userId);

    @Select("select * from bill_item where user_id = #{userId}")
    List<BillItem> findBillItemByUserId(@Param("userId") Integer userId);
}
