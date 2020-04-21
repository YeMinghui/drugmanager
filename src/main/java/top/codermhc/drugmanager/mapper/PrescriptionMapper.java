package top.codermhc.drugmanager.mapper;

import tk.mybatis.mapper.common.Mapper;
import org.springframework.stereotype.Repository;
import top.codermhc.drugmanager.entity.Prescription;

/**
 * @author Ye Minghui
 * @date 2020/04/21 下午10:43
 */
@Repository
public interface PrescriptionMapper extends Mapper<Prescription> {

}
