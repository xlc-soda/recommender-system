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

    @RequestMapping(value = "/wx/auth/login", method = {RequestMethod.POST})
    public String login(HttpSession session, @RequestBody JSONObject jsonObject) {
        return userService.login(session.getId(), jsonObject);
    }

    @RequestMapping(value = "/wx/auth/logout", method = {RequestMethod.POST})
    public String logout(HttpSession session, @RequestBody JSONObject jsonObject) {
        return userService.logout(session.getId(), jsonObject);
    }

    @RequestMapping(value = "/wx/auth/register", method = {RequestMethod.POST})
    public String register(HttpSession session, @RequestBody JSONObject jsonObject) {
        return userService.register(session.getId(), jsonObject);
    }

    @RequestMapping(value = "/wx/auth/captcha", method = {RequestMethod.POST})
    public String getCaptcha(HttpSession session, @RequestBody JSONObject jsonObject) {
        return userService.getCaptcha(session.getId(), jsonObject);
    }

    @RequestMapping(value = "/wx/auth/passwd", method = {RequestMethod.POST})
    public String changePassword(HttpSession session, @RequestBody JSONObject jsonObject) {
        return userService.changePassword(session.getId(), jsonObject);
    }

    @RequestMapping(value = "/wx/auth/info", method = {RequestMethod.POST})
    public String getInfo(HttpSession session) {
        return userService.getInfo(session.getId());
    }

    @RequestMapping(value = "/wx/auth/profile", method = {RequestMethod.POST})
    public String updateProfile(HttpSession session, @RequestBody JSONObject jsonObject) {
        return userService.updateProfile(session.getId(), jsonObject);
    }

    @RequestMapping(value = "/admin/user/list", method = {RequestMethod.GET})
    public String listUser(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getParameter("username");
        String mobile = httpServletRequest.getParameter("mobile");
        return userService.listUser(username, mobile, startPage, pageLimit);
    }

    @RequestMapping(value = "/admin/user/delete", method = {RequestMethod.GET})
    public String deleteUser(HttpServletRequest httpServletRequest) {
        int userId = Integer.valueOf(httpServletRequest.getParameter("id"));
        return userService.deleteUser(userId);
    }

    @RequestMapping(value = "/wx/auth/login_by_weixin", method = {RequestMethod.POST})
    public String loginByWeixin(HttpServletRequest httpServletRequest, @RequestBody JSONObject jsonObject) {
        String appid = jsonObject.getString("appid");
        String secret = jsonObject.getString("secret");
        String code = jsonObject.getString("code");
        JSONObject userInfo = jsonObject.getJSONObject("userInfo");
        String rawData = jsonObject.getString("rawData");
        String signature = jsonObject.getString("signature");
        String encryptedData = jsonObject.getString("encryptedData");
        String iv = jsonObject.getString("iv");
        return userService.loginByWeixin(appid, secret, code, userInfo, rawData, signature, encryptedData, iv);
    }

}
