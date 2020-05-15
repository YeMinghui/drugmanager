package top.codermhc.drugmanager.mapper;

import tk.mybatis.mapper.common.Mapper;
import org.springframework.stereotype.Repository;
import top.codermhc.drugmanager.back.entity.Patient;

/**
 * @author Ye Minghui
 */
@Repository
public interface PatientMapper extends Mapper<Patient> {

}
