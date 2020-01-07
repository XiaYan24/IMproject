package net.ggxiaozhi.web.italker.push.service;


import net.ggxiaozhi.web.italker.push.bean.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 工程名 ： iTalker
 * 作者名 ： Xia焱
 * 日期   ： 2020/01
 * 功能   ：所有账号相关请求的入口
 */

@Path("/account")
public class AccountService {
    ///account
    @GET
    @Path("/login")
    public String get() {
        return "You get the login";
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)//指定传入的格式
    @Produces(MediaType.APPLICATION_JSON)//指定返回的格式
    public User post() {
        User user = new User();
        user.setName("妞妞");
        user.setSex(2);
        return user;
    }
}
