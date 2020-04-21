package ${targetPackage};

import tk.mybatis.mapper.common.Mapper;
import org.springframework.stereotype.Repository;
import ${tableClass.fullClassName};

<#assign dateTime = .now>
/**
 * @author Ye Minghui
 * @date ${dateTime?string["yyyy/MM/dd ahh:mm"]}
 */
@Repository
public interface ${tableClass.shortClassName}Mapper extends Mapper<${tableClass.shortClassName}> {

}