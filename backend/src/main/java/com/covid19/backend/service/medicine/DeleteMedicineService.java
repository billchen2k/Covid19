package com.covid19.backend.service.medicine;

import com.covid19.backend.controller.BaseController;
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
 * 删除病人信息的服务
 */
@Service
public class DeleteMedicineService extends BaseService {
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
        if (uid == null) return -1;
        return 0;
    }

    /**
     * 提供根据医生ID删除医生的服务
     * @param medicine_id
     * @return
     */
    public int deleteMedicineByID(long medicine_id)
    {
        medicineMapper.deleteMedicineByID(medicine_id);
        return 0;
    }


    /**
     * 提供根据药品信息删除病人的服务
     * @param name
     * @param manufacturer
     * @param introduction
     * @param type
     * @return
     */
    public int deleteDoctor(
            String name,
            String manufacturer,
            String introduction,
            String type
    )
    {

        ArrayList<Medicine> medicines = medicineMapper.selectMedicine(
                name,
                manufacturer,
                introduction,
                type);
        for(Medicine medicine: medicines){
            deleteMedicineByID(medicine.getMedicine_id());
        }
        return 0;
    }

}
