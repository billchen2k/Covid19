package com.covid19.backend.controller.hospital;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Hospital;
import com.covid19.backend.service.hospital.GetHospitalInfoService;
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
import java.util.ArrayList;


@RestController
@Api(tags = "医院控制器", value = "和医院有关的控制器")
public class GetHospitalInfo {
    @Autowired
    public GetHospitalInfoService getHospitalInfoService;

    @PostMapping("/hospital/getHospitalInfoByID")
    @ApiOperation(value = "根据医院ID获取医院信息", notes = "可以根据医院ID获取医院信息")
    @ApiImplicitParam(name = "hospital_id",value = "医院ID")
    public Result<Hospital> getHospitalInfoByID(
            @RequestParam(value = "hospital_id") long hospital_id
    )
    {
        Hospital hospital = getHospitalInfoService.geHospitalInfoByID(hospital_id);
        if(hospital == null) return Result.error(2012,"不存在该医院");
        return Result.ok(hospital);
    }

    @PostMapping("/hospital/getHospitalInfo")
    @ApiOperation(value = "根据医院除ID外属性获取医院信息", notes = "可以根据医院除ID外属性获取医院信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "医院名称"),
            @ApiImplicitParam(name = "address",value = "医院地址"),
    })
    public Result<ArrayList<Hospital>> getHospitalInfo(
            @RequestParam(value = "name",required = false)String name,
            @RequestParam(value = "address",required = false)String address
    )
    {
        Hospital hospital = new Hospital();

        hospital.setName(name);
        hospital.setAddress(address);

        ArrayList<Hospital> list = getHospitalInfoService.getHospitalInfo(hospital);
        if (list == null) return null;
        return Result.ok(list);
    }
}
