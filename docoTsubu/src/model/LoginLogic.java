package model;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginLogic {
	Connection con = null;
    private PreparedStatement smt = null;
    ResultSet res = null;

	public boolean execute(User user) throws UnsupportedEncodingException {

        try {
            //データベースに接続
            con =DataBaseConnection.Connection();
            //userテーブルからログインユーザを検索
            smt = con.prepareStatement(
                    "select user_name,user_password from user_table where user_name = ? and user_password = ?");
            smt.setString(1, user.getName());
            smt.setString(2, user.getPass());
            res = smt.executeQuery();
            //userテーブルにログインユーザが登録されている場合はtrueを返す
            return res.next();
        } catch (Exception e) {
        	//userテーブルにログインユーザが登録されていない場合はfalseを返す
        	return false;
        }
	}

}
