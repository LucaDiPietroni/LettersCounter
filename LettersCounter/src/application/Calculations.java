package application;

public class Calculations {
	
	public String encode (String plainText) {
		
		//Tworz� tablic� znak�w i wyznaczam jej d�ugo��
		char[] plainTxtArray = plainText.toCharArray();
		int arraysLength = plainTxtArray.length;
		
		//Tworz� licznik danego znaku i zmienn�, kt�ra mi przechowa zakodowany napis
		int licznik = 0;
		String newTxt = "";
		
		//Obsuguj� pierwszy znak
		licznik++;
		newTxt = newTxt + plainTxtArray[0];
		
		//W�a�ciwe kodowanie
		for(int i = 2; i <= arraysLength; i++) {
			
			//Liczenie kolejnych takich samych znak�w
			if (plainTxtArray[i-1] == plainTxtArray[i-2]) {
				licznik++;
			}
			
			//Przypadek, gdy znak jest inny od poprzedniego
			if (plainTxtArray[i-1] != plainTxtArray[i-2]) {
				newTxt = newTxt + licznik + "," + plainTxtArray[i-1];
				licznik = 1;
			}
		}
		newTxt = newTxt + licznik;
		
		return newTxt;
	}
	
	public String decode (String encodedText) {
		
		//Tworz� tablic� znak�w i wyznaczam jej d�ugo��
		char[] encoTxtArray = encodedText.toCharArray();
		int arraysLength = encoTxtArray.length;
		
		//Obs�uguj� pierwszy znak
		String sign = Character.toString(encoTxtArray[0]);
		
		//Tworz� zmienne potrzebne w p�tli
		String strOfNum = "";
		String decodedStr = "";
		int numOfSigns;
		
		for (int i = 1; i < arraysLength; i++) {
			
			//Przypadek, gdy znak jest cyfr� - sk�adow� liczby ilo�ci, ale liczymy przecinki
			if (Character.toString(encoTxtArray[i]).matches("\\d") && sign.matches(",")) {
				strOfNum = strOfNum + encoTxtArray[i];
			}
			//Przypadek, gdy znak jest cyfr� - znakiem samym w sobie
			else if (Character.toString(encoTxtArray[i]).matches("\\d") && !sign.matches(",") && Character.toString(encoTxtArray[i-1]).matches(",")) {
				sign = Character.toString(encoTxtArray[i]);
			}
			//Przypadek, gdy znak jest cyfr� - sk�adow� liczby ilo�ci, ale nie liczymy przecink�w
			else if (Character.toString(encoTxtArray[i]).matches("\\d") && !sign.matches(",") && !Character.toString(encoTxtArray[i-1]).matches(",")) {
				strOfNum = strOfNum + encoTxtArray[i];
			}
			//Przypadek, gdy trafiamy na przecinek - rozdzielnik
			else if (Character.toString(encoTxtArray[i]).matches(",") && !Character.toString(encoTxtArray[i-1]).matches(",")) {
				numOfSigns = Integer.parseInt(strOfNum);
				for (int j = 0; j < numOfSigns; j++) {
					decodedStr = decodedStr + sign;
				}
				strOfNum = "";
				sign = "";
			}
			//Przypadek, gdy trafiamy na przecinek - znak
//			else if (Character.toString(encoTxtArray[i]).matches(",") && Character.toString(encoTxtArray[i-1]).matches(",")) {
//				sign = Character.toString(encoTxtArray[i]);
//			}
			//Pozosta�e przypadki
			else {
				sign = Character.toString(encoTxtArray[i]);
			}
			
		}
		//Obs�uguj� ostatni znak
		numOfSigns = Integer.parseInt(strOfNum);
		for (int j = 0; j < numOfSigns; j++) {
			decodedStr = decodedStr + sign;
		}
		
		return decodedStr;
	}

}
