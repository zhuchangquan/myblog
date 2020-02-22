package com.mind.utils;

public class Constant {

    public static String uploadDir = "D:\\opt\\apache-tomcat-8.5.31\\webapps\\upload\\";

    public static String uploadUrl = "http://localhost:10080/upload/";

    /**
     * 消息类型
     */
    public enum MsgType{
        SYSTEM,//系统消息

        POST,//评论文章消息

        COMMENT//评论你的评论消息
    }

    public static final int NORMAL_STATUS = 0;
    public static final int DELETE_STATUS = 1;

    public static final String EDIT_HTML_MODEL= "html";
    public static final String EDIT_MD_MODEL= "Markdown";

}
