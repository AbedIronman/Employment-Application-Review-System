import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
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
import java.io.File;
import javafx.scene.image.*;

public class Main extends Application{
	
	private static Stage ps = null;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage ps) {
		this.ps = ps;
		
		LoginWindow login = new LoginWindow();
		Scene scene = login.getLoginWindow();
		ps.setScene(scene);
		//loadWelcomeWindow();
		ps.show();
	}
	
	public static void loadSignUpWindow() {
		SignUpWindow window = new SignUpWindow();
		ps.setScene(window.getSignUpWindow());
	}
	
	public static void loadLoginWindow() {
		LoginWindow window = new LoginWindow();
		ps.setScene(window.getLoginWindow());
	}
	
	public static void loadWelcomeWindowD(String t) {
		WelcomeWindowDash window = new WelcomeWindowDash(t);
		ps.setScene(window.getWelcomeScreen());
	}

	public static void loadWelcomeWindow() {
		WelcomeWindowDash window = new WelcomeWindowDash();
		ps.setScene(window.getWelcomeScreen());
	}

	public static void loadWelcomeWindowE(String t) {
		WelcomeWindowEmp window = new WelcomeWindowEmp(t);
		ps.setScene(window.getWelcomeScreen());
	}

	public static void loadWelcomeWindowJ(String t) {
		WelcomeWindowJob window = new WelcomeWindowJob(t);
		ps.setScene(window.getWelcomeScreen());
	}

	public static void loadWelcomeWindowA(String t) {
		WelcomeWindowApp window = new WelcomeWindowApp(t);
		ps.setScene(window.getWelcomeScreen());
	}
		
	public static void loadRecoveryWindow() {
		PasswordRecovery window = new PasswordRecovery();
		ps.setScene(window.getPasswordRecovery());
	}
	
	public static void loadAddEmployees() {
		AddEmployees window = new AddEmployees();
		try {
			ps.setScene(window.getAddEmployee());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadEmployeeReport() {
		EmployeeReport window = new EmployeeReport();
		try {
			ps.setScene(window.getEmployeeReport());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadDeleteEmployees() {
		DeleteEmployee window = new DeleteEmployee();
		try {
			ps.setScene(window.getDeleteEmployee());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void loadUpdateEmployees() {
		UpdateEmployee window = new UpdateEmployee();
		try {
			ps.setScene(window.getUpdateEmployee());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void loadApplicantReport() {
		ApplicantReport window = new ApplicantReport();
		try {
			ps.setScene(window.getApplicantReport());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadAddApplicant() {
		AddApplicant window = new AddApplicant();
		try {
			ps.setScene(window.getAddApplicant());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadDeleteApplicant() {
		DeleteApplicant window = new DeleteApplicant();
		try {
			ps.setScene(window.getDeleteApplicant());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void loadApplicantInformation() {
		ApplicantInformation window = new ApplicantInformation();
		try {
			ps.setScene(window.getApplicantInformation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
