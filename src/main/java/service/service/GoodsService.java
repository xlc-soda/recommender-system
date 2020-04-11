package service.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsAttributeMapper goodsAttributeMapper;

    @Autowired
    private GoodsProductMapper goodsProductMapper;

    @Autowired
    private GoodsSpecificationMapper goodsSpecificationMapper;

    @Autowired
    private IssueMapper issueMapper;

    @Autowired
    private JsonService jsonService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    public String getGoodsList(boolean isNew, boolean isHot, String keyword, int brandId, int categoryId, int page,
                               int limit, String sort, String order) {
        List<Goods> goodsList = goodsMapper.getGoodsList(isNew, isHot, keyword,
                brandId, categoryId, page, limit * page, sort, order);
        JSONObject data = new JSONObject();
        data.put("total", goodsList.size());
        data.put("pages", page);
        data.put("limit", limit);
        JSONArray jsonArray = new JSONArray();
        for (Goods goods : goodsList) {
            JSONObject item = new JSONObject();
            item.put("id", goods.getId());
            item.put("name", goods.getId());
            item.put("brief", goods.getBrief());
            item.put("picUrl", goods.getPicUrl());
            item.put("isNew", goods.getIsNew());
            item.put("isHot", goods.getIsHot());
            item.put("counterPrice", goods.getCounterPrice());
            item.put("retailPrice", goods.getRetailPrice());
            jsonArray.add(item);
        }
        data.put("list", jsonArray);
        return jsonService.getJsonResult(0, "成功", data);
    }

    public String getGoodsDetail(String sessionId, Integer goodsId) {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        JSONObject data = new JSONObject();
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationMapper.selectByGoodsId(goodsId);
        Collections.sort(goodsSpecificationList, new Comparator<GoodsSpecification>() {
            @Override
            public int compare(GoodsSpecification o1, GoodsSpecification o2) {
                return o1.getSpecification().compareTo(o2.getSpecification());
            }
        });
        // specificationList
        JSONArray specificationList = new JSONArray();
        if (goodsSpecificationList.size() > 0) {
            int index = 0;
            while (index < goodsSpecificationList.size()) {
                String name = goodsSpecificationList.get(index).getSpecification();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", name);
                JSONArray valueList = new JSONArray();
                do {
                    GoodsSpecification goodsSpecification = goodsSpecificationList.get(index);
                    if (!goodsSpecification.getSpecification().equals(name)) {
                        break;
                    }
                    JSONObject element = new JSONObject();
                    element.put("id", goodsSpecification.getId());
                    element.put("goodsId", goodsSpecification.getGoodsId());
                    element.put("specification", name);
                    element.put("value", goodsSpecification.getValue());
                    element.put("picUrl", goodsSpecification.getPicUrl());
                    element.put("addTime", goodsSpecification.getAddTime());
                    element.put("updateTime", goodsSpecification.getUpdateTime());
                    element.put("deleted", goodsSpecification.getDeleted());
                    valueList.add(element);
                } while (++index < goodsSpecificationList.size());
                jsonObject.put("valueList", valueList);
                specificationList.add(jsonObject);
            }
        }
        data.put("specificationList", specificationList);
        // groupon
        // TODO: groupon 暂时不需要管
        JSONArray groupon = new JSONArray();
        data.put("groupon", groupon);
        // issue
        JSONArray issue = new JSONArray();
        List<Issue> allIssues = issueMapper.getAllIssues();
        for (Issue element : allIssues) {
            JSONObject object = new JSONObject();
            object.put("id", element.getId());
            object.put("question", element.getQuestion());
            object.put("answer", element.getAnswer());
            object.put("addTime", element.getAddTime());
            object.put("updateTime", element.getUpdateTime());
            object.put("deleted", element.getDeleted());
            issue.add(object);
        }
        data.put("issue", issue);
        // userHasCollect
        User user = userService.getUserOnline(sessionId);
        Collect collect = collectMapper.selectByUser(user.getId(), goodsId);
        if(null == collect) {
            data.put("userHasCollect", 0);
        } else {
            data.put("userHasCollect", 1);
        }
        // shareImage
        // TODO: shareImage 暂时不需要用管
        data.put("shareImage", null);
        // comment
        JSONObject comment = new JSONObject();
        JSONArray comments = new JSONArray();
        List<Comment> commentList = commentMapper.selectByGoodsId(goodsId);
        for(Comment comment1: commentList) {
            JSONObject object = new JSONObject();
            object.put("addTime", comment1.getAddTime());
            object.put("picList", comment1.getPicUrls());
            object.put("adminContent", comment1.getAdminContent());
            User user1 = userMapper.selectByPrimaryKey(comment1.getUserId());
            object.put("nickname", user1.getNickname());
            object.put("id", comment1.getId());
            object.put("avatar", user1.getAvatar());
            object.put("content", comment1.getContent());
            comments.add(object);
        }
        comment.put("data", comments);
        comment.put("count", comments.size());
        data.put("comment", comment);
        // attribute
        JSONArray attributes = new JSONArray();
        List<GoodsAttribute> goodsAttributes = goodsAttributeMapper.selectByGoodsId(goodsId);
        for (GoodsAttribute goodsAttribute : goodsAttributes) {
            JSONObject object = new JSONObject();
            object.put("id", goodsAttribute.getId());
            object.put("goodsId", goodsAttribute.getGoodsId());
            object.put("attribute", goodsAttribute.getAttribute());
            object.put("value", goodsAttribute.getValue());
            object.put("addTime", goodsAttribute.getAddTime());
            object.put("updateTime", goodsAttribute.getUpdateTime());
            object.put("deleted", goodsAttribute.getDeleted());
            attributes.add(object);
        }
        data.put("attribute", attributes);
        // brand
        JSONObject brand = new JSONObject();
        Brand brand1 = brandMapper.selectByPrimaryKey(goods.getBrandId());
        brand.put("id", brand1.getId());
        brand.put("name", brand1.getName());
        brand.put("desc", brand1.getDesc());
        brand.put("picUrl", brand1.getPicUrl());
        brand.put("sortOrder", brand1.getSortOrder());
        brand.put("floorPrice", brand1.getFloorPrice());
        brand.put("addTime", brand1.getAddTime());
        brand.put("updateTime", brand1.getUpdateTime());
        brand.put("deleted", brand1.getDeleted());
        data.put("brand", brand);
        // productList
        List<GoodsProduct> goodsProducts = goodsProductMapper.selectByGoodsId(goodsId);
        JSONArray productList = new JSONArray();
        for(GoodsProduct goodsProduct: goodsProducts) {
            JSONObject object = new JSONObject();
            object.put("id", goodsProduct.getId());
            object.put("goodsId", goodsProduct.getGoodsId());
            object.put("specifications", goodsProduct.getSpecifications());
            object.put("price", goodsProduct.getPrice());
            object.put("number", goodsProduct.getNumber());
            object.put("url", goodsProduct.getUrl());
            object.put("add_time", goodsProduct.getAddTime());
            object.put("updateTime", goodsProduct.getUpdateTime());
            object.put("deleted", goodsProduct.getDeleted());
            productList.add(object);
        }
        data.put("productList", productList);
        // info
        JSONObject info = new JSONObject();
        info.put("id", goods.getId());
        info.put("goodsSn", goods.getGoodsSn());
        info.put("name", goods.getName());
        info.put("categoryId", goods.getCategoryId());
        info.put("brandId", goods.getBrandId());
        info.put("gallery", goods.getGallery());
        info.put("keywords", goods.getKeywords());
        info.put("brief", goods.getBrief());
        info.put("isOnSale", goods.getIsOnSale());
        info.put("sortOrder", goods.getSortOrder());
        info.put("picUrl", goods.getPicUrl());
        info.put("shareUrl", goods.getShareUrl());
        info.put("isNew", goods.getIsNew());
        info.put("isHot", goods.getIsHot());
        info.put("unit", goods.getUnit());
        info.put("counterPrice", goods.getCounterPrice());
        info.put("retailPrice", goods.getRetailPrice());
        info.put("addTime", goods.getAddTime());
        info.put("updateTime", goods.getUpdateTime());
        info.put("deleted", goods.getDeleted());
        info.put("detail", goods.getDetail());
        data.put("info", info);
        return jsonService.getJsonResult(0, "成功", data);
    }

    // TODO: recommendation related
    public String getGoodsRelated(int id, int page, int limit, String sort, String order) {
        return "";
    }

    // TODO: recommendation info
    public String getGoodsInfo(int id, int page, int limit, String sort, String order) {
        return "";
    }

    public String getGoodsCount() {
        return String.valueOf(goodsMapper.getGoodsCount());
    }
}
