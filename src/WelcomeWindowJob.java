import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;

public class WelcomeWindowJob {
	private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private BorderPane borderPane = null;
	private ListView<String> lv = null;
	String s;

	public WelcomeWindowJob() {
	};

	public WelcomeWindowJob(String s) {
		this.s = s;
	};

	public Scene getWelcomeScreen() {

		BorderPane borderPane = new BorderPane();

		borderPane.setLeft(getLeft());
		borderPane.setTop(getTop());
		try {
			borderPane.setCenter(getRight());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(borderPane, screenBounds.getWidth() - 2, screenBounds.getHeight() - 80);
		return scene;
	}

	private GridPane getCenter() {
		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(30);
		grid.setPadding(new Insets(50, 50, 50, 50));
		grid.setAlignment(Pos.CENTER);

		Text empSection = new Text("This is the Job opening screen");
		empSection.setStyle("-fx-font-family: Arial; -fx-font-size: 20");
		grid.setStyle("-fx-background-color: grey");

		HBox emp = new HBox();
		emp.setAlignment(Pos.CENTER_LEFT);
		emp.getChildren().addAll(empSection);
		grid.add(emp, 0, 0);

		return grid;
	}

	public HBox getRight() throws ClassNotFoundException, SQLException {
		HBox main = new HBox();
		main.setStyle("-fx-background-color: grey");
		main.setAlignment(Pos.CENTER);
		VBox vb = new VBox();
		vb.setStyle("-fx-background-color: grey");
		vb.setMinWidth(screenBounds.getWidth() / 3);
		vb.setMaxWidth(screenBounds.getWidth() / 3);

		// TOP
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.setMinSize(screenBounds.getWidth() / 3, 100);
		pane.setAlignment(Pos.CENTER);

		Text text = new Text("Job Openings");
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font-size: 50;");

		pane.getChildren().add(text);

		vb.getChildren().add(pane);

		// CENTER
		HBox attr = new HBox();
		attr.setAlignment(Pos.CENTER);
		attr.setSpacing(55);
		attr.setPadding(new Insets(25, 25, 25, 25));
		attr.setStyle("-fx-background-color:  #3e32a8;");

		Text empid = new Text("Job ID");
		empid.setStyle("-fx-font-family: Arial; -fx-font-size: 15;");
		empid.maxWidth(vb.getMaxWidth() / 5);
		empid.setFill(Color.WHITE);

		Text fname = new Text("Position");
		fname.setStyle("-fx-font-family: Arial; -fx-font-size: 15;");
		fname.maxWidth(vb.getMaxWidth() / 5);
		fname.setFill(Color.WHITE);

		Text lname = new Text("Start Date");
		lname.setStyle("-fx-font-family: Arial; -fx-font-size: 15;");
		lname.maxWidth(vb.getMaxWidth() / 5);
		lname.setFill(Color.WHITE);

		Text phone = new Text("Salary(Per Hour)");
		phone.setStyle("-fx-font-family: Arial; -fx-font-size: 15;");
		phone.maxWidth(vb.getMaxWidth() / 5);
		phone.setFill(Color.WHITE);

		Text age = new Text("Department");
		age.setStyle("-fx-font-family: Arial; -fx-font-size: 15;");
		age.maxWidth(vb.getMaxWidth() / 5);
		age.setFill(Color.WHITE);

		attr.getChildren().addAll(empid, fname, lname, phone, age);

		vb.getChildren().add(attr);

		lv = new ListView<String>();
		lv.setStyle("-fx-font-family: Arial;");
		lv.setItems(getList());

		vb.getChildren().add(lv);
		HBox.setMargin(vb, new Insets(25, 25, 25, 25));
		main.getChildren().add(vb);
		return main;

	}

	public ObservableList<String> getList() throws ClassNotFoundException, SQLException {
		EARSdatabase db = new EARSdatabase();

		ResultSet rs = db.queryDatabase("SELECT COUNT(*) FROM jobs;");
		rs.next();
		int num = Integer.parseInt(rs.getString(1));

		String[] array = new String[num];

		rs = db.queryDatabase("SELECT * " + "FROM jobs; ");

		for (int i = 0; i < array.length; i++) {
			rs.next();

			array[i] = String.format("%15s %35s %27s %25s %30s", rs.getString(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5));

		}

		ObservableList<String> list = FXCollections.observableArrayList(array);

		db.closeConnection();

		return list;
	}

	private Text getTop() {
		// Setting the header
		Text header = new Text("Welcome to Employee Application Review System");
		header.setStyle("-fx-font-family: Arial; -fx-font-size: 100; -fx-font-weight: bold;");
		header.setWrappingWidth(screenBounds.getWidth() - 2);
		BorderPane.setMargin(header, new Insets(25, 25, 25, 25));
		return header;
	}

	private VBox getLeft() {
		// Creating the option panel
		VBox optionPane = new VBox();
		optionPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: #3287A8;");
		optionPane.setMinWidth(screenBounds.getWidth() / 4);
		System.out.println();

		Image profileImage = null;
		try {
			profileImage = new Image(new FileInputStream(new File("icons//User_icon.png")));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		ImageView imageView = new ImageView(profileImage);
		imageView.setScaleY(2);
		imageView.setScaleX(2);

		optionPane.getChildren().add(imageView);
		optionPane.setAlignment(Pos.TOP_CENTER);
		optionPane.setSpacing(25);
		VBox.setMargin(imageView, new Insets(40, 75, 10, 75));

		Text name = new Text(s.toUpperCase());
		optionPane.getChildren().add(name);
		name.setStyle("-fx-font-size: 20; -fx-fill: white; -fx-font-weight: bold;");

		// options
		VBox options = new VBox();
		options.setStyle("-fx-background-color: black;");
		options.setAlignment(Pos.CENTER);

		Button dashboardButton = new Button("Dashboard");
		Image dashboardImage = null;
		try {
			dashboardImage = new Image(new FileInputStream("icons//Laptop_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		dashboardButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				optionPane.setCursor(Cursor.HAND);
			}
		});

		dashboardButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				optionPane.setCursor(Cursor.DEFAULT);
			}
		});

		dashboardButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadWelcomeWindowD(s);
			}
		});

		dashboardButton.setStyle("-fx-background-color: black;");
		dashboardButton.setTextFill(Color.WHITE);
		dashboardButton.setGraphic(new ImageView(dashboardImage));
		VBox.setMargin(dashboardButton, new Insets(10, 0, 10, 0));
		options.getChildren().add(dashboardButton);

		Button employeeButton = new Button("Employee");
		Image employeeImage = null;
		try {
			employeeImage = new Image(new FileInputStream("icons//Employee_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		employeeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				optionPane.setCursor(Cursor.HAND);
			}
		});

		employeeButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				optionPane.setCursor(Cursor.DEFAULT);
			}
		});

		employeeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadWelcomeWindowE(s);
			}
		});
		employeeButton.setStyle("-fx-background-color: black;");
		employeeButton.setTextFill(Color.WHITE);
		employeeButton.setGraphic(new ImageView(employeeImage));
		VBox.setMargin(employeeButton, new Insets(10, 0, 10, 0));
		options.getChildren().add(employeeButton);

		Button applicantsButton = new Button("Applicant");
		Image salaryImage = null;
		try {
			salaryImage = new Image(new FileInputStream("icons//Employee_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		applicantsButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				optionPane.setCursor(Cursor.HAND);
			}
		});

		applicantsButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				optionPane.setCursor(Cursor.DEFAULT);
			}
		});

		applicantsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadWelcomeWindowA(s);
			}
		});
		applicantsButton.setStyle("-fx-background-color: black;");
		applicantsButton.setTextFill(Color.WHITE);
		applicantsButton.setGraphic(new ImageView(salaryImage));
		VBox.setMargin(applicantsButton, new Insets(10, 0, 10, 0));
		options.getChildren().add(applicantsButton);

		Button jobButton = new Button("Job Openings");
		Image leaveImage = null;
		try {
			leaveImage = new Image(new FileInputStream("icons//Leave_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		jobButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				optionPane.setCursor(Cursor.HAND);
			}
		});

		jobButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				optionPane.setCursor(Cursor.DEFAULT);
			}
		});
		jobButton.setStyle("-fx-background-color: black;");
		jobButton.setTextFill(Color.WHITE);
		jobButton.setGraphic(new ImageView(leaveImage));
		VBox.setMargin(jobButton, new Insets(10, 0, 10, 0));
		options.getChildren().add(jobButton);

		optionPane.getChildren().add(options);

		// logout option
		Button logoutButton = new Button("Log Out");
		logoutButton.setTextFill(Color.WHITE);
		logoutButton.setMinSize(optionPane.getMinWidth() / 3, 50);
		logoutButton.setStyle("-fx-background-color: black; -fx-border-width: 5; -fx-border-color: white;");
		optionPane.getChildren().add(logoutButton);

		logoutButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				optionPane.setCursor(Cursor.HAND);
			}
		});

		logoutButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				optionPane.setCursor(Cursor.DEFAULT);
			}
		});

		logoutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadLoginWindow();
			}
		});

		VBox.setMargin(logoutButton, new Insets(25, 0, 75, 0));
		return optionPane;
	};

}
