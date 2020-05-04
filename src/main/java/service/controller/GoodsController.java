package service.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.service.GoodsService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/wx/goods/list", method = RequestMethod.GET)
    public String getGoodsList(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("X-Librecmall-Token");
        boolean isNew = Boolean.valueOf(httpServletRequest.getParameter("isNew"));
        boolean isHot = Boolean.valueOf(httpServletRequest.getParameter("isHot"));
        String keyword = httpServletRequest.getParameter("keyword");
        int brandId = Integer.valueOf(httpServletRequest.getParameter("brandId"));
        int categoryId = Integer.valueOf(httpServletRequest.getParameter("categoryId"));
        int page = Integer.valueOf(httpServletRequest.getParameter("page"));
        int limit = Integer.valueOf(httpServletRequest.getParameter("limit"));
        String sort = httpServletRequest.getParameter("sort");
        String order = httpServletRequest.getParameter("order");
        return goodsService.getGoodsList(header, isNew, isHot, keyword, brandId,
                categoryId, page, limit, sort, order);
    }

    @RequestMapping(value = "/admin/goods/list", method = RequestMethod.GET)
    public String getGoodsListAdmin(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("X-Librecmall-Admin-Token");
        int goodsId = Integer.valueOf(httpServletRequest.getParameter("goodsId"));
        String goodsSn = httpServletRequest.getParameter("goodsSn");
        String name = httpServletRequest.getParameter("name");
        int page = Integer.valueOf(httpServletRequest.getParameter("page"));
        int limit = Integer.valueOf(httpServletRequest.getParameter("limit"));
        String sort = httpServletRequest.getParameter("sort");
        String order = httpServletRequest.getParameter("order");
        return goodsService.getGoodsListAdmin(header, goodsId, goodsSn, name, page, limit, sort, order);
    }

    @RequestMapping(value = "/wx/goods/detail", method = RequestMethod.GET)
    public String getGoodsDetail(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("X-Librecmall-Token");
        int goodsId = Integer.valueOf(httpServletRequest.getParameter("id"));
        return goodsService.getGoodsDetail(header, goodsId);
    }

    @RequestMapping(value = "/wx/goods/count", method = RequestMethod.GET)
    public String getGoodsCount(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("X-Librecmall-Token");
        return goodsService.getGoodsCount(header);
    }

    @RequestMapping(value = "/admin/goods/update", method = RequestMethod.POST)
    public String updateGoodsDetail(HttpServletRequest httpServletRequest, @RequestBody JSONObject jsonObject) {
        String header = httpServletRequest.getHeader("X-Librecmall-Admin-Token");
        JSONArray goodsArray = jsonObject.getJSONObject("goods").getJSONArray("goods");
        JSONArray specifications = jsonObject.getJSONArray("specifications");
        JSONArray products = jsonObject.getJSONArray("products");
        JSONArray attributes = jsonObject.getJSONArray("attributes");
        return goodsService.updateGoodsDetail(header, goodsArray, specifications, products, attributes);
    }

    @RequestMapping(value = "/admin/goods/create", method = RequestMethod.POST)
    public String createGoodsDetail(HttpServletRequest httpServletRequest, @RequestBody JSONObject jsonObject) {
        String header = httpServletRequest.getHeader("X-Librecmall-Admin-Token");
        JSONArray goodsArray = jsonObject.getJSONObject("goods").getJSONArray("goods");
        JSONArray specifications = jsonObject.getJSONArray("specifications");
        JSONArray products = jsonObject.getJSONArray("products");
        JSONArray attributes = jsonObject.getJSONArray("attributes");
        return goodsService.createGoodsDetail(header, goodsArray, specifications, products, attributes);
    }

    @RequestMapping(value = "/admin/goods/delete", method = RequestMethod.POST)
    public String deleteGoods(HttpServletRequest httpServletRequest, @RequestBody JSONObject jsonObject) {
        String header = httpServletRequest.getHeader("X-Librecmall-Admin-Token");
        int goodsId = jsonObject.getInteger("id");
        return goodsService.deleteGoods(header, goodsId);
    }

    @RequestMapping(value = "/admin/goods/detail", method = RequestMethod.GET)
    public String getGoodsDetailAdmin(HttpServletRequest httpServletRequest, @RequestBody JSONObject jsonObject) {
        String header = httpServletRequest.getHeader("X-Librecmall-Admin-Token");
        int goodsId = jsonObject.getInteger("id");
        return goodsService.getGoodsDetailAdmin(header, goodsId);
    }
}
