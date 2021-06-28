import java.util.Scanner;

public class IslemSecimi {


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
		PersonelIslemleri personelIslemleri = new PersonelIslemleri();


		while(true) {


			System.out.println("************************");
			System.out.print("��lemi Se�iniz: ");
			String islem=scanner.nextLine();
			if (islem.equals("q")) {
				System.out.println("��k�� Ba�ar�l�...");
				break;
			} else if (islem.equals("1")) {
				personelIslemleri.personelleriGetir();
				System.out.println("Personeller ba�ar�yla g�r�nt�lendi...");

			} else if (islem.equals("2")) {
				System.out.print("Getirilecek Personel id'si giriniz : ");
				int personel_id = scanner.nextInt();
				personelIslemleri.personelGetir(personel_id);
				System.out.println("Personel ba�ar�yla g�r�nt�lendi... ");


			} else if (islem.equals("3")) {
				System.out.print("Ad giriniz : ");
				String ad = scanner.nextLine();
				System.out.print("Soyad giriniz : ");
				String soyad = scanner.nextLine();
				System.out.print("Departman giriniz : ");
				String departman = scanner.nextLine();
				System.out.print("Maa� giriniz : ");
				String maas = scanner.nextLine();

				personelIslemleri.personelEkle(ad,soyad,departman,maas);
				System.out.println("Personel ba�ar�yla eklendi... ");
			} else if (islem.equals("4")) {
				System.out.println("Silinecek Personel id giriniz:");
				int personel_id = scanner.nextInt();
				personelIslemleri.personelSil(personel_id);
				System.out.println("Personel ba�ar�yla silindi... ");
			} else if (islem.equals("5")) {
				System.out.print("G�ncellenecek Personel id'sini giriniz:");
				int personel_id = scanner.nextInt();
				personelIslemleri.personelGuncelle(personel_id);
				System.out.println("Personel bilgileri ba�ar�yla g�ncellendi...");
			} else {
				System.out.println("Ge�ersiz i�lem giri�i yapt�n�z.....");
			}

		}

		scanner.close();


	}




}
