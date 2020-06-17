package com.covid19.backend.service.image;

import com.covid19.backend.model.Result;
import com.covid19.backend.model.Image;
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


@Service
public class DeleteImageService extends BaseService {

    public int checkCurrentUserInfo(HttpServletRequest request)
    {
        Long uid = Authentication.getCurrentUid(request);
        if (uid == null || uid != 0) return -1;
        return 0;
    }


    public int deleteImageByID(long image_id)
    {
        imageMapper.deleteImageByID(image_id);
        return 0;
    }

    public int deleteImage(Image image)
    {

        ArrayList<Image> images = imageMapper.selectImage(image);
        for(Image t_image: images){
            deleteImageByID(t_image.getImage_id());
        }
        return 0;
    }

}
