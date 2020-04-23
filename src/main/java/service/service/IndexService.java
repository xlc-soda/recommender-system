package service.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;

import java.util.List;

@Service
public class IndexService {
    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GrouponMapper grouponMapper;

    @Autowired
    private JsonService jsonService;

    @Autowired
    private TopicMapper topicMapper;

    public String index(String sessionId, JSONObject jsonObject, int page, int limit) {
        JSONObject data = new JSONObject();
        List<Goods> latestGoods = goodsMapper.getLatestGoods(page * limit, limit);
        JSONArray newGoodsList = new JSONArray();
        for(Goods goods: latestGoods) {
            JSONObject object = new JSONObject();
            object.put("id", goods.getId());
            object.put("name", goods.getName());
            object.put("brief", goods.getBrief());
            object.put("picUrl", goods.getPicUrl());
            object.put("isNew", goods.getIsNew());
            object.put("isHot", goods.getIsHot());
            object.put("counterPrice", goods.getCounterPrice());
            object.put("retailPrice", goods.getRetailPrice());
            newGoodsList.add(object);
        }
        data.put("newGoodsList", newGoodsList);
        List<Coupon> coupons = couponMapper.getAllCoupons(page * limit, limit);
        JSONArray couponList = new JSONArray();
        for(Coupon coupon: coupons) {
            JSONObject object = new JSONObject();
            object.put("id", coupon.getId());
            object.put("name", coupon.getName());
            object.put("desc", coupon.getDesc());
            object.put("tag", coupon.getTag());
            object.put("discount", coupon.getDiscount());
            object.put("min", coupon.getMin());
            object.put("days", coupon.getDays());
            couponList.add(object);
        }
        data.put("couponList", couponList);
        // TODO: channel
        data.put("channel", "");
        List<Groupon> allGroupons = grouponMapper.getAllGroupons(page * limit, limit);
        JSONArray grouponList = new JSONArray();
        /*
        for(Groupon groupon: allGroupons) {
            JSONObject object = new JSONObject();
            object.put("id", groupon.getId());
            object.put("name", groupon.get());
            object.put("brief", groupon.getb());
            object.put("desc", coupon.getDesc());
            object.put("tag", coupon.getTag());
            object.put("discount", coupon.getDiscount());
            object.put("min", coupon.getMin());
            object.put("days", coupon.getDays());
            grouponList.add(object);
        }
        */
        // TODO: grouponList
        data.put("grouponList", grouponList);
        // TODO: banner
        JSONArray banner = new JSONArray();
        data.put("banner", banner);
        // brandList
        List<Brand> allBrands = brandMapper.getAllBrands(page * limit, limit);
        JSONArray brandList = new JSONArray();
        for(Brand brand: allBrands) {
            JSONObject object = new JSONObject();
            object.put("id", brand.getId());
            object.put("name", brand.getName());
            object.put("desc", brand.getDesc());
            object.put("picUrl", brand.getPicUrl());
            object.put("floorPrice", brand.getFloorPrice());
            brandList.add(object);
        }
        data.put("brandList", brandList);
        // hotGoodsList
        List<Goods> hotGoods = goodsMapper.getHotGoods(page * limit, limit);
        JSONArray hotGoodsList = new JSONArray();
        for(Goods goods: hotGoods) {
            JSONObject object = new JSONObject();
            object.put("id", goods.getId());
            object.put("name", goods.getName());
            object.put("brief", goods.getBrief());
            object.put("picUrl", goods.getPicUrl());
            object.put("isNew", goods.getIsNew());
            object.put("isHot", goods.getIsHot());
            object.put("counterPrice", goods.getCounterPrice());
            object.put("retailPrice", goods.getRetailPrice());
            hotGoodsList.add(object);
        }
        data.put("hotGoodsList", hotGoodsList);
        List<Topic> allTopics = topicMapper.getAllTopics(page * limit, limit);
        JSONArray topicList = new JSONArray();
        for(Topic topic: allTopics) {
            JSONObject object = new JSONObject();
            object.put("id", topic.getId());
            object.put("title", topic.getTitle());
            object.put("subtitle", topic.getSubtitle());
            object.put("price", topic.getPrice());
            object.put("readCount", topic.getReadCount());
            object.put("picUrl", topic.getPicUrl());
            topicList.add(object);
        }
        data.put("topicList", topicList);
        List<Goods> floorGoods = goodsMapper.getFloorGoods();
        int category_id = floorGoods.get(0).getCategoryId();
        int count = 0;
        JSONArray floorGoodsList = new JSONArray();
        JSONObject list = new JSONObject();
        Category category = categoryMapper.selectByPrimaryKey(category_id);
        list.put("name", null == category? "": category.getName());
        JSONArray array = new JSONArray();
        for(Goods goods: floorGoods) {
            if(count == limit) {
                if(goods.getCategoryId() == category_id) {
                    continue;
                } else {
                    list.put("goodsList", array);
                    list.put("id", category_id);
                    floorGoodsList.add(list);
                    list = new JSONObject();
                    category_id = goods.getCategoryId();
                    category = categoryMapper.selectByPrimaryKey(category_id);
                    list.put("name", null == category? "": category.getName());
                    array = new JSONArray();
                    count = 0;
                }
            }
            JSONObject object = new JSONObject();
            object.put("id", goods.getId());
            object.put("name", goods.getName());
            object.put("brief", goods.getBrief());
            object.put("picUrl", goods.getPicUrl());
            object.put("isNew", goods.getIsNew());
            object.put("isHot", goods.getIsHot());
            object.put("counterPrice", goods.getCounterPrice());
            object.put("retailPrice", goods.getRetailPrice());
            array.add(object);
        }
        data.put("floorGoodsList", floorGoodsList);
        return jsonService.getJsonResult(0, "成功", data);
    }
}
