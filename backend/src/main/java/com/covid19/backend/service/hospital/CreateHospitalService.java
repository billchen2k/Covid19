package com.covid19.backend.service.hospital;

import com.covid19.backend.model.Hospital;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class CreateHospitalService extends BaseService{

    public int checkCurrentUserInfo(HttpServletRequest request)
    {
        Long uid = Authentication.getCurrentUid(request);
        if (uid == null) return -1;
        return 0;
    }

    public Hospital createHospital(Hospital hospital)
    {
        hospitalMapper.insertHospital(hospital);
        return hospital;
    }
}

