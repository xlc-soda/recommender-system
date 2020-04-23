package service.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

    private static HashMap<Integer, String> hashMap = new HashMap<>();

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    static {
        hashMap.put(0 ,"全部");
        hashMap.put(1 ,"待付款");
        hashMap.put(2 ,"待发货");
        hashMap.put(3 ,"待收货");
        hashMap.put(4 ,"待评价");
    }

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private JsonService jsonService;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GrouponMapper grouponMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private UserMapper userMapper;

    public String getGoodsInfo(String sessionId, int showType, int page,
                               int limit, String sort, String order) {
        User user = userService.getUserOnline(sessionId);
        List<Order> orders = orderMapper.selectByUserId(user.getId(), showType, page, limit * page, sort, order);
        JSONObject data = new JSONObject();
        data.put("total", orders.size());
        data.put("pages", page);
        data.put("limit", limit);
        data.put("page", page);
        JSONArray list = new JSONArray();
        String type = hashMap.get(showType);
        for(Order order1: orders) {
            JSONObject object = new JSONObject();
            object.put("orderStatusText", type);
            Groupon groupon = grouponMapper.selectByOrderId(order1.getId());
            if(null == groupon) {
                object.put("isGroupin", true);
            } else {
                object.put("isGroupin", false);
            }
            object.put("orderSn", order1.getOrderSn());
            object.put("actualPrice", order1.getActualPrice());
            List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByOrderId(order1.getId());
            JSONArray goodsList = new JSONArray();
            for(OrderGoods orderGoods: orderGoodsList) {
                JSONObject goods = new JSONObject();
                goods.put("number", orderGoods.getNumber());
                goods.put("picUrl", orderGoods.getPicUrl());
                goods.put("id", orderGoods.getProductId());
                goods.put("goodsName", orderGoods.getGoodsName());
                goods.put("specifications", orderGoods.getSpecifications());
                goodsList.add(goods);
            }
            object.put("goodsList", goodsList);
            object.put("id", order1.getId());
            JSONObject handleOption = new JSONObject();
            handleOption.put("cancel", order1.getDeleted());
            handleOption.put("delete", order1.getDeleted());
            handleOption.put("pay", "".equals(order1.getPayId()));
            handleOption.put("comment", order1.getComments() > 0);
            handleOption.put("confirm", order1.getConfirmTime() != null);
            handleOption.put("refund", "".equals(order1.getRefundContent()));
            // TODO: rebuy 暂时不用
            handleOption.put("rebuy", false);
            object.put("handleOption", handleOption);
            list.add(object);
        }
        data.put("list", list);
        return jsonService.getJsonResult(0, "成功", data);
    }

    public String getOrderDetail(int orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        JSONObject data = new JSONObject();
        JSONObject orderInfo = new JSONObject();
        orderInfo.put("consignee", order.getConsignee());
        orderInfo.put("address", order.getAddress());
        orderInfo.put("addTime", order.getAddTime());
        orderInfo.put("orderSn", order.getOrderSn());
        orderInfo.put("actualPrice", order.getActualPrice());
        orderInfo.put("mobile", order.getMobile());
        orderInfo.put("orderStatusText", hashMap.get(order.getOrderStatus()));
        orderInfo.put("goodsPrice", order.getGoodsPrice());
        orderInfo.put("couponPrice", order.getCouponPrice());
        orderInfo.put("id", order.getId());
        orderInfo.put("freightPrice", order.getFreightPrice());
        JSONObject handleOption = new JSONObject();
        handleOption.put("cancel", order.getDeleted());
        handleOption.put("delete", order.getDeleted());
        handleOption.put("pay", "".equals(order.getPayId()));
        handleOption.put("comment", order.getComments() > 0);
        handleOption.put("confirm", order.getConfirmTime() != null);
        handleOption.put("refund", "".equals(order.getRefundContent()));
        // TODO: rebuy 暂时不用
        handleOption.put("rebuy", false);
        orderInfo.put("handleOption", handleOption);
        data.put("orderInfo", orderInfo);
        // orderGoods
        List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByOrderId(order.getId());
        JSONArray goodsList = new JSONArray();
        for(OrderGoods orderGoods: orderGoodsList) {
            JSONObject goods = new JSONObject();
            goods.put("id", orderGoods.getProductId());
            goods.put("orderId", order.getId());
            goods.put("goodsId", orderGoods.getGoodsId());
            goods.put("goodsName", orderGoods.getGoodsName());
            goods.put("goodsSn", orderGoods.getGoodsSn());
            goods.put("productId", orderGoods.getProductId());
            goods.put("number", orderGoods.getNumber());
            goods.put("price", orderGoods.getPrice());
            goods.put("specifications", orderGoods.getSpecifications());
            goods.put("picUrl", orderGoods.getPicUrl());
            goods.put("comment", orderGoods.getComment());
            goods.put("addTime", orderGoods.getAddTime());
            goods.put("updateTime", orderGoods.getUpdateTime());
            goods.put("deleted", orderGoods.getDeleted());
            goodsList.add(goods);
        }
        data.put("orderGoods", goodsList);
        return jsonService.getJsonResult(0, "成功", data);
    }

    public String createOrder(String sessionId, int cartId, int addressId, int couponId,
                              String message, int grouponRulesId, int grouponLinkId) {
        User user = userService.getUserOnline(sessionId);
        List<Cart> carts = cartMapper.selectByUserId(user.getId());
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(Cart cart: carts) {
            Goods goods = goodsMapper.selectByPrimaryKey(cart.getGoodsId());
            totalPrice = totalPrice.add(goods.getRetailPrice());
            cart.setChecked(true);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        Order order = new Order();
        // userId
        order.setUserId(user.getId());
        // orderSn
        Date date = new Date();
        String orderSn = simpleDateFormat.format(date);
        order.setOrderSn(orderSn);
        // orderStatus
        order.setOrderStatus(Short.valueOf("103"));
        // aftersaleStatus
        order.setAftersaleStatus(Short.valueOf("0"));
        // consignee
        order.setConsignee(user.getNickname());
        // mobile
        order.setMobile(user.getMobile());
        // address
        Address address = addressMapper.selectByPrimaryKey(addressId);
        order.setAddress(address.getProvince() + " " + address.getCity() + " " +
                address.getCounty() + " " + address.getAddressDetail());
        // message
        order.setMessage(message);
        // goodsPrice
        order.setGoodsPrice(totalPrice);
        // freightPrice
        // TODO: 配送费用
        order.setFreightPrice(BigDecimal.valueOf(0));
        // couponPrice
        // 优惠券
        if(couponId != -1) {
            Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
            order.setCouponPrice(coupon.getDiscount().multiply(totalPrice));
        } else {
            order.setCouponPrice(BigDecimal.valueOf(0));
        }
        // integralPrice
        // TODO: 积分减免
        order.setIntegralPrice(BigDecimal.valueOf(0));
        // grouponPrice
        order.setGrouponPrice(BigDecimal.valueOf(0));
        // orderPrice
        // = goods_price + freight_price - coupon_price
        order.setOrderPrice(totalPrice.add(order.getFreightPrice()).subtract(order.getCouponPrice()));
        // actualPrice
        // = order_price - integral_price
        order.setActualPrice(order.getOrderPrice().subtract(order.getIntegralPrice()));
        // payId
        order.setPayId(null);
        // payTime
        order.setPayTime(null);
        // shipSn
        order.setShipSn(null);
        // shipChannel
        order.setShipChannel(null);
        // shipTime
        order.setShipTime(null);
        // refundAmount
        order.setRefundAmount(null);
        // refundType
        order.setRefundType(null);
        // refundContent
        order.setRefundContent(null);
        // refundTime
        order.setRefundTime(null);
        // confirmTime
        order.setConfirmTime(null);
        // comments
        order.setComments(Short.valueOf("0"));
        // endTime
        order.setEndTime(null);
        // addTime
        order.setAddTime(date);
        // updateTime
        order.setUpdateTime(date);
        // deleted
        order.setDeleted(false);
        orderMapper.insertSelective(order);
        order = orderMapper.selectByOrderSn(orderSn);
        JSONObject data = new JSONObject();
        data.put("orderId", order.getId());
        return jsonService.getJsonResult(0, "成功", data);
    }

    public String cancelOrder(int orderId) {
        orderMapper.deleteByPrimaryKey(orderId);
        return jsonService.getJsonResult(0, "成功");
    }
}
