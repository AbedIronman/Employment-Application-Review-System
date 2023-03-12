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

public class WelcomeWindowApp {
	private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private BorderPane borderPane = null;
	String s = "";

	public WelcomeWindowApp() {
	};

	public WelcomeWindowApp(String s) {
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
		Image iImage = null;

		// Employee Section

		HBox emp = new HBox();

		try {
			iImage = new Image(new FileInputStream("icons//Identicator_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		Image addImage = null;
		try {
			addImage = new Image(new FileInputStream("icons//Add_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		Image deleteImage = null;
		try {
			deleteImage = new Image(new FileInputStream("icons//Delete_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		// Application Section

		HBox app = new HBox();

		Button infoBtn2 = new Button();
		Tooltip tp2 = new Tooltip("Section contains applicant related information");
		infoBtn2.setGraphic(new ImageView(iImage));
		infoBtn2.setStyle("-fx-background-color: grey;");
		infoBtn2.setTooltip(tp2);
		
		infoBtn2.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				app.setCursor(Cursor.HAND);
			}
		});

		infoBtn2.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				app.setCursor(Cursor.DEFAULT);
			}
		});
		
		Text appSection = new Text("Application Section");
		appSection.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");

		app.setAlignment(Pos.CENTER_LEFT);
		app.getChildren().addAll(infoBtn2, appSection);
		grid.add(app, 0, 0);

		Button listBtn = new Button("Applicant Report");
		Image listImage = null;
		try {
			listImage = new Image(new FileInputStream("icons//Salary_Receipt_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		listBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.HAND);
			}
		});

		listBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.DEFAULT);
			}
		});

		listBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadApplicantReport();
			}
		});
		
		listBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		listBtn.setTextFill(Color.WHITE);
		listBtn.setGraphic(new ImageView(listImage));
		listBtn.setMinSize(200, 200);
		grid.add(listBtn, 0, 1);

		Button addAppBtn = new Button("Add Applicant");
		addAppBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		addAppBtn.setTextFill(Color.WHITE);
		addAppBtn.setGraphic(new ImageView(addImage));
		addAppBtn.setMinSize(200, 200);
		grid.add(addAppBtn, 1, 1);

		addAppBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.HAND);
			}
		});

		addAppBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.DEFAULT);
			}
		});
		
		addAppBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadAddApplicant();
			}
		});

		Button deleteAppBtn = new Button("Delete Applicant");
		deleteAppBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		deleteAppBtn.setTextFill(Color.WHITE);
		deleteAppBtn.setGraphic(new ImageView(deleteImage));
		deleteAppBtn.setMinSize(200, 200);
		grid.add(deleteAppBtn, 0, 2);

		deleteAppBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.HAND);
			}
		});

		deleteAppBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.DEFAULT);
			}
		});
		
		deleteAppBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadDeleteApplicant();
			}
		});

		Button appInfoBtn = new Button("Applicant Information");
		Image infoImage = null;
		try {
			infoImage = new Image(new FileInputStream("icons//Update_Salary_Icon.png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		appInfoBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.HAND);
			}
		});

		appInfoBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				grid.setCursor(Cursor.DEFAULT);
			}
		});

		appInfoBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadApplicantInformation();
			}
		});

		appInfoBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		appInfoBtn.setTextFill(Color.WHITE);
		appInfoBtn.setGraphic(new ImageView(infoImage));
		appInfoBtn.setMinSize(200, 200);
		grid.add(appInfoBtn, 1, 2);

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
				borderPane.setCenter(getCenterHome());
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
				borderPane.setCenter(getCenterHome());
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
