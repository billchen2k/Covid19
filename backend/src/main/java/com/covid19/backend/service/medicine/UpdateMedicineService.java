package com.covid19.backend.service.medicine;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.controller.medicine.GetMedicineInfo;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Medicine;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.service.medicine.GetMedicineInfoService;
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
public class UpdateMedicineService extends BaseService{
    @Autowired
    public GetMedicineInfoService getMedicineInfoService;

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

    public Medicine updateMedicine(
            long doctor_id,
            String name,
            String manufacturer,
            String introduction,
            String type
    )
    {
        Medicine medicine = getMedicineInfoService.getMedicineInfoByID(doctor_id);
        medicine.setName(name).setManufacturer(manufacturer).setIntroduction(introduction).setType(type);
        medicineMapper.updateMedicineInfo(medicine);
        return medicine;
    }

}
