package model;

import java.io.Serializable;
/**
 * つぶやき情報を管理するためのJavaBeans
 * @author risa
 *
 */
public class Mutter implements Serializable {
    private String number;//つぶやきNo
    private String userName;//ユーザ名
    private String text;//つぶやき内容

    public Mutter() {
    }

    /**
     * つぶやき情報設定メソッド
     * @param number　つぶやきNo
     * @param userName　ユーザ名
     * @param text　つぶやき
     */
    public Mutter(String number, String userName, String text) {
        this.number = number;
        this.userName = userName;
        this.text = text;
    }

    /**
     * つぶやきNo取得メソッド
     * @returnつぶやきNo
     */
    public String getNumber() {
        return number;
    }

    /**
     * ユーザ名取得メソッド
     * @returnユーザ名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * つぶやき取得メソッド
     * @returnつぶやき
     */
    public String getText() {
        return text;
    }

}
