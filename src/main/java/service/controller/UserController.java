package service.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param session
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/wx/auth/login", method = {RequestMethod.POST})
    public String login(HttpSession session, @RequestBody JSONObject jsonObject) {
//        String header = httpServletRequest.getHeader("X-Librecmall-Token");
        return userService.login(session.getId(), jsonObject);
    }

    @RequestMapping(value = "/wx/auth/logout", method = {RequestMethod.POST})
    public String logout(HttpServletRequest httpServletRequest, @RequestBody JSONObject jsonObject) {
        String header = httpServletRequest.getHeader("X-Librecmall-Token");
        return userService.logout(header, jsonObject);
    }

    @RequestMapping(value = "/wx/auth/info", method = {RequestMethod.POST})
    public String getInfo(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("X-Librecmall-Token");
        return userService.getInfo(header);
    }

    @RequestMapping(value = "/wx/auth/profile", method = {RequestMethod.POST})
    public String updateProfile(HttpServletRequest httpServletRequest, @RequestBody JSONObject jsonObject) {
        String header = httpServletRequest.getHeader("X-Librecmall-Token");
        return userService.updateProfile(header, jsonObject);
    }

    @RequestMapping(value = "/admin/user/list", method = {RequestMethod.GET})
    public String listUser(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("X-Librecmall-Admin-Token");
        String username = httpServletRequest.getParameter("username");
        String mobile = httpServletRequest.getParameter("mobile");
        return userService.listUser(header, username, mobile, startPage, pageLimit);
    }

    @RequestMapping(value = "/admin/user/delete", method = {RequestMethod.GET})
    public String deleteUser(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("X-Librecmall-Admin-Token");
        int userId = Integer.valueOf(httpServletRequest.getParameter("id"));
        return userService.deleteUser(header, userId);
    }

    @RequestMapping(value = "/wx/auth/login_by_weixin", method = {RequestMethod.POST})
    public String loginByWeixin(HttpSession session, @RequestBody JSONObject jsonObject) {
//        String header = httpServletRequest.getHeader("X-Librecmall-Token");
        String appid = jsonObject.getString("appid");
        String secret = jsonObject.getString("secret");
        String code = jsonObject.getString("code");
        JSONObject userInfo = jsonObject.getJSONObject("userInfo");
        String rawData = jsonObject.getString("rawData");
        String signature = jsonObject.getString("signature");
        String encryptedData = jsonObject.getString("encryptedData");
        String iv = jsonObject.getString("iv");
        return userService.loginByWeixin(session.getId(), appid, secret, code, userInfo, rawData, signature, encryptedData, iv);
    }

}
