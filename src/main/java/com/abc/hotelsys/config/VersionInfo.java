package com.abc.hotelsys.config;




public class VersionInfo {

    //application info
    public final static String APP_NAME="酒店房间管理系统";
    public final static String APP_VERSION="1.0";
    public final static String APP_STATUS="Beta";
    public final static String APP_BUILDATE="2023-10-1";
    public final static String APP_BUILDVER="#1";

    //application author
    public final static String APP_AUTHOR="第1小组";
    public final static String APP_WORKSTUDIO="闽江学院数据库课程设计";

    public final static String buildFooterStr()
    {
        StringBuffer sb=new StringBuffer();

        sb.append(APP_NAME);
        sb.append(" "+"(版本:"+APP_STATUS+APP_VERSION);
        sb.append(" &nbsp;&nbsp;Build:"+APP_BUILDVER);
        sb.append(" "+APP_BUILDATE+")");
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;开发团队:"+APP_AUTHOR);
        sb.append("&copy;&nbsp;"+APP_WORKSTUDIO+"&nbsp;&nbsp;"+"<br>");

        return sb.toString();
    }

}
