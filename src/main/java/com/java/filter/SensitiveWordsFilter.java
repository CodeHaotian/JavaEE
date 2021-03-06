package com.java.filter;

import cn.hutool.core.io.IoUtil;

import javax.servlet.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词汇过滤器
 */
//@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.创建代理对象，增强getParameter方法

        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance( req.getClass().getClassLoader(), req.getClass().getInterfaces(), (proxy, method, args) -> {
            //增强getParameter方法
            //判断是否是getParameter方法
            if ("getParameter".equals( method.getName() )) {
                //增强返回值
                //获取返回值
                String value = (String) method.invoke( req, args );
                if (value != null) {
                    for (String str : list) {
                        if (value.contains( str )) {
                            value = value.replaceAll( str, "***" );
                        }
                    }
                }
                return value;
            } /*else if (method.getName().equals( "getParameterMap" )) {
                Map<String, String[]> parameterMap = (Map<String, String[]>) method.invoke( req, args );

                String st = null;
                for (String key : parameterMap.keySet()) {
                    String[] values = parameterMap.get( key );
                    for (String value : values) {
                        st = value;
                    }
                    if (st != null){

                    }

                }

            }*/
            //判断方法名是否是 getParameterMap

            //判断方法名是否是 getParameterValue

            return method.invoke( req, args );
        } );
        //2.放行
        chain.doFilter( proxy_req, resp );
    }

    //敏感词汇集合

    private List<String> list = new ArrayList<String>();

    @Override
    public void init(FilterConfig config) {

        try {
            //1.获取文件真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath( "/敏感词汇.txt" );
            //2.读取文件
            BufferedReader br = new BufferedReader( new FileReader( realPath ) );
            //3.将文件的每一行数据添加到list中
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add( line );
            }

            br.close();

            System.out.println( list );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
    }

}
