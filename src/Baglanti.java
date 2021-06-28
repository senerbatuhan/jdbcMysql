import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Baglanti {

	private Connection con = null;
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}




	public Baglanti() {

		String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.db_ismi+ "?useUnicode=true&characterEncoding=utf8";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException ex){
			System.out.println("Driver Bulunamadý.....");
		}

		try {
			con = DriverManager.getConnection(url, Database.kullanici_adi, Database.parola);		

		} catch (SQLException ex) {
			System.out.println("Baðlantý Baþarýsýz.....");	
		}

	}




}
