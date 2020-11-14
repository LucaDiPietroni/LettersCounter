package application;

public class Calculations {
	
	public String encode (String plainText) {
		
		//Tworzê tablicê znaków i wyznaczam jej d³ugoœæ
		char[] plainTxtArray = plainText.toCharArray();
		int arraysLength = plainTxtArray.length;
		
		//Tworzê licznik danego znaku i zmienn¹, która mi przechowa zakodowany napis
		int licznik = 0;
		String newTxt = "";
		
		//Obsugujê pierwszy znak
		licznik++;
		newTxt = newTxt + plainTxtArray[0];
		
		//W³aœciwe kodowanie
		for(int i = 2; i <= arraysLength; i++) {
			
			//Liczenie kolejnych takich samych znaków
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
		
		//Tworzê tablicê znaków i wyznaczam jej d³ugoœæ
		char[] encoTxtArray = encodedText.toCharArray();
		int arraysLength = encoTxtArray.length;
		
		//Obs³ugujê pierwszy znak
		String sign = Character.toString(encoTxtArray[0]);
		
		//Tworzê zmienne potrzebne w pêtli
		String strOfNum = "";
		String decodedStr = "";
		int numOfSigns;
		
		for (int i = 1; i < arraysLength; i++) {
			
			//Przypadek, gdy znak jest cyfr¹ - sk³adow¹ liczby iloœci, ale liczymy przecinki
			if (Character.toString(encoTxtArray[i]).matches("\\d") && sign.matches(",")) {
				strOfNum = strOfNum + encoTxtArray[i];
			}
			//Przypadek, gdy znak jest cyfr¹ - znakiem samym w sobie
			else if (Character.toString(encoTxtArray[i]).matches("\\d") && !sign.matches(",") && Character.toString(encoTxtArray[i-1]).matches(",")) {
				sign = Character.toString(encoTxtArray[i]);
			}
			//Przypadek, gdy znak jest cyfr¹ - sk³adow¹ liczby iloœci, ale nie liczymy przecinków
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
			//Pozosta³e przypadki
			else {
				sign = Character.toString(encoTxtArray[i]);
			}
			
		}
		//Obs³ugujê ostatni znak
		numOfSigns = Integer.parseInt(strOfNum);
		for (int j = 0; j < numOfSigns; j++) {
			decodedStr = decodedStr + sign;
		}
		
		return decodedStr;
	}

}
