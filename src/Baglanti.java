import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Baglanti {

	private String kullanici_adi = "root";
	private String parola = "";
	private String db_ismi = "calisanlar";
	private String host = "localhost";
	private int port = 3306;


	private Connection con = null;
	private Statement statement = null;
	public void calis() {

		String islemler = "1. Personelleri G�r�nt�le\n"
				+"2. Personel G�r�nt�le\n"
				+"3. Personel Ekleme\n"
				+"4. Personel Silme\n"
				+"5. Personel G�ncelleme\n"
				+"��k�� i�in q'ya bas�n";

		System.out.println("************************");
		System.out.println(islemler);
		Scanner scanner = new Scanner(System.in);
		

		while(true) {

			
			System.out.println("************************");
			System.out.println("��lemi Se�iniz: ");
			String islem=scanner.nextLine();
			if (islem.equals("q")) {
				System.out.println("��k�� Ba�ar�l�...");
				break;
			}
			else if (islem.equals("1")) {
				calisanlarGetir();
				System.out.println("Personeller ba�ar�yla g�r�nt�lendi... ");
			}
			else if (islem.equals("2")) {
				personelGetir();
				System.out.println("Personel ba�ar�yla g�r�nt�lendi... ");
			}
			else if (islem.equals("3")) {
				personelEkle();
				System.out.println("Personel ba�ar�yla eklendi... ");
			}
			else if (islem.equals("4")) {
				personelSil();
				System.out.println("Personel ba�ar�yla silindi... ");
			}
			else if (islem.equals("5")) {
				personelGuncelle();
				System.out.println("Personel ba�ar�yla g�ncellendi... ");
			}
			else {
				System.out.println("Ge�ersiz i�lem giri�i yapt�n�z.....");
			}
		}




	}

	public Baglanti() {

		String url = "jdbc:mysql://" + host + ":" + port + "/" + db_ismi+ "?useUnicode=true&characterEncoding=utf8";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException ex){
			System.out.println("Driver Bulunamad�.....");
		}

		try {
			con = DriverManager.getConnection(url, kullanici_adi, parola);
			System.out.println("Ba�lant� Ba�ar�l�.....");		

		} catch (SQLException ex) {
			System.out.println("Ba�lant� Ba�ar�s�z.....");	
		}

	}

	public void calisanlarGetir() {
		String sorgu = "Select * from personeller";
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sorgu);
			while (rs.next()) {

				int id = rs.getInt("id");
				String ad = rs.getString("ad");
				String soyad = rs.getString("soyad");
				String departman = rs.getString("departman");
				String maas = rs.getString("maas");

				System.out.println("ID: " + id + " | Ad: " + ad + " | Soyad: " + soyad + " | Departman: " + departman + " | Maa�: " + maas + "\n");


			}

		} catch (SQLException ex) {
			// TODO: handle exception
			System.out.println("Personeller getirilemedi...");
		}
	}

	public void personelGetir() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Personel neye g�re getirilsin?\n"
				+ "1. id\n"
				+ "2. ad");
		String kisi_islem = scan.nextLine();
		if (kisi_islem.equals("1")) {
			System.out.println("Personel id giriniz:");
			int personel_id = scan.nextInt();
			String sorgu = "Select * from personeller where id = "+personel_id+"";
			try {
				statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sorgu);
				while (rs.next()) {
					int id = rs.getInt("id");
					String ad = rs.getString("ad");
					String soyad = rs.getString("soyad");
					String departman = rs.getString("departman");
					String maas = rs.getString("maas");
					System.out.println("ID: " + id + " | Ad: " + ad + " | Soyad: " + soyad + " | Departman: " + departman + " | Maa�: " + maas + "\n");
				}

			} catch (Exception e) {
				System.out.println("Personel id ile getirilemedi...");
			}	

		} else if(kisi_islem.equals("2")) {

			System.out.println("Personel ad� giriniz:");
			String personel_adi = scan.nextLine();
			String sorgu = "Select * from personeller where ad = '"+personel_adi+"'";

			try {
				statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sorgu);
				while (rs.next()) {
					int id = rs.getInt("id");
					String ad = rs.getString("ad");
					String soyad = rs.getString("soyad");
					String departman = rs.getString("departman");
					String maas = rs.getString("maas");
					System.out.println("ID: " + id + " | Ad: " + ad + " | Soyad: " + soyad + " | Departman: " + departman + " | Maa�: " + maas + "\n");
				}

			} catch (Exception e) {
				System.out.println("Personel ad� ile getirilemedi...");
			}

		} else {
			System.out.println("Ge�ersiz Personel ad� ile getirme i�lemi giri�i yapt�n�z.....");
		}
	}

	public void personelEkle() {
		
		try {
			statement = con.createStatement();

			Scanner scan = new Scanner(System.in);

			System.out.print("Ad giriniz : ");
			String ad = scan.nextLine();
			System.out.print("Soyad giriniz : ");
			String soyad = scan.nextLine();
			System.out.print("Departman giriniz : ");
			String departman = scan.nextLine();
			System.out.print("Maa� giriniz : ");
			String maas = scan.nextLine();
			String sorgu = "Insert Into personeller (ad,soyad,departman,maas) VALUES ('"+ad+"','"+soyad+"','"+departman+"','"+maas+"')";

			statement.executeUpdate(sorgu);


		} catch (Exception e) {
			System.out.println("Personel ekleme Exception f�rlatt�.");
		}
		
		
	}

	public void personelSil() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Personel neye g�re silinsin?\n"
				+ "1. id\n"
				+ "2. ad");
		String kisi_islem = scan.nextLine();
		if (kisi_islem.equals("1")) {
			System.out.println("Silinecek Personel id giriniz:");
			int personel_id = scan.nextInt();
			String sorgu = "Delete from personeller where id = "+personel_id+"";
			try {
				statement = con.createStatement();
				int deger = statement.executeUpdate(sorgu);
				System.out.println("Silinen ki�i say�s� : "+deger);

			} catch (Exception e) {
				System.out.println("Personel id ile silinemedi...");
			}	

		} else if(kisi_islem.equals("2")) {

			System.out.println("Silinecek Personel ad� giriniz:");
			String personel_adi = scan.nextLine();
			String sorgu = "Delete from personeller where ad = '"+personel_adi+"'";

			try {
				statement = con.createStatement();
				int deger = statement.executeUpdate(sorgu);
				System.out.println("Silinen ki�i say�s� : "+deger);

			} catch (Exception e) {
				System.out.println("Personel ad� ile silinemedi...");
			}	

		} else {
			System.out.println("Ge�ersiz Personel ad� ile getirme i�lemi giri�i yapt�n�z.....");
		}
	}

	public void personelGuncelle() {
		Scanner scan = new Scanner(System.in);
		System.out.println("G�ncellenecek Personel id'sini giriniz:");
		String kisi_id = scan.nextLine();
		
		System.out.println("G�ncelleme neye g�re yap�lacak? (ad,soyad,departman,maas)");
		String islem_guncel = scan.nextLine();
		if (islem_guncel.equals("ad")) {
			try {
				statement = con.createStatement();
				System.out.print("G�ncel Ad giriniz : ");
				String ad = scan.nextLine();
				String sorgu = "Update personeller Set ad='"+ad+"' where id="+kisi_id+"";
				statement.executeUpdate(sorgu);
				
			} catch (Exception e) {
				System.out.println("Personel ada g�re g�ncelleme Exception f�rlatt�.");
			}
		} else if(islem_guncel.equals("soyad")) {
			try {
				statement = con.createStatement();
				System.out.print("G�ncel Soyad giriniz : ");
				String soyad = scan.nextLine();
				String sorgu = "Update personeller Set soyad='"+soyad+"' where id="+kisi_id+"";
				statement.executeUpdate(sorgu);
				
			} catch (Exception e) {
				System.out.println("Personel soyada g�re g�ncelleme Exception f�rlatt�.");
			}
		} else if(islem_guncel.equals("departman")) {
			try {
				statement = con.createStatement();
				System.out.print("G�ncel Departman giriniz : ");
				String departman = scan.nextLine();
				String sorgu = "Update personeller Set departman='"+departman+"' where id="+kisi_id+"";
				statement.executeUpdate(sorgu);
				
			} catch (Exception e) {
				System.out.println("Personel departmana g�re g�ncelleme Exception f�rlatt�.");
			}
		} else if(islem_guncel.equals("maas")) {
			try {
				statement = con.createStatement();
				System.out.print("G�ncel Maa� giriniz : ");
				String maas = scan.nextLine();
				String sorgu = "Update personeller Set maas='"+maas+"' where id="+kisi_id+"";
				statement.executeUpdate(sorgu);
				
			} catch (Exception e) {
				System.out.println("Personel maasa g�re g�ncelleme Exception f�rlatt�.");
			}
		} else {
			System.out.println("Ge�ersiz g�ncelleme i�lemi giri�i yapt�n�z...");
		}
		
	}

}
