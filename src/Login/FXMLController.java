/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Login;


//import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import java.sql.ResultSet;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FXMLController implements Initializable {
    
    
    @FXML
    private Pane main_area;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    
    
    
    @FXML
    private void closeApp(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }
    @FXML
    private void createAccount(MouseEvent event) throws IOException{
        Parent fxml =FXMLLoader.load(getClass().getResource("/createaccount/createaccFXML.fxml"));
        main_area.getChildren().addAll(fxml);
    }
    @FXML
     private void forgotPassword(MouseEvent event) throws IOException{
        Parent fxml =FXMLLoader.load(getClass().getResource("/forgotpassword/fpassFXML.fxml"));
        main_area.getChildren().addAll(fxml);
    }
    public void loginAccount(MouseEvent event){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdcsmartcity","root","");
           String sql = "SELECT * FROM cityuserdata WHERE Email=? and Password=?";
           ps =con.prepareStatement(sql);
           
           ps.setString(1, email.getText());
           ps.setString(2, password.getText());
           
           
           rs = ps.executeQuery();
           if(rs.next()){
               Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Login Sucessful");
            a.setHeaderText("Account Login Sucessfully");
            a.setContentText("Design your dashborad and design it");
            a.showAndWait(); 
           }
           else{
                Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Login Fail");
            a.setContentText("Your Account no.  or Pin is incorrect . Try Again !!!");
            a.showAndWait();
           }
           
           
        }catch(Exception e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Error is login account");
            a.setContentText("Your account is not login.There is some technical issue"+ e.getMessage());
            a.showAndWait();
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     
       
    }    
   
    
    
}
