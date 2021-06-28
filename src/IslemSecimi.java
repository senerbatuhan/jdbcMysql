import java.util.Scanner;

public class IslemSecimi {


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
		PersonelIslemleri personelIslemleri = new PersonelIslemleri();


		while(true) {


			System.out.println("************************");
			System.out.print("Ýþlemi Seçiniz: ");
			String islem=scanner.nextLine();
			if (islem.equals("q")) {
				System.out.println("Çýkýþ Baþarýlý...");
				break;
			} else if (islem.equals("1")) {
				personelIslemleri.personelleriGetir();
				System.out.println("Personeller baþarýyla görüntülendi...");

			} else if (islem.equals("2")) {
				System.out.print("Getirilecek Personel id'si giriniz : ");
				int personel_id = scanner.nextInt();
				personelIslemleri.personelGetir(personel_id);
				System.out.println("Personel baþarýyla görüntülendi... ");


			} else if (islem.equals("3")) {
				System.out.print("Ad giriniz : ");
				String ad = scanner.nextLine();
				System.out.print("Soyad giriniz : ");
				String soyad = scanner.nextLine();
				System.out.print("Departman giriniz : ");
				String departman = scanner.nextLine();
				System.out.print("Maaþ giriniz : ");
				String maas = scanner.nextLine();

				personelIslemleri.personelEkle(ad,soyad,departman,maas);
				System.out.println("Personel baþarýyla eklendi... ");
			} else if (islem.equals("4")) {
				System.out.println("Silinecek Personel id giriniz:");
				int personel_id = scanner.nextInt();
				personelIslemleri.personelSil(personel_id);
				System.out.println("Personel baþarýyla silindi... ");
			} else if (islem.equals("5")) {
				System.out.print("Güncellenecek Personel id'sini giriniz:");
				int personel_id = scanner.nextInt();
				personelIslemleri.personelGuncelle(personel_id);
				System.out.println("Personel bilgileri baþarýyla güncellendi...");
			} else {
				System.out.println("Geçersiz iþlem giriþi yaptýnýz.....");
			}

		}

		scanner.close();


	}




}
