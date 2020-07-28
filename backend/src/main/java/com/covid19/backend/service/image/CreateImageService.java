package com.covid19.backend.service.image;

import com.covid19.backend.model.Image;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class CreateImageService extends BaseService{

    public int checkCurrentUserInfo(HttpServletRequest request)
    {
        Long uid = Authentication.getCurrentUid(request);
        if (uid == null || uid != 0) return -1;
        return 0;
    }

    public Image createImage(Image image)
    {
        imageMapper.insertImage(image);
        return image;
    }
}

