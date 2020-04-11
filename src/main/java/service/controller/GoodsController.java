package service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.service.GoodsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/wx/goods/list", method = RequestMethod.GET)
    public String getGoodsList(HttpServletRequest httpServletRequest) {
        boolean isNew = Boolean.valueOf(httpServletRequest.getParameter("isNew"));
        boolean isHot = Boolean.valueOf(httpServletRequest.getParameter("isHot"));
        String keyword = httpServletRequest.getParameter("keyword");
        int brandId = Integer.valueOf(httpServletRequest.getParameter("brandId"));
        int categoryId = Integer.valueOf(httpServletRequest.getParameter("categoryId"));
        int page = Integer.valueOf(httpServletRequest.getParameter("page"));
        int limit = Integer.valueOf(httpServletRequest.getParameter("limit"));
        String sort = httpServletRequest.getParameter("sort");
        String order = httpServletRequest.getParameter("order");
        return goodsService.getGoodsList(isNew, isHot, keyword, brandId,
                categoryId, page, limit, sort, order);
    }

    @RequestMapping(value = "/wx/goods/detail", method = RequestMethod.GET)
    public String getGoodsDetail(HttpSession session, HttpServletRequest httpServletRequest) {
        int goodsId = Integer.valueOf(httpServletRequest.getParameter("id"));
        return goodsService.getGoodsDetail(session.getId(), goodsId);
    }

    @RequestMapping(value = "/wx/goods/info", method = RequestMethod.GET)
    public String getGoodsInfo(HttpServletRequest httpServletRequest) {
        int id = Integer.valueOf(httpServletRequest.getParameter("id"));
        int page = Integer.valueOf(httpServletRequest.getParameter("page"));
        int limit = Integer.valueOf(httpServletRequest.getParameter("limit"));
        String sort = httpServletRequest.getParameter("sort");
        String order = httpServletRequest.getParameter("order");
        return goodsService.getGoodsInfo(id, page, limit, sort, order);
    }

    @RequestMapping(value = "/wx/goods/related", method = RequestMethod.GET)
    public String getGoodsRelated(HttpServletRequest httpServletRequest) {
        int id = Integer.valueOf(httpServletRequest.getParameter("id"));
        int page = Integer.valueOf(httpServletRequest.getParameter("page"));
        int limit = Integer.valueOf(httpServletRequest.getParameter("limit"));
        String sort = httpServletRequest.getParameter("sort");
        String order = httpServletRequest.getParameter("order");
        return goodsService.getGoodsRelated(id, page, limit, sort, order);
    }

    @RequestMapping(value = "/wx/goods/count", method = RequestMethod.GET)
    public String getGoodsCount() {
        return goodsService.getGoodsCount();
    }

}
