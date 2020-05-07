package service.controller.recommender;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.service.PersonalizedRecommenderService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PersonalizedRecommenderController {

    @Autowired
    private PersonalizedRecommenderService personalizedRecommenderService;

    @RequestMapping(value = "/wx/goods/related")
    public String recommendRelated(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("X-Librecmall-Token");
        Integer userId = Integer.valueOf(httpServletRequest.getParameter("userId"));
        return personalizedRecommenderService.recommendRelated(header, userId);
    }

    @RequestMapping(value = "/wx/recommend/update")
    public String recommendUpdate(HttpServletRequest httpServletRequest, @RequestBody JSONObject jsonObject) {
        String header = httpServletRequest.getHeader("X-Librecmall-Token");
        Integer userId = Integer.valueOf(httpServletRequest.getParameter("userId"));
        JSONArray items = jsonObject.getJSONArray("items");
        return personalizedRecommenderService.updateRecommend(header, userId, items);
    }

}
