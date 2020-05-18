package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

	public static Connection Connection() throws ReflectiveOperationException, SQLException{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //データベースに接続
            Connection con = null;
            con = DriverManager.getConnection(
                    "jdbc:mysql://study-db.cs6qjcuxltwi.us-east-2.rds.amazonaws.com:3306/study?useUnicode=true&characterEncoding=utf8","root","tyty0317");
            return con;
	}

}
