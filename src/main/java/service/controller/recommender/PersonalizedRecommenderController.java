package service.controller.recommender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import service.service.PersonalizedRecommenderService;

import javax.servlet.http.HttpServletRequest;

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
    public String recommendUpdate(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("X-Librecmall-Token");
        Integer userId = Integer.valueOf(httpServletRequest.getParameter("userId"));
        return personalizedRecommenderService.updateRecommend(header, userId);
    }

}
