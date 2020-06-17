package com.covid19.backend.service.image;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Image;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.service.image.GetImageInfoService;
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

@Service
public class UpdateImageService extends BaseService{
    @Autowired
    public GetImageInfoService getImageInfoService;

    public int checkCurrentUserInfo(HttpServletRequest request)
    {
        Long uid = Authentication.getCurrentUid(request);
        if (uid == null || uid != 0) return -1;
        return 0;
    }

    public Image updateImage(Image new_image)
    {
        Image image = getImageInfoService.getImageInfoByID(new_image.getImage_id());

        image.setPatient_id(new_image.getPatient_id());
        image.setScanning_doctor_id(new_image.getScanning_doctor_id());
        image.setImage_picture(new_image.getImage_picture());
        image.setConclusion(new_image.getConclusion());
        image.setConclusion_doctor_id(new_image.getConclusion_doctor_id());

        imageMapper.updateImage(image);
        return image;
    }
}
