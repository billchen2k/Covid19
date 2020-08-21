package com.covid19.backend.service.prescription;

import com.covid19.backend.model.Result;
import com.covid19.backend.model.Prescription;
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
public class DeletePrescriptionService extends BaseService {

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
     * 提供根据医生ID删除医生的服务
     * @param prescription_id
     * @return
     */
    public int deletePrescriptionByID(long prescription_id)
    {
        prescriptionMapper.deletePrescriptionByID(prescription_id);
        return 0;
    }


    /**
     * 提供根据药品信息删除病人的服务
     * @param patient_id
     * @param medicine_id
     * @param dosage
     * @param usage
     * @return
     */
    public int deletePrescription(
            String patient_id,
            String medicine_id,
            String dosage,
            String usage,
            String doctor_id
    )
    {

        ArrayList<Prescription> prescriptions = prescriptionMapper.selectPrescription(
                patient_id,
                medicine_id,
                dosage,
                usage,
                doctor_id);
        for(Prescription prescription: prescriptions){
            deletePrescriptionByID(prescription.getPrescription_id());
        }
        return 0;
    }

}
