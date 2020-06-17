package com.covid19.backend.controller.hospital;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Hospital;
import com.covid19.backend.service.hospital.CreateHospitalService;
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
public class CreateHospital extends BaseController{
    @Autowired
    public CreateHospitalService createHospitalService;

    @PostMapping("/hospital/createHospital")
    @ApiOperation(value = "创建医院信息", notes = "可以创建医院信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "医院名称"),
            @ApiImplicitParam(name = "address",value = "医院地址"),
    })
    public Result createMedicine(
            @RequestParam(value = "name",required = false)String name,
            @RequestParam(value = "address",required = false)String address,
            HttpServletRequest request)
    {
        if(createHospitalService.checkCurrentUserInfo(request) == -1)
            return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");

        Hospital hospital = new Hospital();

        hospital.setName(name);
        hospital.setAddress(address);

        Hospital new_hospital = createHospitalService.createHospital(hospital);
        return Result.ok(new_hospital);
    }
}
