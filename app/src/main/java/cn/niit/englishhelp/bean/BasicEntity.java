package cn.niit.englishhelp.bean;

import java.util.List;

/**
 * User:xsl
 * Date:2017/5/21
 * Time:22:06
 */

public class BasicEntity {
    private String us_phonetic;

    private String phonetic;

    private String uk_phonetic;

    private List<String> explains;

    public String getUs_phonetic() {
        return us_phonetic;
    }

    public void setUs_phonetic(String us_phonetic) {
        this.us_phonetic = us_phonetic;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getUk_phonetic() {
        return uk_phonetic;
    }

    public void setUk_phonetic(String uk_phonetic) {
        this.uk_phonetic = uk_phonetic;
    }

    public List<String> getExplains() {
        return explains;
    }

    public void setExplains(List<String> explains) {
        this.explains = explains;
    }
}
