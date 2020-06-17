package com.covid19.backend.service.patient;

import com.covid19.backend.model.Result;
import com.covid19.backend.model.Patient;
import com.covid19.backend.service.BaseService;
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
 * 删除病人信息的服务
 */
@Service
public class DeletePatientService extends BaseService {
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
     * 提供根据病人ID删除病人的服务
     * @param patient_id
     * @return
     */
    public int deletePatientByID(long patient_id)
    {
        patientMapper.deletePatientByID(patient_id);
        return 0;
    }
    /**
     * 提供根据病人信息删除病人的服务
     * @param name
     * @param gender
     * @param birthday
     * @param onset_place
     * @param onset_date
     * @param confirm_date
     * @param status
     * @param doctor_id
     * @param hospital_id
     * @param is_doctor
     * @return
     */
    public int deletePatient(
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

        ArrayList<Patient> patients = patientMapper.selectPatient(
                name,
                gender,
                birthday,
                onset_place,
                onset_date,
                confirm_date,
                status,
                doctor_id,
                hospital_id,
                is_doctor
        );
        for(Patient patient: patients){
            deletePatientByID(patient.getPatient_id());
        }
        return 0;
    }

}
