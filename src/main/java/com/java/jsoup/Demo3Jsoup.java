package com.java.jsoup;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Document对象功能
 */
public class Demo3Jsoup {
    public static void main(String[] args) throws IOException {
        //1.获取student.xml的path
        String path = Demo3Jsoup.class.getClassLoader().getResource( "jsoup/student.xml" ).getPath();
        //2.获取Document对象
        Document document = Jsoup.parse( new File( path ), "utf-8" );

        //3.获取元素对象了。
        //3.1获取所有student对象
        Elements elements = document.getElementsByTag( "student" );
        System.out.println( elements );

        System.out.println( "-----------" );


        //3.2 获取属性名为id的元素对象们
        Elements elements1 = document.getElementsByAttribute( "id" );
        System.out.println( elements1 );
        System.out.println( "-----------" );
        //3.2获取 number属性值为001的元素对象
        Elements elements2 = document.getElementsByAttributeValue( "number", "001" );
        System.out.println( elements2 );

        System.out.println( "-----------" );
        //3.3获取id属性值的元素对象
        Element test = document.getElementById( "test" );
        System.out.println( test );
    }

}
