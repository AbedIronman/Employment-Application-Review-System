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
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class ListEmployees {
	private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private HBox hbox;
	ListView<String> lv = null;
	
	public ListEmployees() {
		
	}
	
	public Scene getListEmployee() throws ClassNotFoundException, SQLException {
		StackPane root = new StackPane();
		root.setPadding(new Insets(100, 100, 100, 100));
		root.setStyle("-fx-background-color: grey;");
		
		hbox = new HBox();
		hbox.setStyle("-fx-background-color: #3287A8;");
		hbox.setMinWidth(screenBounds.getWidth()*0.8);
		hbox.setMaxWidth(screenBounds.getWidth()*0.8);
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);
		
		StackPane.setAlignment(hbox, Pos.CENTER);
		
		hbox.getChildren().addAll(getRight());
		
		root.getChildren().add(getRight());
		Scene scene = new Scene(root, screenBounds.getWidth()-2, screenBounds.getHeight()-80);
		return scene;
	}
	
	
	public VBox getRight() throws ClassNotFoundException, SQLException {
		VBox vb = new VBox();
		vb.setMinWidth(screenBounds.getWidth()/50);
		vb.setMaxWidth(screenBounds.getWidth());
		
		//TOP
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.setMinSize(screenBounds.getWidth(), 100);
		pane.setAlignment(Pos.CENTER);
		
		Text text = new Text("Current List");
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font-size: 50;");
		
		pane.getChildren().add(text);

		vb.getChildren().add(pane);
		
		//CENTER
		HBox attr = new HBox();
		attr.setAlignment(Pos.CENTER_LEFT);
		attr.setSpacing(210);
		attr.setPadding(new Insets(25, 25, 25, 25));
		attr.setStyle("-fx-background-color:  #3e32a8;");
				
		Text empid = new Text("Employee ID");
		empid.setStyle("-fx-font-family: Arial; -fx-font-size: 25;");
		empid.resize(100, 100);
		empid.setFill(Color.WHITE);
		
		Text fname = new Text("First Name");
		fname.setStyle("-fx-font-family: Arial; -fx-font-size: 25;");
		fname.setFill(Color.WHITE);
		
		Text lname = new Text("Last Name");
		lname.setStyle("-fx-font-family: Arial; -fx-font-size: 25;");
		lname.setFill(Color.WHITE);

		Text phone = new Text("Phone");
		phone.setStyle("-fx-font-family: Arial; -fx-font-size: 25;");
		phone.setFill(Color.WHITE);
		
		Text age = new Text("Age");
		age.setStyle("-fx-font-family: Arial; -fx-font-size: 25;");
		age.setFill(Color.WHITE);

		Text salary = new Text("Salary");
		salary.setStyle("-fx-font-family: Arial; -fx-font-size: 25;");
		salary.setFill(Color.WHITE);
		
		attr.getChildren().addAll(empid, fname, lname, phone, age, salary);
		
		vb.getChildren().add(attr);

		lv = new ListView<String>();
		lv.setStyle("-fx-font-family: Arial; -fx-font-size: 25;"); 
		lv.setItems(getList());
	
		vb.getChildren().add(lv);
		return vb;
	}
	
	public ObservableList<String> getList() throws ClassNotFoundException, SQLException{
		EARSdatabase db = new EARSdatabase();
		
		ResultSet rs = db.queryDatabase("SELECT COUNT(*) FROM employees;");
		rs.next();
		int num = Integer.parseInt(rs.getString(1));
		
		String[] array = new String[num];
		
		
		rs = db.queryDatabase("SELECT employee_id, first_name, last_name, phone, age, salary_perhour "
				+ "FROM employees; ");
		
		for(int i = 0; i<array.length; i++) {
			rs.next();
			array[i] = rs.getString(1) + "\t\t\t\t" + rs.getString(2) + "\t\t\t\t" + rs.getString(3) 
			+  "\t\t\t\t" + rs.getString(4) + "\t\t\t\t\t" + rs.getString(5) + "\t\t\t\t\t" + rs.getString(6);
		}
		
		ObservableList<String> list = FXCollections.observableArrayList(array);
		
		db.closeConnection();
		
		return list;
	}
}
