package ${targetPackage};

import tk.mybatis.mapper.common.Mapper;
import org.springframework.stereotype.Repository;
import ${tableClass.fullClassName};

<#assign dateTime = .now>
/**
 * @author Ye Minghui
 */
@Repository
public interface ${tableClass.shortClassName}Mapper extends Mapper<${tableClass.shortClassName}> {

}