package model;

import java.io.Serializable;
/**
 * ログインユーザ情報を管理するためのJavaBeans
 * @author risa
 *
 */
public class User implements Serializable {
    private String name;//ユーザ名
    private String pass;//パスワード

    public User() {
    }

    /**
     * ユーザ情報設定コンストラクタ
     * @param name　ユーザ名
     * @param pass　パスワード
     */
    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    /**
     * ユーザ名取得メソッド
     * @returnユーザ名
     */
    public String getName() {
        return name;
    }

    /**
     * パスワード取得メソッド
     * @returnパスワード
     */
    public String getPass() {
        return pass;
    }

}
