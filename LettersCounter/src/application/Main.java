package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			//Kreacja okienka :)
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,550,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//Zaczynam od zrobienia prostych rzeczy czyli labelek
			Label CodeSrc = new Label("ród³o:");
			Label CodeRes = new Label("Wynik operacji:");
			Label Operation = new Label("Operacja:");
			
			//Tworzê pola tekstowe, do wprowadzania danych i wyœwietlania
			TextField inputTxt = new TextField();
			TextField outputTxt = new TextField();
			
			//Teraz powa¿ne rzeczy, czyli RadioButtony i Toogle Group;
			RadioButton coding = new RadioButton("kodowanie");
			coding.setSelected(true);
			RadioButton decoding = new RadioButton("dekodowanie");

			ToggleGroup RadioOps = new ToggleGroup();
			coding.setToggleGroup(RadioOps);
			decoding.setToggleGroup(RadioOps);
			
			//Tworzê i nadajê funckjonalnoœæ przyciskom
			Button DoIt = new Button("Wykonaj");
			DoIt.setOnAction(event -> {
				try {
					Calculations testString = new Calculations();
					if (coding.isSelected()) {
						outputTxt.setText(testString.encode(inputTxt.getText()));
					}
					if (decoding.isSelected()) {
						outputTxt.setText(testString.decode(inputTxt.getText()));
					}
				} catch(Exception ine) {
					Alert AlarmWin = new Alert(AlertType.INFORMATION);
					AlarmWin.setTitle("B³¹d!");
					AlarmWin.setHeaderText(null);
					AlarmWin.setContentText("Pole tekstowe jest puste lub wprowadzony tekst jest niew³aœciwy");
					
					AlarmWin.showAndWait();
				};
			});
			DoIt.getStyleClass().add("Button");
			
			Button CopySwap = new Button("Kopiuj");
			CopySwap.setOnAction(event -> {
				try {
					inputTxt.setText(outputTxt.getText());
					outputTxt.setText("");
				} catch(Exception ine) {
					Alert AlarmWin = new Alert(AlertType.INFORMATION);
					AlarmWin.setTitle("B³¹d!");
					AlarmWin.setHeaderText(null);
					if (outputTxt.getText() == "") AlarmWin.setContentText("Pole: 'Wynik operacji:' jest puste");
					else AlarmWin.setContentText("Operacja nie powiod³a siê");
					AlarmWin.showAndWait();
				};
			});
			CopySwap.getStyleClass().add("Button");
			
			//Tworzê kontenery, najwiêkszy oraz dwa na dwie strony ekranu aplikacji
			
			GridPane LeftWing = new GridPane();
			LeftWing.getStyleClass().add("grid");
			
			VBox RightWing = new VBox();
			RightWing.getStyleClass().add("rightWing");
			
			HBox LabelButtRow = new HBox();
			LabelButtRow.getStyleClass().add("thirdRow");
			
			Group thatThirdRow = new Group();
			
			//Poma³u ³adujê rzeczy do GridPane'a
			LeftWing.add(CodeSrc, 0, 0);
			LeftWing.add(inputTxt, 0, 1);
			
			LabelButtRow.getChildren().addAll(CodeRes, CopySwap);
			LeftWing.add(LabelButtRow, 0, 2);
			
			LeftWing.add(outputTxt, 0, 3);
			LeftWing.add(DoIt, 0, 5);
			
			//Wk³adam ustawienia programu na praw¹ stronê ekranu aplikacji
			RightWing.getChildren().addAll(Operation, coding, decoding);
			
			//Tworzê i nadajê funckjonalnoœæ przyciskom
			root.setLeft(LeftWing);
			root.setCenter(RightWing);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		//"MMMMAAaa7854777''';/';[]]';/...,,,,ddhrte
	}
}
