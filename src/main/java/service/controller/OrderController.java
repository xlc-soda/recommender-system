package service.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/wx/order/list", method = RequestMethod.GET)
    public String getOrderList(HttpServletRequest httpServletRequest, HttpSession session) {
        int showType = Integer.valueOf(httpServletRequest.getParameter("showType"));
        int page = Integer.valueOf(httpServletRequest.getParameter("page"));
        int limit = Integer.valueOf(httpServletRequest.getParameter("limit"));
        String sort = httpServletRequest.getParameter("sort");
        String order = httpServletRequest.getParameter("order");
        return orderService.getGoodsInfo(session.getId(), showType, page, limit, sort, order);
    }

    @RequestMapping(value = "/wx/order/detail", method = RequestMethod.GET)
    public String getOrderDetail(HttpSession session, HttpServletRequest httpServletRequest) {
        int orderId = Integer.valueOf(httpServletRequest.getParameter("orderId"));
        return orderService.getOrderDetail(session.getId(), orderId);
    }

    @RequestMapping(value = "/wx/order/submit", method = {RequestMethod.POST})
    public String createOrder(HttpSession session, @RequestBody JSONObject jsonObject) {
        int cartId = jsonObject.getInteger("cartId");
        int addressId = jsonObject.getInteger("addressId");
        int couponId = jsonObject.getInteger("couponId");
        String message = jsonObject.getString("message");
        int grouponRulesId = jsonObject.getInteger("grouponRulesId");
        int grouponLinkId = jsonObject.getInteger("grouponLinkId");
        return orderService.createOrder(session.getId(), cartId, addressId, couponId, message, grouponRulesId, grouponLinkId);
    }


    @RequestMapping(value = "/wx/order/cancel", method = RequestMethod.POST)
    public String cancelOrder(HttpSession session, HttpServletRequest httpServletRequest) {
        int orderId = Integer.valueOf(httpServletRequest.getParameter("orderId"));
        return orderService.cancelOrder(session.getId(), orderId);
    }

}
