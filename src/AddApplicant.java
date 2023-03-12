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
import java.sql.SQLException;
import java.awt.Panel;
import java.io.File;
import java.sql.*;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class AddApplicant {
	private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private HBox hbox;
	ListView<String> lv = null;

	public AddApplicant() {

	}

	public Scene getAddApplicant() throws ClassNotFoundException, SQLException {
		StackPane root = new StackPane();
		root.setPadding(new Insets(100, 100, 100, 100));
		root.setStyle("-fx-background-color: grey;");

		hbox = new HBox();
		hbox.setStyle("-fx-background-color: #3287A8;");
		hbox.setMinWidth(screenBounds.getWidth() * 0.8);
		hbox.setMaxWidth(screenBounds.getWidth() * 0.8);
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);

		StackPane.setAlignment(hbox, Pos.CENTER);

		hbox.getChildren().addAll(getLeft(), getRight());

		root.getChildren().add(hbox);
		Scene scene = new Scene(root, screenBounds.getWidth() - 2, screenBounds.getHeight() - 80);
		return scene;
	}

	public VBox getLeft() {
		VBox vb = new VBox();
		vb.setMinWidth(screenBounds.getWidth() / 3);
		vb.setMaxWidth(screenBounds.getWidth() / 3);
		vb.setAlignment(Pos.TOP_CENTER);

		// TOP
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.setMinSize(screenBounds.getWidth() / 3, 100);
		pane.setAlignment(Pos.CENTER);

		Text text = new Text("Add Applicant");
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

		TextField firstName = new TextField();
		Label firstlabel = new Label("First name: ", firstName);
		firstlabel.setContentDisplay(ContentDisplay.BOTTOM);
		firstlabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		firstlabel.setTextFill(Color.WHITE);

		TextField lastName = new TextField();
		Label lastlabel = new Label("Last name: ", lastName);
		lastlabel.setContentDisplay(ContentDisplay.BOTTOM);
		lastlabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		lastlabel.setTextFill(Color.WHITE);

		names.getChildren().addAll(firstlabel, lastlabel);

		fields.getChildren().add(names);

		HBox idPhone = new HBox();
		idPhone.setSpacing(20);
		idPhone.setAlignment(Pos.CENTER);

		TextField appId = new TextField();
		Label idlabel = new Label("Applicant ID: ", appId);
		idlabel.setContentDisplay(ContentDisplay.BOTTOM);
		idlabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		idlabel.setTextFill(Color.WHITE);

		TextField phone = new TextField();
		Label phonelabel = new Label("Phone no.: ", phone);
		phonelabel.setContentDisplay(ContentDisplay.BOTTOM);
		phonelabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		phonelabel.setTextFill(Color.WHITE);

		idPhone.getChildren().addAll(idlabel, phonelabel);
		fields.getChildren().add(idPhone);

		HBox ageRev = new HBox();
		ageRev.setSpacing(20);
		ageRev.setAlignment(Pos.CENTER);

		TextField age = new TextField();
		Label agelabel = new Label("Age: ", age);
		agelabel.setContentDisplay(ContentDisplay.BOTTOM);
		agelabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		agelabel.setTextFill(Color.WHITE);

		ComboBox<String> review = new ComboBox<String>();
		review.getItems().addAll("Qualified", "Under-Qualified", "Waitlist");
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

		TextArea com = new TextArea();
		com.setWrapText(true);
		com.setMaxSize(525, 150);
		Label comLabel = new Label("Comments: ", com);
		comLabel.setContentDisplay(ContentDisplay.BOTTOM);
		comLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		comLabel.setTextFill(Color.WHITE);

		cmtB.getChildren().add(comLabel);
		fields.getChildren().add(cmtB);

		Button addEmp = new Button("ADD APPLICANT");
		addEmp.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		addEmp.setTextFill(Color.WHITE);
		addEmp.setMinWidth(fields.getMinWidth() / 3);

		addEmp.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				vb.setCursor(Cursor.HAND);
			}
		});

		addEmp.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				vb.setCursor(Cursor.DEFAULT);
			}
		});

		addEmp.setOnAction(e -> {
			EARSdatabase db = null;

			try {
				db = new EARSdatabase();
				db.updateDatabase(
						"INSERT INTO `applicant`(`applicant_id`, `first_name`, `last_name`, `phone`, `age`, `comments`, `review`)"
						+ " VALUES ('" + appId.getText() + "','" + firstName.getText() + "','" + 
								lastName.getText() +"','" + phone.getText() + "','" + age.getText() + "','" + 
						com.getText()+ "','" + review.getValue() +"')");
				
				lv.setItems(getList());
			} catch (SQLException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}

		});

		fields.getChildren().add(addEmp);

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

	public VBox getRight() throws ClassNotFoundException, SQLException {
		VBox vb = new VBox();
		vb.setMinWidth(screenBounds.getWidth() / 3);
		vb.setMaxWidth(screenBounds.getWidth() / 3);

		// TOP
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.setMinSize(screenBounds.getWidth() / 3, 100);
		pane.setAlignment(Pos.CENTER);

		Text text = new Text("Current List");
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font-size: 50;");

		pane.getChildren().add(text);

		vb.getChildren().add(pane);

		// CENTER
		HBox attr = new HBox();
		attr.setAlignment(Pos.CENTER_LEFT);
		attr.setSpacing(40);
		attr.setPadding(new Insets(25, 25, 25, 25));
		attr.setStyle("-fx-background-color:  #3e32a8;");

		Text appid = new Text("Applicant ID");
		appid.setStyle("-fx-font-family: Arial; -fx-font-size: 25;");
		appid.minWidth(vb.getMaxWidth() / 3);
		appid.setFill(Color.WHITE);

		Text fname = new Text("First Name");
		fname.setStyle("-fx-font-family: Arial; -fx-font-size: 25;");
		fname.minWidth(vb.getMaxWidth() / 3);
		fname.setFill(Color.WHITE);

		Text lname = new Text("Last Name");
		lname.setStyle("-fx-font-family: Arial; -fx-font-size: 25;");
		lname.minWidth(vb.getMaxWidth() / 3);
		lname.setFill(Color.WHITE);

		attr.getChildren().addAll(appid, fname, lname);

		vb.getChildren().add(attr);

		lv = new ListView<String>();
		lv.setStyle("-fx-font-family: Arial; -fx-font-size: 25;");
		lv.setItems(getList());

		vb.getChildren().add(lv);
		HBox.setMargin(vb, new Insets(25, 25, 25, 25));
		return vb;
	}

	public ObservableList<String> getList() throws ClassNotFoundException, SQLException {
		EARSdatabase db = new EARSdatabase();

		ResultSet rs = db.queryDatabase("SELECT COUNT(*) FROM applicant;");
		rs.next();
		int num = Integer.parseInt(rs.getString(1));

		String[] array = new String[num];

		rs = db.queryDatabase("SELECT applicant_id, first_name, last_name " + "FROM applicant; ");

		for (int i = 0; i < array.length; i++) {
			rs.next();
			array[i] = rs.getString(1) + "                  " + rs.getString(2) + "                  "
					+ rs.getString(3);
		}

		ObservableList<String> list = FXCollections.observableArrayList(array);

		db.closeConnection();

		return list;
	}
}
