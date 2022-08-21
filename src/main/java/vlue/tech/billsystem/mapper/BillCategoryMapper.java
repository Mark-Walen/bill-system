package vlue.tech.billsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vlue.tech.billsystem.pojo.BillCategory;

@Mapper
public interface BillCategoryMapper extends BaseMapper<BillCategory> {
}
