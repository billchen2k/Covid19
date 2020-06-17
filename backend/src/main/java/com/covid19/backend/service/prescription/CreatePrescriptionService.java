package com.covid19.backend.service.prescription;

import com.covid19.backend.model.Prescription;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class CreatePrescriptionService extends BaseService{

    /**
     * 检查用户是否登陆
     * @param request
     * @return
     */
    public int checkCurrentUserInfo(HttpServletRequest request)
    {
        Long uid = Authentication.getCurrentUid(request);
        if (uid == null || uid != 0) return -1;
        return 0;
    }

    /**
     * 提供创建用药的服务
     * @param patient_id
     * @param medicine_id
     * @param dosage
     * @param usage
     * @return
     */
    public Prescription createPrescription(
            String patient_id,
            String medicine_id,
            String dosage,
            String usage
    )
    {
        Prescription prescription = new Prescription();
        prescription.setPatient_id(patient_id).setMedicine_id(medicine_id).setDosage(dosage).setUsage(usage);
        long id = prescriptionMapper.insertPrescription(prescription);
        prescription.setPrescription_id(id);
        return prescription;
    }
}

