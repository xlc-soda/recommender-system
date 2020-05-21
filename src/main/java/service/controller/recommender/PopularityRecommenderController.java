package service.controller.recommender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.service.PopularityRecommenderService;

import javax.servlet.http.HttpSession;

@RestController
public class PopularityRecommenderController {

    @Autowired
    private PopularityRecommenderService popularityRecommenderService;

    /**
     * 获取推荐结果
     * @param session
     * @return
     */
    @RequestMapping(name = "/check", method = {RequestMethod.GET, RequestMethod.POST})
    public String getRecommendResult(HttpSession session) {
        return popularityRecommenderService.getRecommendResult(session.getId());
    }
}
