package com.covid19.backend.service.prescription;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Prescription;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.service.prescription.GetPrescriptionInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.covid19.backend.utils.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * 修改医生信息的服务
 */
@Service
public class UpdatePrescriptionService extends BaseService{
    @Autowired
    public GetPrescriptionInfoService getPrescriptionInfoService;
    /**
     * 检查用户是否登陆
     * @param request
     * @return
     */
    public int checkCurrentUserInfo(HttpServletRequest request)
    {
        Long uid = Authentication.getCurrentUid(request);
        if (uid == null) return -1;
        return 0;
    }

    public Prescription updatePrescription(
            long prescription_id,
            String patient_id,
            String medicine_id,
            String dosage,
            String usage,
            String doctor_id
    )
    {
        Prescription prescription = getPrescriptionInfoService.gePrescriptionInfoByID(prescription_id);
        prescription.setPatient_id(patient_id).setMedicine_id(medicine_id).setDosage(dosage).setUsage(usage).setDoctor_id(doctor_id);
        prescriptionMapper.updatePrescription(prescription);
        return prescription;
    }
}
