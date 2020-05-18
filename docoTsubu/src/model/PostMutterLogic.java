package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PostMutterLogic {
	Connection con = null;
    private PreparedStatement smt, smt2, smt3, smt4 = null;
    int insertResult, updateResult, deleteResult = 0;
    ResultSet res = null;

    /**
     * つぶやき登録メソッド
     * @param mutter　つぶやき情報１レーコード分
     * @param request　リクエストスコープ
     */
    public void insertTsubuyaki(Mutter mutter, HttpServletRequest request) {
	     try{
    		//データベースに接続
			 con = DataBaseConnection.Connection();
	         //tsubuyakiテーブルにつぶやきを登録
	         smt = con.prepareStatement(
	                 "insert into tsubuyaki_table(user_name, tsubuyaki) values (?, ?)");
	         smt.setString(1, mutter.getUserName());
	         smt.setString(2, mutter.getText());
	         insertResult = smt.executeUpdate();
	     } catch (Exception e) {
	    	//エラ〜メッセージをリクエストスコープに保存
		    request.setAttribute("errorMsg", "つぶやきが登録できません");
	     }
	}

    /**
     * つぶやき編集メソッド
     * @param mutter　つぶやき情報１レーコード分
     * @param request　リクエストスコープ
     */
    public void changeTsubuyaki(Mutter mutter, HttpServletRequest request) {
	     try{
    		//データベースに接続
			 con = DataBaseConnection.Connection();
	         //tsubuyakiテーブルつぶやきの編集
	         smt2 = con.prepareStatement(
	                 "update tsubuyaki_table set tsubuyaki = ? where tsubuyaki_no = ?");
	         smt2.setString(1, mutter.getText());
	         smt2.setString(2, mutter.getNumber());
	         updateResult = smt2.executeUpdate();
	     } catch (Exception e) {
	    	//エラ〜メッセージをリクエストスコープに保存
		    request.setAttribute("errorMsg", "つぶやきが編集できません");
	     }
	}

    /**
     * つぶやき削除メソッド
     * @param mutter　つぶやき情報１レーコード分
     * @param request　リクエストスコープ
     */
    public void deleteTsubuyaki(Mutter mutter, HttpServletRequest request) {
	     try{
    		//データベースに接続
			 con = DataBaseConnection.Connection();
	         //tsubuyakiテーブルつぶやきの削除
	         smt3 = con.prepareStatement(
	                 "delete from tsubuyaki_table where tsubuyaki_no = ?");
	         smt3.setString(1, mutter.getNumber());
	         deleteResult = smt3.executeUpdate();
	     } catch (Exception e) {
	    	//エラ〜メッセージをリクエストスコープに保存
		    request.setAttribute("errorMsg", "つぶやきが削除できません");
	     }
	}

    /**
     * つぶやき一覧検索メソッド
     * @param request リクエストパラメータ
     * @return
     */
    public List<Mutter> selectTsubuyaki(HttpServletRequest request){
    	List<Mutter> allMutterList = new ArrayList<>();
    	try {
    		 //データベースに接続
			 con = DataBaseConnection.Connection();
	         //tsubuyakiテーブルに登録されているつぶやきをリストに格納
	         smt4 = con.prepareStatement(
	                  "select * from tsubuyaki_table");
	         res = smt4.executeQuery();
	         while(res.next()) {
	            	Mutter allMutter = new Mutter(res.getString("tsubuyaki_no"), res.getString("user_name"), res.getString("tsubuyaki"));
	            	allMutterList.add(allMutter);
	         }
    	} catch (Exception e){
    		//エラ〜メッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "つぶやきが表示できません");
    	}
		 return allMutterList;
	}
}