package top.codermhc.drugmanager.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.codermhc.drugmanager.base.entity.Inventory;
import top.codermhc.drugmanager.base.entity.InventoryChange;
import top.codermhc.drugmanager.base.entity.MedicalCase;
import top.codermhc.drugmanager.base.entity.Patient;
import top.codermhc.drugmanager.base.entity.Prescription;
import top.codermhc.drugmanager.base.entity.PrescriptionDetail;
import top.codermhc.drugmanager.base.service.InventoryChangeService;
import top.codermhc.drugmanager.base.service.InventoryService;
import top.codermhc.drugmanager.base.service.MedicalCaseService;
import top.codermhc.drugmanager.base.service.PatientService;
import top.codermhc.drugmanager.base.service.PrescriptionDetailService;
import top.codermhc.drugmanager.base.service.PrescriptionService;
import top.codermhc.drugmanager.exception.DrugmanagerException;
import top.codermhc.drugmanager.utils.ResponseData;

@RestController
public class YiLiaoLiuCheng extends BaseController {

    @Resource(name = "patientServiceImpl")
    private PatientService patientService;

    @Resource(name = "medicalCaseServiceImpl")
    private MedicalCaseService medicalCaseService;

    @Resource(name = "prescriptionServiceImpl")
    private PrescriptionService prescriptionService;

    @Resource(name = "prescriptionDetailServiceImpl")
    private PrescriptionDetailService prescriptionDetailService;

    @Resource(name = "inventoryServiceImpl")
    private InventoryService inventoryService;

    @Resource(name = "inventoryChangeServiceImpl")
    private InventoryChangeService inventoryChangeService;

    @PostMapping("/geiyao")
    public Object geiyao(@RequestBody Map<String, Object> map) {
        String name = (String) map.get("name");
        String identity = (String) map.get("identity");

        if (name == null || identity == null) {
            return new ResponseData(HttpStatus.BAD_REQUEST, "病人信息不全", null);
        }

        Patient one = patientService.getOne(Wrappers.<Patient>lambdaQuery().eq(Patient::getIdentity, identity));
        if (one == null) {
            Patient patient = new Patient();
            patient.setIdentity(identity);
            patient.setName(name);
            patientService.save(patient);
            one = patient;
        }

        MedicalCase medicalCase = new MedicalCase();
        medicalCase.setPatientId(one.getId());
        medicalCase.setAge(computeAgeFromIdentity(identity));
        medicalCase.setDescribes((String) map.get("describes"));

        medicalCaseService.save(medicalCase);

        List<Map<String, Object>> drugs = (List<Map<String, Object>>) map.get("drugs");
        if (drugs == null) {
            return null;
        }


        Prescription prescription = new Prescription();
        prescription.setMedicalCaseId(medicalCase.getId());
        prescription.setPatientId(one.getId());

        prescriptionService.save(prescription);

        for (Map<String, Object> drug : drugs) {
            // gson 会把整型转浮点...
            double tempid = (Double) drug.get("id");
            long id = (long) tempid;

            String drugName = (String) drug.get("drugName");

            if (!doesDrugCouldUse(id)) {
                throw new DrugmanagerException("你不能用药品 ["+ drugName +"], 该操作将被记录。");
            }

            int dose = Integer.valueOf((String) drug.get("dose"));

            int times = Integer.valueOf((String) drug.get("times"));
            PrescriptionDetail prescriptionDetail = new PrescriptionDetail();

            prescriptionDetail.setDrugId(id);
            prescriptionDetail.setDose(dose);
            prescriptionDetail.setPrescriptionId(prescription.getId());
            prescriptionDetail.setTimes(times);
            prescriptionDetailService.save(prescriptionDetail);

            List<Inventory> list = inventoryService
                .list(Wrappers.<Inventory>lambdaQuery().eq(Inventory::getDrugId, prescriptionDetail.getDrugId())
                    .orderByAsc(Inventory::getExpDate));

            int need = dose * times;
            for (Inventory inventory : list) {
                if (inventory.getAmount() < need) {
                    InventoryChange inventoryChange = new InventoryChange();
                    inventoryChange.setAmountDelta(inventory.getAmount());
                    inventoryChange.setInventoryId(inventory.getId());
                    inventoryChange.setOperateMethod("geiyao");
                    inventoryChange.setOperatorId(user().getId());
                    inventoryChange.setOperateTime(LocalDateTime.now());

                    need -= inventory.getAmount();
                    inventoryService.removeById(inventory.getId());
                    inventoryChangeService.save(inventoryChange);
                } else {
                    int amount1 = inventory.getAmount();
                    amount1 -= need;
                    inventory.setAmount(amount1);

                    InventoryChange inventoryChange = new InventoryChange();
                    inventoryChange.setInventoryId(inventory.getId());
                    inventoryChange.setOperateMethod("geiyao");
                    inventoryChange.setOperatorId(user().getId());
                    inventoryChange.setOperateTime(LocalDateTime.now());
                    inventoryChange.setAmountDelta(need);

                    inventoryService.updateById(inventory);
                    inventoryChangeService.save(inventoryChange);
                    need = 0;
                    break;
                }
            }
        }

        return null;
    }

    private boolean doesDrugCouldUse(Long id) {
        // todo check depart drugs

        return true;
    }

    private int computeAgeFromIdentity(String identity) {
        String age = identity.substring(6, 14);
        LocalDate now = LocalDate.now();
        LocalDate birth = LocalDate.parse(age, DateTimeFormatter.ofPattern("yyyyMMdd"));
        java.time.Period period = Period.between(birth, now);
        return period.getYears();
    }

}
