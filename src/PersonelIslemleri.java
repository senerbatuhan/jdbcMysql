import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PersonelIslemleri {
	
	Statement statement = null;
	Baglanti baglanti = new Baglanti();

	public void personelleriGetir() {


		String sorgu = "Select * from personeller";
		try {
			statement = baglanti.getCon().createStatement();
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
			System.out.println("Personeller getirilemedi...");
		}

		
	}
	
	
	public void personelGetir(int id) {
		

		if ( id == (int)id ) {

			String sorgu = "Select * from personeller where id = "+id+"";
			try {
				statement = baglanti.getCon().createStatement();
				ResultSet rs = statement.executeQuery(sorgu);
				while (rs.next()) {

					String ad = rs.getString("ad");
					String soyad = rs.getString("soyad");
					String departman = rs.getString("departman");
					String maas = rs.getString("maas");
					System.out.println("ID: " + id + " | Ad: " + ad + " | Soyad: " + soyad + " | Departman: " + departman + " | Maa�: " + maas + "\n");
				}

			} catch (Exception e) {
				System.out.println("Personel id ile getirilemedi...");
			}	

		} else {
			System.out.println("Ge�ersiz Personel id giri�i yapt�n�z.....");
		}
	}
	
	
	public void personelEkle(String ad,String soyad,String departman,String maas) {
		
		
		try {
			statement = baglanti.getCon().createStatement();
 
			String sorgu = "Insert Into personeller (ad,soyad,departman,maas) VALUES ('"+ad+"','"+soyad+"','"+departman+"','"+maas+"')";

			statement.executeUpdate(sorgu);


		} catch (Exception e) {
			System.out.println("Personel ekleme Exception f�rlatt�.");
		}


	}

	
	public void personelSil(int id) {

		if ( id == (int)id ) {
			
			String sorgu = "Delete from personeller where id = "+id+"";
			try {
				statement = baglanti.getCon().createStatement();
				int deger = statement.executeUpdate(sorgu);
				System.out.println("Silinen ki�i say�s� : "+deger+"\n");

			} catch (Exception e) {
				System.out.println("Personel id ile silinemedi...");
			}	

		}  else {
			System.out.println("Ge�ersiz Personel ad� ile getirme i�lemi giri�i yapt�n�z.....");
		}
	}

	
	public void personelGuncelle(int id) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("G�ncelleme neye g�re yap�lacak? (ad,soyad,departman,maas)");
		String islem_guncel = scan.nextLine();
		if (islem_guncel.equals("ad")) {
			try {
				statement = baglanti.getCon().createStatement();
				System.out.print("G�ncel Ad giriniz : ");
				String ad = scan.nextLine();
				
				String sorgu = "Update personeller Set ad='"+ad+"' where id="+id+"";
				statement.executeUpdate(sorgu);

			} catch (Exception e) {
				System.out.println("Personel ada g�re g�ncelleme Exception f�rlatt�.");
			}
		} else if(islem_guncel.equals("soyad")) {
			try {
				statement = baglanti.getCon().createStatement();

				System.out.print("G�ncel Soyad giriniz : ");
				String soyad = scan.nextLine();
				String sorgu = "Update personeller Set soyad='"+soyad+"' where id="+id+"";
				statement.executeUpdate(sorgu);

			} catch (Exception e) {
				System.out.println("Personel soyada g�re g�ncelleme Exception f�rlatt�.");
			}
		} else if(islem_guncel.equals("departman")) {
			System.out.print("G�ncel Departman giriniz : ");
			String departman = scan.nextLine();
			try {
				statement = baglanti.getCon().createStatement();
				String sorgu = "Update personeller Set departman='"+departman+"' where id="+id+"";
				statement.executeUpdate(sorgu);

			} catch (Exception e) {
				System.out.println("Personel departmana g�re g�ncelleme Exception f�rlatt�.");
			}
		} else if(islem_guncel.equals("maas")) {
			try {
				statement = baglanti.getCon().createStatement();
				System.out.print("G�ncel Maa� giriniz : ");
				String maas = scan.nextLine();
				String sorgu = "Update personeller Set maas='"+maas+"' where id="+id+"";
				statement.executeUpdate(sorgu);

			} catch (Exception e) {
				System.out.println("Personel maasa g�re g�ncelleme Exception f�rlatt�.");
			}
		} else {
			System.out.println("Ge�ersiz g�ncelleme i�lemi giri�i yapt�n�z...");
		}


	}
	
	
	
	
}
