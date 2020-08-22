package com.covid19.backend.service.patient;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Patient;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.service.patient.GetPatientInfoService;
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
 * 修改病人信息的服务
 */
@Service
public class UpdatePatientService extends BaseService{
    @Autowired
    public GetPatientInfoService getPatientInfoService;
    /**
     * 检查用户是否登陆
     * @param request
     * @return
     */
    public int checkCurrentUserInfo(HttpServletRequest request)
    {
        Long uid = Authentication.getCurrentUid(request);
        if (uid == null || uid == 0) return -1;
        return 0;
    }
    public Patient updatePatient(
            long patient_id,
            String name,
            String gender,
            String birthday,
            String onset_place,
            String onset_date,
            String confirm_date,
            String status,
            String doctor_id,
            String hospital_id,
            String is_doctor
    )
    {
        Patient patient = getPatientInfoService.getPatientInfoByID(patient_id);
        patient.setPatient_name(name).setPatient_gender(gender).setPatient_birthday(birthday).setOnset_place(onset_place).setOnset_date(onset_date);
        patient.setConfirm_date(confirm_date).setStatus(status).setDoctor_id(doctor_id).setHospital_id(hospital_id).setIs_doctor(is_doctor);
        patientMapper.updatePatientInfo(patient);
        return patient;
    }

}
