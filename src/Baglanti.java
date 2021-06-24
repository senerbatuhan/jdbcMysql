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

		String islemler = "1. Personelleri Görüntüle\n"
				+"2. Personel Görüntüle\n"
				+"3. Personel Ekleme\n"
				+"4. Personel Silme\n"
				+"5. Personel Güncelleme\n"
				+"Çýkýþ için q'ya basýn";

		System.out.println("************************");
		System.out.println(islemler);
		Scanner scanner = new Scanner(System.in);
		

		while(true) {

			
			System.out.println("************************");
			System.out.println("Ýþlemi Seçiniz: ");
			String islem=scanner.nextLine();
			if (islem.equals("q")) {
				System.out.println("Çýkýþ Baþarýlý...");
				break;
			}
			else if (islem.equals("1")) {
				calisanlarGetir();
				System.out.println("Personeller baþarýyla görüntülendi... ");
			}
			else if (islem.equals("2")) {
				personelGetir();
				System.out.println("Personel baþarýyla görüntülendi... ");
			}
			else if (islem.equals("3")) {
				personelEkle();
				System.out.println("Personel baþarýyla eklendi... ");
			}
			else if (islem.equals("4")) {
				personelSil();
				System.out.println("Personel baþarýyla silindi... ");
			}
			else if (islem.equals("5")) {
				personelGuncelle();
				System.out.println("Personel baþarýyla güncellendi... ");
			}
			else {
				System.out.println("Geçersiz iþlem giriþi yaptýnýz.....");
			}
		}




	}

	public Baglanti() {

		String url = "jdbc:mysql://" + host + ":" + port + "/" + db_ismi+ "?useUnicode=true&characterEncoding=utf8";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException ex){
			System.out.println("Driver Bulunamadý.....");
		}

		try {
			con = DriverManager.getConnection(url, kullanici_adi, parola);
			System.out.println("Baðlantý Baþarýlý.....");		

		} catch (SQLException ex) {
			System.out.println("Baðlantý Baþarýsýz.....");	
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

				System.out.println("ID: " + id + " | Ad: " + ad + " | Soyad: " + soyad + " | Departman: " + departman + " | Maaþ: " + maas + "\n");


			}

		} catch (SQLException ex) {
			// TODO: handle exception
			System.out.println("Personeller getirilemedi...");
		}
	}

	public void personelGetir() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Personel neye göre getirilsin?\n"
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
					System.out.println("ID: " + id + " | Ad: " + ad + " | Soyad: " + soyad + " | Departman: " + departman + " | Maaþ: " + maas + "\n");
				}

			} catch (Exception e) {
				System.out.println("Personel id ile getirilemedi...");
			}	

		} else if(kisi_islem.equals("2")) {

			System.out.println("Personel adý giriniz:");
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
					System.out.println("ID: " + id + " | Ad: " + ad + " | Soyad: " + soyad + " | Departman: " + departman + " | Maaþ: " + maas + "\n");
				}

			} catch (Exception e) {
				System.out.println("Personel adý ile getirilemedi...");
			}

		} else {
			System.out.println("Geçersiz Personel adý ile getirme iþlemi giriþi yaptýnýz.....");
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
			System.out.print("Maaþ giriniz : ");
			String maas = scan.nextLine();
			String sorgu = "Insert Into personeller (ad,soyad,departman,maas) VALUES ('"+ad+"','"+soyad+"','"+departman+"','"+maas+"')";

			statement.executeUpdate(sorgu);


		} catch (Exception e) {
			System.out.println("Personel ekleme Exception fýrlattý.");
		}
		
		
	}

	public void personelSil() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Personel neye göre silinsin?\n"
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
				System.out.println("Silinen kiþi sayýsý : "+deger);

			} catch (Exception e) {
				System.out.println("Personel id ile silinemedi...");
			}	

		} else if(kisi_islem.equals("2")) {

			System.out.println("Silinecek Personel adý giriniz:");
			String personel_adi = scan.nextLine();
			String sorgu = "Delete from personeller where ad = '"+personel_adi+"'";

			try {
				statement = con.createStatement();
				int deger = statement.executeUpdate(sorgu);
				System.out.println("Silinen kiþi sayýsý : "+deger);

			} catch (Exception e) {
				System.out.println("Personel adý ile silinemedi...");
			}	

		} else {
			System.out.println("Geçersiz Personel adý ile getirme iþlemi giriþi yaptýnýz.....");
		}
	}

	public void personelGuncelle() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Güncellenecek Personel id'sini giriniz:");
		String kisi_id = scan.nextLine();
		
		System.out.println("Güncelleme neye göre yapýlacak? (ad,soyad,departman,maas)");
		String islem_guncel = scan.nextLine();
		if (islem_guncel.equals("ad")) {
			try {
				statement = con.createStatement();
				System.out.print("Güncel Ad giriniz : ");
				String ad = scan.nextLine();
				String sorgu = "Update personeller Set ad='"+ad+"' where id="+kisi_id+"";
				statement.executeUpdate(sorgu);
				
			} catch (Exception e) {
				System.out.println("Personel ada göre güncelleme Exception fýrlattý.");
			}
		} else if(islem_guncel.equals("soyad")) {
			try {
				statement = con.createStatement();
				System.out.print("Güncel Soyad giriniz : ");
				String soyad = scan.nextLine();
				String sorgu = "Update personeller Set soyad='"+soyad+"' where id="+kisi_id+"";
				statement.executeUpdate(sorgu);
				
			} catch (Exception e) {
				System.out.println("Personel soyada göre güncelleme Exception fýrlattý.");
			}
		} else if(islem_guncel.equals("departman")) {
			try {
				statement = con.createStatement();
				System.out.print("Güncel Departman giriniz : ");
				String departman = scan.nextLine();
				String sorgu = "Update personeller Set departman='"+departman+"' where id="+kisi_id+"";
				statement.executeUpdate(sorgu);
				
			} catch (Exception e) {
				System.out.println("Personel departmana göre güncelleme Exception fýrlattý.");
			}
		} else if(islem_guncel.equals("maas")) {
			try {
				statement = con.createStatement();
				System.out.print("Güncel Maaþ giriniz : ");
				String maas = scan.nextLine();
				String sorgu = "Update personeller Set maas='"+maas+"' where id="+kisi_id+"";
				statement.executeUpdate(sorgu);
				
			} catch (Exception e) {
				System.out.println("Personel maasa göre güncelleme Exception fýrlattý.");
			}
		} else {
			System.out.println("Geçersiz güncelleme iþlemi giriþi yaptýnýz...");
		}
		
	}

}
