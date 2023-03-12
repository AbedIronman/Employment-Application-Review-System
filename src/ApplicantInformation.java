import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.event.*;
import javafx.scene.shape.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Panel;
import java.io.File;
import java.sql.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class ApplicantInformation {
	private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private HBox hbox;
	private ListView<String> lv = null;
	TextField firstName = new TextField();
	TextField lastName = new TextField();
	TextField appId = new TextField();
	TextField phone = new TextField();
	TextField age = new TextField();
	
	TextField review = new TextField();;
	TextArea com = new TextArea();
	
	public ApplicantInformation() {

	}

	public Scene getApplicantInformation() throws ClassNotFoundException, SQLException {
		StackPane root = new StackPane();
		root.setPadding(new Insets(100, 100, 100, 100));
		root.setStyle("-fx-background-color: grey;");

		hbox = new HBox();
		hbox.setStyle("-fx-background-color: #3287A8;");
		hbox.setMinWidth(screenBounds.getWidth() * 0.8);
		hbox.setMaxWidth(screenBounds.getWidth() * 0.8);
		hbox.setSpacing(50);
		hbox.setAlignment(Pos.CENTER);

		StackPane.setAlignment(hbox, Pos.CENTER);

		hbox.getChildren().addAll(getLeft(), getRight());

		root.getChildren().add(hbox);
		Scene scene = new Scene(root, screenBounds.getWidth() - 2, screenBounds.getHeight() - 80);
		return scene;
	}

	public VBox getLeft() {
		VBox vb = new VBox();
		vb.setMinWidth(screenBounds.getWidth() / 5);
		vb.setMaxWidth(screenBounds.getWidth() / 5);
		vb.setAlignment(Pos.TOP_LEFT);

		// TOP
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.setMaxSize(screenBounds.getWidth() / 5, 100);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(25, 25, 25, 25));

		Text text = new Text("Find Applicant");
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font-size: 30;");

		pane.getChildren().add(text);

		vb.getChildren().add(pane);

		// CENTER
		VBox center = new VBox();
		center.setMinWidth(screenBounds.getWidth() / 5);
		center.setMaxWidth(screenBounds.getWidth() / 5);
		center.setSpacing(20);
		center.setAlignment(Pos.CENTER);
		center.setStyle("-fx-background-color: #3e32a8;");
		center.setPadding(new Insets(25, 25, 25, 25));

		TextField value = new TextField();
		value.setMaxWidth(vb.getMinWidth() / 2);
		value.setMinWidth(vb.getMinWidth() / 2);
		Label label = new Label("Enter Applicant ID: ", value);
		label.setContentDisplay(ContentDisplay.BOTTOM);
		label.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		label.setTextFill(Color.WHITE);

		Button delete = new Button("FIND");
		delete.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		delete.setTextFill(Color.WHITE);
		delete.setMinWidth(vb.getMinWidth() / 3);

		delete.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				center.setCursor(Cursor.HAND);
			}
		});

		delete.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				center.setCursor(Cursor.DEFAULT);
			}
		});

		delete.setOnAction(e -> {
			try {
				updateDatabase(value);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		center.getChildren().addAll(label, delete);

		vb.getChildren().add(center);

		// BOTTOM
		StackPane pane2 = new StackPane();
		pane2.setStyle("-fx-background-color: black;");
		pane2.setMaxSize(screenBounds.getWidth() / 5, 100);
		pane2.setAlignment(Pos.CENTER);
		pane2.setPadding(new Insets(25, 25, 25, 25));

		Button back = new Button("BACK");
		back.setStyle("-fx-background-color: #9e2105; -fx-font-size: 20;");
		back.setTextFill(Color.WHITE);
		back.setMinWidth(vb.getMinWidth() / 3);

		back.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				pane2.setCursor(Cursor.HAND);
			}
		});

		back.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				pane2.setCursor(Cursor.DEFAULT);
			}
		});

		back.setOnAction(e -> {
			Main.loadWelcomeWindow();
		});

		pane2.getChildren().add(back);

		vb.getChildren().add(pane2);

		HBox.setMargin(vb, new Insets(25, 25, 25, 25));
		return vb;
	}

	public void updateDatabase(TextField userId) throws ClassNotFoundException, SQLException {
		EARSdatabase db = new EARSdatabase();
		ResultSet rs = db.queryDatabase("SELECT * FROM applicant WHERE applicant_id = '" + userId.getText() + "'");
		rs.next();
		String fname = rs.getString(2);
		String lname = rs.getString(3);
		String id = rs.getString(1);
		String phoneN = rs.getString(4);
		String ageN = rs.getString(5);
		String comnt = rs.getString(6);
		String rev = rs.getString(7);
		firstName.setText(fname);
		lastName.setText(lname);
		appId.setText(id);
		phone.setText(phoneN);
		age.setText(ageN);
		review.setText(rev);
		com.setText(comnt);
	}

	public VBox getRight() {
		VBox vb = new VBox();
		vb.setMinWidth(screenBounds.getWidth() / 3);
		vb.setMaxWidth(screenBounds.getWidth() / 3);
		vb.setAlignment(Pos.TOP_CENTER);

		// TOP
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.setMinSize(screenBounds.getWidth() / 3, 100);
		pane.setAlignment(Pos.CENTER);

		Text text = new Text("Applicant Information");
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font-size: 50;");

		pane.getChildren().add(text);

		vb.getChildren().add(pane);

		// CENTER
		VBox fields = new VBox();
		fields.setMinWidth(screenBounds.getWidth() / 3);
		fields.setMaxWidth(screenBounds.getWidth() / 3);
		fields.setSpacing(20);
		fields.setAlignment(Pos.CENTER);
		fields.setStyle("-fx-background-color: #3e32a8;");
		fields.setPadding(new Insets(25, 25, 25, 25));

		HBox names = new HBox();
		names.setSpacing(20);
		names.setAlignment(Pos.CENTER);

		firstName.setEditable(false);
		Label firstlabel = new Label("First name: ", firstName);
		firstlabel.setContentDisplay(ContentDisplay.BOTTOM);
		firstlabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		firstlabel.setTextFill(Color.WHITE);

		lastName.setEditable(false);
		Label lastlabel = new Label("Last name: ", lastName);
		lastlabel.setContentDisplay(ContentDisplay.BOTTOM);
		lastlabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		lastlabel.setTextFill(Color.WHITE);

		names.getChildren().addAll(firstlabel, lastlabel);
		fields.getChildren().add(names);

		HBox idPhone = new HBox();
		idPhone.setSpacing(20);
		idPhone.setAlignment(Pos.CENTER);

		appId.setEditable(false);
		Label idlabel = new Label("Applicant ID: ", appId);
		idlabel.setContentDisplay(ContentDisplay.BOTTOM);
		idlabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		idlabel.setTextFill(Color.WHITE);

		phone.setEditable(false);
		Label phonelabel = new Label("Phone no.: ", phone);
		phonelabel.setContentDisplay(ContentDisplay.BOTTOM);
		phonelabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		phonelabel.setTextFill(Color.WHITE);

		idPhone.getChildren().addAll(idlabel, phonelabel);
		fields.getChildren().add(idPhone);

		HBox ageRev = new HBox();
		ageRev.setSpacing(20);
		ageRev.setAlignment(Pos.CENTER);

		age.setEditable(false);
		Label agelabel = new Label("Age: ", age);
		agelabel.setContentDisplay(ContentDisplay.BOTTOM);
		agelabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		agelabel.setTextFill(Color.WHITE);

		review.setEditable(false);
		review.setMaxWidth(fields.getMinWidth() / 2.5);
		review.setMinWidth(fields.getMinWidth() / 2.5);
		Label revLabel = new Label("Review: ", review);
		revLabel.setContentDisplay(ContentDisplay.BOTTOM);
		revLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		revLabel.setTextFill(Color.WHITE);

		ageRev.getChildren().addAll(agelabel, revLabel);

		fields.getChildren().add(ageRev);

		HBox cmtB = new HBox();
		cmtB.setSpacing(20);
		cmtB.setAlignment(Pos.CENTER);

		com.setEditable(false);
		com.setMaxSize(525, 150);
		com.setWrapText(true);
		Label comLabel = new Label("Comments: ", com);
		comLabel.setContentDisplay(ContentDisplay.BOTTOM);
		comLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		comLabel.setTextFill(Color.WHITE);

		cmtB.getChildren().add(comLabel);
		fields.getChildren().add(cmtB);


		vb.getChildren().add(fields);

		// BOTTOM
		StackPane pane2 = new StackPane();
		pane2.setStyle("-fx-background-color: black;");
		pane2.setMinSize(screenBounds.getWidth() / 3, 100);
		pane2.setAlignment(Pos.CENTER);

		Button back = new Button("BACK");
		back.setStyle("-fx-background-color: #9e2105; -fx-font-size: 20;");
		back.setTextFill(Color.WHITE);
		back.setMinWidth(fields.getMinWidth() / 3);

		back.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				pane2.setCursor(Cursor.HAND);
			}
		});

		back.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				pane2.setCursor(Cursor.DEFAULT);
			}
		});

		back.setOnAction(e -> {
			Main.loadWelcomeWindow();
		});

		pane2.getChildren().add(back);

		vb.getChildren().add(pane2);

		HBox.setMargin(vb, new Insets(25, 25, 25, 25));
		return vb;
	}

}
