package com.covid19.backend.controller.hospital;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Hospital;
import com.covid19.backend.service.hospital.GetHospitalInfoService;
import com.covid19.backend.service.hospital.UpdateHospitalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "医院控制器", value = "和医院有关的控制器")
public class UpdateHospital extends BaseController{
    @Autowired
    public UpdateHospitalService updateHospitalService;
    @Autowired
    public GetHospitalInfoService getHospitalInfoService;

    @PostMapping("/hospital/updateHospital")
    @ApiOperation(value = "更新医院信息", notes = "可以更新医院信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hospital_id",value = "医院ID"),
            @ApiImplicitParam(name = "name",value = "医院名称"),
            @ApiImplicitParam(name = "address",value = "医院地址"),
    })
    public Result updateHospital(
            @RequestParam(value = "hospital_id") long hospital_id,
            @RequestParam(value = "name",required = false)String name,
            @RequestParam(value = "address",required = false)String address,
            HttpServletRequest request)
    {
        if(updateHospitalService.checkCurrentUserInfo(request) == -1)
            return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");
        Hospital Hospital = getHospitalInfoService.geHospitalInfoByID(hospital_id);
        if(Hospital == null) return Result.error(2012,"不存在该医院");

        Hospital new_hospital = new Hospital();
        new_hospital.setHospital_id(hospital_id);
        new_hospital.setHospital_name(name);
        new_hospital.setAddress(address);

        updateHospitalService.updateHospital(new_hospital);
        return Result.ok(hospital_id);
    }
}
