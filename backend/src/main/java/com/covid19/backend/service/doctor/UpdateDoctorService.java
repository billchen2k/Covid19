package com.covid19.backend.service.doctor;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Doctor;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.service.doctor.GetDoctorInfoService;
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
public class UpdateDoctorService extends BaseService{
    @Autowired
    public GetDoctorInfoService getDoctorInfoService;
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

    public Doctor updateDoctor(
            long doctor_id,
            String name,
            String gender,
            String birthday,
            String department,
            String hospital_id
    )
    {
        Doctor doctor = getDoctorInfoService.getDoctorInfoByID(doctor_id);
        doctor.setDoctor_name(name).setDoctor_gender(gender).setDoctor_birthday(birthday).setDepartment(department).setHospital_id(hospital_id);
        doctorMapper.updateDoctorInfo(doctor);
        return doctor;
    }

}
