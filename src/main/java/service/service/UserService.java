package service.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.UserMapper;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import redis.clients.jedis.Jedis;
import util.config.Configs;
import util.json.JsonUtil;
import util.logger.LoggerUtil;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public String login(String sessionId, JSONObject jsonObject) {
        User user = getUser(jsonObject);
        String result;
        if(null != user) {
            JSONObject userInfo = new JSONObject();
            userInfo.put("nickName", user.getNickname());
            userInfo.put("avatarUrl", user.getAvatar());
            JSONObject data = new JSONObject();
            data.put("userInfo", userInfo);
            data.put("token", sessionId);
            // redis里记录登录信息
            if(setUserOnline(sessionId, user)) {
                result = JsonUtil.getJsonResult(0, "成功", data);
            } else {
                result = JsonUtil.getJsonResult(501, "用户名不存在或密码不正确", null);
            }
        } else {
            result =  JsonUtil.getJsonResult(501, "用户名不存在或密码不正确", null);
        }
        logger.info(LoggerUtil.info(sessionId, result));
        logger.info(LoggerUtil.info(sessionId, result));
        return result;
    }

    public String logout(String sessionId, JSONObject jsonObject) {
        User user = getUser(jsonObject);
        String result;
        if(null == user) {
            // redis里记录登录信息
            Jedis jedis = new Jedis(Configs.hosts.get("slave1"));
            jedis.hdel(Configs.USER_FIELD, sessionId);
            jedis.close();
            result = JsonUtil.getJsonResult(0, "成功");
        } else {
            result = JsonUtil.getJsonResult(501, "失败");
        }
        logger.info(LoggerUtil.info(sessionId, result));
        return result;
    }

    public String getInfo(String sessionId) {
        User user = getUserOnline(sessionId);
        String result;
        if(null != user) {
            JSONObject data = new JSONObject();
            data.put("gender", user.getGender());
            data.put("nickName", user.getNickname());
            data.put("mobile", user.getMobile());
            data.put("avatar", user.getAvatar());
            result = JsonUtil.getJsonResult(0, "成功", data);
        } else {
            result = JsonUtil.getJsonResult(501, "失败");
        }
        logger.info(LoggerUtil.info(sessionId, result));
        return result;
    }

    public String updateProfile(String sessionId, JSONObject jsonObject) {
        User user = getUserOnline(sessionId);
        String result;
        if(null != user) {
            user.setGender(jsonObject.getByte("gender"));
            user.setNickname(jsonObject.getString("nickName"));
            user.setAvatar(jsonObject.getString("avatar"));
            userMapper.updateByPrimaryKey(user);
            result = JsonUtil.getJsonResult(0, "成功");
        } else {
            result = JsonUtil.getJsonResult(501, "失败");
        }
        logger.info(LoggerUtil.info(sessionId, result));
        return result;
    }

    private User getUser(JSONObject jsonObject) {
        User user = new User();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        user.setPassword(password);
        user.setUsername(username);
        user = userMapper.selectByPassword(user);
        return user;
    }

    private boolean setUserOnline(String sessionId, User user) {
        Jedis jedis = new Jedis(Configs.hosts.get("slave1"));
        long result = jedis.hset(sessionId, Configs.USER_FIELD, String.valueOf(user.getId()));
        result += jedis.expire(sessionId, 7200);
        jedis.close();
        return result > 0;
    }

    User getUserOnline(String token) {
        Jedis jedis = new Jedis(Configs.hosts.get("slave1"));
        String userID = jedis.hget(token, Configs.USER_FIELD);
        jedis.close();
        return userMapper.selectByPrimaryKey(Integer.valueOf(userID));
    }

    public String listUser(String sessionId, String username, String mobile, int page, int limit) {
        if("".equals(username) && "".equals(mobile)) {
            return JsonUtil.getJsonResult(401, "请保证用户名和手机号至少一个不为空");
        }
        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();
        List<User> users = userMapper.selectByUsernameOrMobile(username, mobile, page * limit, limit);
        for(User user: users) {
            JSONObject object = new JSONObject();
            object.put("id", user.getId());
            object.put("username", user.getUsername());
            object.put("password", user.getPassword());
            object.put("gender", user.getGender());
            object.put("lastLoginTime", user.getLastLoginTime());
            object.put("lastLoginIp", user.getLastLoginIp());
            object.put("userLevel", user.getUserLevel());
            object.put("nickname", user.getNickname());
            object.put("mobile", user.getMobile());
            object.put("avatar", user.getAvatar());
            object.put("weixinOpenid", user.getWeixinOpenid());
            object.put("sessionKey", user.getSessionKey());
            object.put("status", user.getStatus());
            object.put("addTime", user.getAddTime());
            object.put("updateTime", user.getUpdateTime());
            object.put("deleted", user.getDeleted());
            list.add(object);
        }
        data.put("total", list.size());
        data.put("pages", (list.size() + page - 1) / page);
        data.put("limit", limit);
        data.put("page", page);
        data.put("list", list);
        String result = JsonUtil.getJsonResult(0, "成功", data);
        logger.info(LoggerUtil.info(sessionId, result));
        return result;
    }

    public String deleteUser(String sessionId, int userId) {
        String result;
        if(userMapper.deleteByPrimaryKey(userId) == 0) {
            result = JsonUtil.getJsonResult(501, "失败");
        } else {
            result = JsonUtil.getJsonResult(0, "成功");
        }
        logger.info(LoggerUtil.info(sessionId, result));
        return result;
    }

    public String loginByWeixin(String sessionId, String appid, String secret, String code, JSONObject userInfo, String rawData,
                                String signature, String encryptedData, String iv) {
        WxMaDefaultConfigImpl wxMaDefaultConfig = new WxMaDefaultConfigImpl();
        wxMaDefaultConfig.setAppid(appid);
        wxMaDefaultConfig.setSecret(secret);
        wxMaDefaultConfig.setAccessToken(code);
        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(wxMaDefaultConfig);
        WxMaJscode2SessionResult wxMaJscode2SessionResult;
        String sessionKey, unionId;
        String result;
        try {
             wxMaJscode2SessionResult = wxMaService.jsCode2SessionInfo(code);
             sessionKey = wxMaJscode2SessionResult.getSessionKey();
             unionId = wxMaJscode2SessionResult.getUnionid();
        } catch (WxErrorException e) {
            e.printStackTrace();
            result = JsonUtil.getJsonResult(501, "失败");
            logger.info(LoggerUtil.info(sessionId, result));
            return result;
        }
        WxMaUserService wxMaUserService = wxMaService.getUserService();
        if(wxMaUserService.checkUserInfo(sessionKey, rawData, signature)) {
            WxMaUserInfo userInfo1 = wxMaUserService.getUserInfo(sessionKey, encryptedData, iv);
            String openId = userInfo1.getOpenId();
            JSONObject.toJSONString(userInfo1);
            userInfo.put("openId", openId);
            userInfo.put("unionId", unionId);
            JSONObject data = new JSONObject();
            data.put("token", sessionId);
            data.put("userInfo", userInfo);
            User user = new User();
            // TODO: 确认微信登录时用户名如何存储
            user.setUsername("");
            // TODO: 确认微信登录时密码如何存储
            user.setPassword("");
            // TODO: 测试性别
            user.setGender(Byte.valueOf(userInfo1.getGender()));
            // TODO: 确认微信登录时生日如何存储
            Date date = new Date();
            user.setBirthday(date);
            user.setLastLoginTime(date);
            // TODO: 确认微信登录时ip如何存储
            user.setLastLoginIp("");
            user.setUserLevel(Byte.valueOf("0"));
            user.setNickname(userInfo1.getNickName());
            // TODO: 确认微信登录时手机如何存储
            user.setMobile("");
            user.setAvatar(userInfo1.getAvatarUrl());
            user.setWeixinOpenid(openId);
            user.setSessionKey(sessionKey);
            user.setStatus(Byte.valueOf("0"));
            user.setAddTime(date);
            user.setUpdateTime(date);
            user.setDeleted(false);
            if(userMapper.insertSelective(user) > 0 && setUserOnline(sessionId, user)) {
                result = JsonUtil.getJsonResult(0, "成功", data);
            } else {
                result = JsonUtil.getJsonResult(501, "失败");
            }
        } else {
            result = JsonUtil.getJsonResult(501, "失败");
        }
        logger.info(LoggerUtil.info(sessionId, result));
        return result;
    }
}
