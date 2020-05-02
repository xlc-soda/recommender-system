package service.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import util.json.JsonUtil;
import util.logger.LoggerUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class GoodsService {

    private static final Logger logger = Logger.getLogger(GoodsService.class);

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
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    public String getGoodsList(String sessionId, boolean isNew, boolean isHot, String keyword, int brandId, int categoryId, int page,
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
        String result = JsonUtil.getJsonResult(0, "成功", data);
        logger.info(LoggerUtil.info(sessionId, result));
        return result;
    }

    public String getGoodsListAdmin(String sessionId, int goodsId, String goodsSn, String name, int page,
                               int limit, String sort, String order) {
        List<Goods> goodsList = goodsMapper.getGoodsListAdmin(goodsId, goodsSn, name, page * limit, limit, sort, order);
        JSONObject data = new JSONObject();
        data.put("total", goodsList.size());
        data.put("pages", page);
        data.put("limit", limit);
        JSONArray jsonArray = new JSONArray();
        for (Goods goods : goodsList) {
            JSONObject item = new JSONObject();
            item.put("id", goods.getId());
            item.put("goodsSn", goods.getGoodsSn());
            item.put("name", goods.getId());
            item.put("categoryId", goods.getCategoryId());
            item.put("brandId", goods.getBrandId());
            item.put("gallery", goods.getGallery());
            item.put("keywords", goods.getKeywords());
            item.put("brief", goods.getBrief());
            item.put("isOnSale", goods.getIsOnSale());
            item.put("sortOrder", goods.getSortOrder());
            item.put("picUrl", goods.getPicUrl());
            item.put("shareUrl", goods.getShareUrl());
            item.put("isNew", goods.getIsNew());
            item.put("isHot", goods.getIsHot());
            item.put("unit", goods.getUnit());
            item.put("counterPrice", goods.getCounterPrice());
            item.put("retailPrice", goods.getRetailPrice());
            item.put("addTime", goods.getAddTime());
            item.put("updateTime", goods.getUpdateTime());
            item.put("deleted", goods.getDeleted());
            item.put("detail", goods.getDetail());
            jsonArray.add(item);
        }
        data.put("list", jsonArray);
        String result = JsonUtil.getJsonResult(0, "成功", data);
        logger.info(LoggerUtil.info(sessionId, result));
        return result;
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
        String result = JsonUtil.getJsonResult(0, "成功", data);
        logger.info(LoggerUtil.info(sessionId, result));
        return result;
    }

    public String getGoodsCount(String sessionId) {
        String goodsCount = String.valueOf(goodsMapper.getGoodsCount());
        logger.info(LoggerUtil.info(sessionId, goodsCount));
        return goodsCount;
    }

    public String updateGoodsDetail(String sessionId, JSONArray goodsArray, JSONArray specifications, JSONArray products, JSONArray attributes) {
        boolean success = true;
        success &= updateGoods(goodsArray);
        success &= updateGoodsSpecification(specifications);
        success &= updateGoodsProduct(products);
        success &= updateGoodsAttribute(attributes);
        if(success) {
            String result = JsonUtil.getJsonResult(501, "失败");
            logger.info(LoggerUtil.info(sessionId, result));
            return result;
        } else {
            String result = JsonUtil.getJsonResult(0, "成功");
            logger.info(LoggerUtil.info(sessionId, result));
            return result;
        }
    }

    private boolean updateGoods(JSONArray goodsArray) {
        boolean success = true;
        for(JSONObject object: goodsArray.toJavaList(JSONObject.class)) {
            Goods goods = goodsMapper.selectByPrimaryKey(object.getInteger("id"));
            goods.setGoodsSn(object.getString("goodsSn"));
            goods.setName(object.getString("name"));
            goods.setCategoryId(object.getInteger("categoryId"));
            goods.setBrandId(object.getInteger("brandId"));
            goods.setGallery(object.getString("gallery"));
            goods.setKeywords(object.getString("keywords"));
            goods.setBrief(object.getString("brief"));
            goods.setIsOnSale(object.getBooleanValue("isOnSale"));
            goods.setSortOrder(object.getShort("sortOrder"));
            goods.setPicUrl(object.getString("picUrl"));
            goods.setShareUrl(object.getString("shareUrl"));
            goods.setIsNew(object.getBoolean("isNew"));
            goods.setIsHot(object.getBoolean("isHot"));
            goods.setUnit(object.getString("unit"));
            goods.setCounterPrice(object.getBigDecimal("counterPrice"));
            goods.setRetailPrice(object.getBigDecimal("retailPrice"));
            goods.setAddTime(object.getDate("addTime"));
            goods.setUpdateTime(new Date());
            goods.setDeleted(object.getBoolean("deleted"));
            goods.setDetail(object.getString("detail"));
            if(goodsMapper.updateByPrimaryKeySelective(goods) == 0) {
                success = false;
            }
        }
        return success;
    }

    private boolean updateGoodsSpecification(JSONArray specifications) {
        boolean success = true;
        for(JSONObject object: specifications.toJavaList(JSONObject.class)) {
            GoodsSpecification goodsSpecification = goodsSpecificationMapper.selectByPrimaryKey(object.getInteger("value"));
            goodsSpecification.setPicUrl(object.getString("picUrl"));
            if(goodsSpecificationMapper.updateByPrimaryKeySelective(goodsSpecification) == 0) {
                success = false;
            }
        }
        return success;
    }

    private boolean updateGoodsProduct(JSONArray products) {
        boolean success = true;
        for(JSONObject object: products.toJavaList(JSONObject.class)) {
            GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(object.getInteger("id"));
            goodsProduct.setPrice(object.getBigDecimal("price"));
            goodsProduct.setNumber(object.getInteger("number"));
            goodsProduct.setUrl(object.getString("url"));
            if(goodsProductMapper.updateByPrimaryKeySelective(goodsProduct) == 0) {
                success = false;
            }
        }
        return success;

    }

    private boolean updateGoodsAttribute(JSONArray attributes) {
        boolean success = true;
        for(JSONObject object: attributes.toJavaList(JSONObject.class)) {
            GoodsAttribute goodsAttribute = goodsAttributeMapper.selectByPrimaryKey(object.getInteger("id"));
            goodsAttribute.setGoodsId(object.getInteger("goodsId"));
            goodsAttribute.setAttribute(object.getString("attribute"));
            goodsAttribute.setValue(object.getString("value"));
            goodsAttribute.setAddTime(object.getDate("addTime"));
            goodsAttribute.setUpdateTime(new Date());
            goodsAttribute.setDeleted(object.getBoolean("deleted"));
            if(goodsAttributeMapper.updateByPrimaryKeySelective(goodsAttribute) == 0) {
                success = false;
            }
        }
        return success;
    }

    public String createGoodsDetail(String sessionId, JSONArray goodsArray, JSONArray specifications, JSONArray products, JSONArray attributes) {
        boolean success = true;
        success &= createGoods(goodsArray);
        success &= createGoodsSpecification(specifications);
        success &= createGoodsProduct(products);
        success &= createGoodsAttribute(attributes);
        if(success) {
            String result = JsonUtil.getJsonResult(501, "失败");
            logger.info(LoggerUtil.info(sessionId, result));
            return result;
        } else {
            String result = JsonUtil.getJsonResult(0, "成功");
            logger.info(LoggerUtil.info(sessionId, result));
            return result;
        }
    }

    private boolean createGoods(JSONArray goodsArray) {
        boolean success = true;
        for(JSONObject object: goodsArray.toJavaList(JSONObject.class)) {
            Goods goods = new Goods();
            goods.setId(null);
            goods.setGoodsSn(object.getString("goodsSn"));
            goods.setName(object.getString("name"));
            goods.setGallery(object.getString("gallery"));
            goods.setGallery(object.getString("gallery"));
            goods.setPicUrl(object.getString("picUrl"));
            goods.setIsNew(object.getBoolean("isNew"));
            goods.setIsHot(object.getBoolean("isHot"));
            goods.setIsOnSale(object.getBoolean("isOnSale"));
            Date date = new Date();
            goods.setAddTime(date);
            goods.setUpdateTime(date);
            goods.setDeleted(false);
            if(goodsMapper.insertSelective(goods) == 0) {
                success = false;
            }
        }
        return success;
    }

    private boolean createGoodsSpecification(JSONArray specifications) {
        boolean success = true;
        for(JSONObject object: specifications.toJavaList(JSONObject.class)) {
            GoodsSpecification goodsSpecification = new GoodsSpecification();
            goodsSpecification.setGoodsId(object.getInteger("id"));
            goodsSpecification.setSpecification(object.getString("specification"));
            goodsSpecification.setValue(object.getString("value"));
            goodsSpecification.setPicUrl(object.getString("picUrl"));
            goodsSpecification.setDeleted(false);
            if(goodsSpecificationMapper.insertSelective(goodsSpecification) == 0) {
                success = false;
            }
        }
        return success;
    }

    private boolean createGoodsProduct(JSONArray products) {
        boolean success = true;
        for(JSONObject object: products.toJavaList(JSONObject.class)) {
            GoodsProduct goodsProduct = new GoodsProduct();
            goodsProduct.setId(object.getInteger("id"));
            goodsProduct.setSpecifications(object.getString("specification"));
            goodsProduct.setPrice(object.getBigDecimal("price"));
            goodsProduct.setNumber(object.getInteger("number"));
            goodsProduct.setUrl(object.getString("url"));
            goodsProduct.setDeleted(false);
            if(goodsProductMapper.insertSelective(goodsProduct) == 0) {
                success = false;
            }
        }
        return success;

    }

    private boolean createGoodsAttribute(JSONArray attributes) {
        boolean success = true;
        for(JSONObject object: attributes.toJavaList(JSONObject.class)) {
            GoodsAttribute goodsAttribute = new GoodsAttribute();
            goodsAttribute.setGoodsId(object.getInteger("id"));
            goodsAttribute.setAttribute(object.getString("attribute"));
            goodsAttribute.setValue(object.getString("value"));
            Date date = new Date();
            goodsAttribute.setAddTime(date);
            goodsAttribute.setUpdateTime(date);
            goodsAttribute.setDeleted(false);
            if(goodsAttributeMapper.insertSelective(goodsAttribute) == 0) {
                success = false;
            }
        }
        return success;
    }

    public String deleteGoods(String sessionId, int goodsId) {
        if(goodsMapper.deleteByPrimaryKey(goodsId) == 0) {
            String result = JsonUtil.getJsonResult(501, "失败");
            logger.info(LoggerUtil.info(sessionId, result));
            return result;
        } else {
            String result = JsonUtil.getJsonResult(0, "成功");
            logger.info(LoggerUtil.info(sessionId, result));
            return result;
        }
    }

    public String getGoodsDetailAdmin(String sessionId, Integer goodsId) {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        JSONObject data = new JSONObject();
        data.put("id", goods.getId());
        data.put("goodsSn", goods.getGoodsSn());
        data.put("name", goods.getName());
        data.put("categoryId", goods.getCategoryId());
        data.put("brandId", goods.getBrandId());
        data.put("gallery", goods.getGallery());
        data.put("keywords", goods.getKeywords());
        data.put("brief", goods.getBrief());
        data.put("isOnSale", goods.getIsOnSale());
        data.put("sortOrder", goods.getSortOrder());
        data.put("picUrl", goods.getPicUrl());
        data.put("shareUrl", goods.getShareUrl());
        data.put("isNew", goods.getIsNew());
        data.put("isHot", goods.getIsHot());
        data.put("unit", goods.getUnit());
        data.put("counterPrice", goods.getCounterPrice());
        data.put("retailPrice", goods.getRetailPrice());
        data.put("addTime", goods.getAddTime());
        data.put("updateTime", goods.getUpdateTime());
        data.put("deleted", goods.getDeleted());
        data.put("detail", goods.getDetail());
        String result = JsonUtil.getJsonResult(0, "成功", data);
        logger.info(LoggerUtil.info(sessionId, result));
        return result;
    }
}
