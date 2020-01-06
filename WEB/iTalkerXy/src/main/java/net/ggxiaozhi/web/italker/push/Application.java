package net.ggxiaozhi.web.italker.push;


import org.glassfish.jersey.server.ResourceConfig;

public class Application  extends ResourceConfig {

    public Application(){
        //注册逻辑处理的包名
        packages("net.ggxiaozhi.web.italker.push.service");
       // packages(AccountService.class.getPackage().getName());
    }
}
