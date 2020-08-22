package com.covid19.backend.service.doctor;

import com.covid19.backend.model.Result;
import com.covid19.backend.model.Doctor;
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
public class DeleteDoctorService extends BaseService {
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

    /**
     * 提供根据医生ID删除医生的服务
     * @param doctor_id
     * @return
     */
    public int deleteDoctorByID(long doctor_id)
    {
        doctorMapper.deleteDoctorByID(doctor_id);
        return 0;
    }

    /**
     * 提供根据病人信息删除病人的服务
     * @param name
     * @param gender
     * @param birthday
     * @param department
     * @param hospital_id
     * @return
     */
    public int deleteDoctor(
            String name,
            String gender,
            String birthday,
            String department,
            String hospital_id
    )
    {

        ArrayList<Doctor> doctors = doctorMapper.selectDoctor(
                name,
                gender,
                birthday,
                department,
                hospital_id
        );
        for(Doctor doctor: doctors){
            deleteDoctorByID(doctor.getDoctor_id());
        }
        return 0;
    }

}
