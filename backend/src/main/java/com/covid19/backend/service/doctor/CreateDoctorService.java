package com.covid19.backend.service.doctor;

import com.covid19.backend.model.Doctor;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class CreateDoctorService extends BaseService{
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
     * 提供创建医生的服务
     * @param name
     * @param gender
     * @param birthday
     * @param department
     * @param hospital_id
     * @return
     */
    public Doctor createDoctor(
            String name,
            String gender,
            String birthday,
            String department,
            String hospital_id
    )
    {
        Doctor doctor = new Doctor();
        doctor.setDoctor_name(name).setDoctor_gender(gender).setDoctor_birthday(birthday).setDepartment(department).setHospital_id(hospital_id);
        doctorMapper.insertDoctor(doctor);
        return doctor;
    }
}

