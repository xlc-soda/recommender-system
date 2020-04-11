package service.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.service.UserService;

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

}
