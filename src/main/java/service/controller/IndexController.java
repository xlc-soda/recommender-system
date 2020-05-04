package service.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.service.IndexService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IndexController extends BaseController {

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "/wx/home/index", method = RequestMethod.GET)
    public String index(HttpServletRequest httpServletRequest, @RequestBody JSONObject jsonObject) {
        String header = httpServletRequest.getHeader("X-Librecmall-Token");
        return indexService.index(header, jsonObject, startPage, pageLimit);
    }
}
