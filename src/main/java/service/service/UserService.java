package service.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import redis.clients.jedis.Jedis;
import util.Configs;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JsonService jsonService;

    public String login(String sessionId, JSONObject jsonObject) {
        User user = getUser(jsonObject);
        if(null == user) {
            JSONObject userInfo = new JSONObject();
            userInfo.put("nickName", user.getNickname());
            userInfo.put("avatarUrl", user.getAvatar());
            JSONObject data = new JSONObject();
            data.put("userInfo", userInfo);
            data.put("token", sessionId);
            // redis里记录登录信息
            Jedis jedis = new Jedis(Configs.hosts.get("slave1"));
            jedis.hset(sessionId, Configs.USER_FIELD, String.valueOf(user.getId()));
            jedis.close();
            return jsonService.getJsonResult(0, "成功", data);
        } else {
            return jsonService.getJsonResult(501, "用户名不存在或密码不正确", null);
        }
    }

    public String logout(String sessionId, JSONObject jsonObject) {
        User user = getUser(jsonObject);
        if(null == user) {
            // redis里记录登录信息
            Jedis jedis = new Jedis(Configs.hosts.get("slave1"));
            jedis.hdel(Configs.USER_FIELD, sessionId);
            jedis.close();
            return jsonService.getJsonResult(0, "成功");
        } else {
            return jsonService.getJsonResult(501, "失败");
        }
    }

    // TODO: implement method register
    public String register(String sessionId, JSONObject jsonObject) {
        JSONObject result = new JSONObject();

        return result.toJSONString();
    }

    // TODO: implement method getCaptcha
    public String getCaptcha(String sessionId, JSONObject jsonObject) {
        JSONObject result = new JSONObject();

        return result.toJSONString();
    }

    // TODO: implement method changePassword
    public String changePassword(String sessionId, JSONObject jsonObject) {
        JSONObject result = new JSONObject();

        return result.toJSONString();
    }

    public String getInfo(String sessionId) {
        User user = getUserOnline(sessionId);
        if(null != user) {
            JSONObject data = new JSONObject();
            data.put("gender", user.getGender());
            data.put("nickName", user.getNickname());
            data.put("mobile", user.getMobile());
            data.put("avatar", user.getAvatar());
            return jsonService.getJsonResult(0, "成功", data);
        } else {
            return jsonService.getJsonResult(501, "失败");
        }
    }

    public String updateProfile(String sessionId, JSONObject jsonObject) {
        User user = getUserOnline(sessionId);
        if(null != user) {
            user.setGender(jsonObject.getByte("gender"));
            user.setNickname(jsonObject.getString("nickName"));
            user.setAvatar(jsonObject.getString("avatar"));
            userMapper.updateByPrimaryKey(user);
            return jsonService.getJsonResult(0, "成功");
        } else {
            return jsonService.getJsonResult(501, "失败");
        }
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

    public User getUserOnline(String sessionId) {
        Jedis jedis = new Jedis(Configs.hosts.get("slave1"));
        String userID = jedis.hget(sessionId, Configs.USER_FIELD);
        jedis.close();
        return userMapper.selectByPrimaryKey(Integer.valueOf(userID));
    }

    public String listUser(String username, String mobile, int page, int limit) {
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
        return jsonService.getJsonResult(0, "成功", data);
    }

    public String deleteUser(int userId) {
        if(userMapper.deleteByPrimaryKey(userId) == 0) {
            return jsonService.getJsonResult(501, "失败");
        } else {
            return jsonService.getJsonResult(0, "成功");
        }
    }
}
