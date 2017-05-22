package cn.niit.englishhelp.bean;

import java.util.List;

/**
 * User:xsl
 * Date:2017/5/21
 * Time:22:08
 */

public class Web {
    private List<String> value ;

    private String key;

    public void setString(List<String> value){
        this.value = value;
    }
    public List<String> getString(){
        return this.value;
    }
    public void setKey(String key){
        this.key = key;
    }
    public String getKey(){
        return this.key;
    }

}
