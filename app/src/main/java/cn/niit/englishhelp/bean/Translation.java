package cn.niit.englishhelp.bean;

import java.util.List;

/**
 * User:xsl
 * Date:2017/5/21
 * Time:22:04
 */

public class Translation {

    private List<String> translation ;

    private BasicEntity basic;

    private String query;

    private int errorCode;

    private List<Web> web ;

    public void setString(List<String> translation){
        this.translation = translation;
    }
    public List<String> getString(){
        return this.translation;
    }
    public void setBasic(BasicEntity basic){
        this.basic = basic;
    }
    public BasicEntity getBasic(){
        return this.basic;
    }
    public void setQuery(String query){
        this.query = query;
    }
    public String getQuery(){
        return this.query;
    }
    public void setErrorCode(int errorCode){
        this.errorCode = errorCode;
    }
    public int getErrorCode(){
        return this.errorCode;
    }
    public void setWeb(List<Web> web){
        this.web = web;
    }
    public List<Web> getWeb(){
        return this.web;
    }
}
