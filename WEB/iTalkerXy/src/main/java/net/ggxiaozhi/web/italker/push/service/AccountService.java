package net.ggxiaozhi.web.italker.push.service;


import javax.ws.rs.*;

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


}
