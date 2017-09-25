package com.orieange.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableOAuth2Sso
public class ZuulLauncher {

    public static void main(String[] args) {
        SpringApplication.run(ZuulLauncher.class,args);
    }

    @Bean
    public ZuulFilter accessFilter(OAuth2ClientProperties properties) {


        final  String  str="Basic "+Base64.getEncoder().encodeToString((properties.getClientId()+":"+properties.getClientSecret()).getBytes());

        ZuulFilter accessFilter = new ZuulFilter() {

            @Override
            public boolean shouldFilter() {
                return true;
            }

            @Override
            public Object run() {
                RequestContext ctx = RequestContext.getCurrentContext();
                HttpServletRequest request = ctx.getRequest();
                HttpServletResponse response = ctx.getResponse();
                System.out.println(
                        String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
                if(request.getRequestURL().toString().endsWith("/oauth/token")) {
                    ctx.addZuulRequestHeader("Authorization", str);
                }else{
                    String access_token = request.getParameter("access_token");
                    if(access_token!=null&&access_token.length()>0){
                        Cookie cookie=new Cookie("X-ACCESS-TOKEN",access_token);
                        response.addHeader("X-ACCESS-TOKEN",access_token);
                        response.addCookie(cookie);
                    }
                }
                return null;
            }

            @Override
            public String filterType() {
                return "pre";
            }

            @Override
            public int filterOrder() {
                return 0;
            }
        };

        return accessFilter;
    }

}
