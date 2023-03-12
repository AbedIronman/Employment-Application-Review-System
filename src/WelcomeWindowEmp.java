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
import java.io.File;

public class WelcomeWindowEmp {
	private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private BorderPane borderPane = null;
	String s = "";

	public WelcomeWindowEmp() {
	};

	public WelcomeWindowEmp(String s) {
		this.s = s;
	};

	public Scene getWelcomeScreen() {

		BorderPane borderPane = new BorderPane();

		borderPane.setLeft(getLeft());
		borderPane.setTop(getTop());
		borderPane.setCenter(getCenter());		
		
		Scene scene = new Scene(borderPane, screenBounds.getWidth() - 2, screenBounds.getHeight() - 80);
		return scene;
	}

	private GridPane getCenterHome() {
		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(30);
		grid.setPadding(new Insets(50, 50, 50, 50));
		grid.setAlignment(Pos.CENTER);

		Text empSection = new Text("This is the home screen! Please click any of the buttons on the side to view the pages");
		empSection.setStyle("-fx-font-family: Arial; -fx-font-size: 20");
		grid.setStyle("-fx-background-color: grey");

		HBox emp = new HBox();
		emp.setAlignment(Pos.CENTER_LEFT);
		emp.getChildren().addAll(empSection);
		grid.add(emp, 0, 0);
		
		return grid;
	}

	private GridPane getCenter() {
		GridPane grid = new GridPane();
		grid.setHgap(100);
		grid.setVgap(70);
		grid.setPadding(new Insets(100, 100, 100, 100));
		grid.setAlignment(Pos.TOP_CENTER);

		// Employee Section

		HBox emp = new HBox();

		Image iImage = null;
		try {
			iImage = new Image(new FileInputStream("icons//Identicator_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		Button infoBtn1 = new Button();
		Tooltip tp1 = new Tooltip("Section contains employee related information");
		infoBtn1.setGraphic(new ImageView(iImage));
		infoBtn1.setStyle("-fx-background-color: grey;");
		infoBtn1.setTooltip(tp1);

		infoBtn1.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				emp.setCursor(Cursor.HAND);
			}
		});

		infoBtn1.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				emp.setCursor(Cursor.DEFAULT);
			}
		});

		Text empSection = new Text("Employee Section");
		empSection.setStyle("-fx-font-family: Arial; -fx-font-size: 20");

		emp.setAlignment(Pos.CENTER_LEFT);
		emp.getChildren().addAll(infoBtn1, empSection);
		grid.add(emp, 0, 0);

		Button addBtn = new Button("Add Employee");
		Image addImage = null;
		try {
			addImage = new Image(new FileInputStream("icons//Add_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		addBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.HAND);
			}
		});

		addBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.DEFAULT);
			}
		});
		
		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadAddEmployees();
			}
		});
		
		addBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		addBtn.setTextFill(Color.WHITE);
		addBtn.setGraphic(new ImageView(addImage));
		addBtn.setMinSize(200, 200);
		grid.add(addBtn, 0, 1);

		Button updateBtn = new Button("Update Employee");
		Image updateImage = null;
		try {
			updateImage = new Image(new FileInputStream("icons//Update_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		updateBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.HAND);
			}
		});

		updateBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.DEFAULT);
			}
		});

		updateBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadUpdateEmployees();
			}
		});
		
		updateBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		updateBtn.setTextFill(Color.WHITE);
		updateBtn.setGraphic(new ImageView(updateImage));
		updateBtn.setMinSize(200, 200);
		grid.add(updateBtn, 1, 1);

		Button deleteBtn = new Button("Delete Employee");
		Image deleteImage = null;
		try {
			deleteImage = new Image(new FileInputStream("icons//Delete_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		deleteBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.HAND);
			}
		});

		deleteBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.DEFAULT);
			}
		});

		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadDeleteEmployees();
			}
		});
		
		deleteBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		deleteBtn.setTextFill(Color.WHITE);
		deleteBtn.setGraphic(new ImageView(deleteImage));
		deleteBtn.setMinSize(200, 200);
		grid.add(deleteBtn, 0, 2);

		Button reportBtn = new Button("Employee Report");
		Image reportImage = null;
		try {
			reportImage = new Image(new FileInputStream("icons//Report_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		reportBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.HAND);
			}
		});

		reportBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.DEFAULT);
			}
		});

		reportBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadEmployeeReport();
			}
		});
		

		reportBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		reportBtn.setTextFill(Color.WHITE);
		reportBtn.setGraphic(new ImageView(reportImage));
		reportBtn.setMinSize(200, 200);
		grid.add(reportBtn, 1, 2);


		grid.setStyle("-fx-background-color: grey");

		return grid;
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

		Button applicantButton = new Button("Applicant");
		Image salaryImage = null;
		try {
			salaryImage = new Image(new FileInputStream("icons//Employee_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		applicantButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				optionPane.setCursor(Cursor.HAND);
			}
		});

		applicantButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				optionPane.setCursor(Cursor.DEFAULT);
			}
		});

		applicantButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadWelcomeWindowA(s);
			}
		});
		applicantButton.setStyle("-fx-background-color: black;");
		applicantButton.setTextFill(Color.WHITE);
		applicantButton.setGraphic(new ImageView(salaryImage));
		VBox.setMargin(applicantButton, new Insets(10, 0, 10, 0));
		options.getChildren().add(applicantButton);

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

		jobButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadWelcomeWindowJ(s);
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
