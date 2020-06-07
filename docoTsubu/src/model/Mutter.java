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
     * つぶやき一覧表示用コンストラクタ
     * @param numbe　つぶやきNo
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
     * @return number　つぶやきNo
     */
    public String getNumber() {
        return number;
    }

    /**
     * つぶやきNo設定メソッド
     * @param number セットする つぶやきNo
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * ユーザ名取得メソッド
     * @return userName　ユーザ名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ユーザ名設定メソッド
     * @param userName セットする ユーザ名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * つぶやき取得メソッド
     * @return text　つぶやき
     */
    public String getText() {
        return text;
    }

    /**
     * つぶやき設定メソッド
     * @param text セットする つぶやき
     */
    public void setText(String text) {
        this.text = text;
    }
}
