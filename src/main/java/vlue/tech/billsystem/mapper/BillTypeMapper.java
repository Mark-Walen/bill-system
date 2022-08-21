package vlue.tech.billsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vlue.tech.billsystem.pojo.BillType;

@Mapper
public interface BillTypeMapper extends BaseMapper<BillType> {
}
