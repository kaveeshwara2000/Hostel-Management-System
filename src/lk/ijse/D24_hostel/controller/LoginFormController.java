package lk.ijse.D24_hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.D24_hostel.bo.BOFactory;
import lk.ijse.D24_hostel.bo.custom.UserBO;
import lk.ijse.D24_hostel.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXButton btnLogIn;
    public JFXPasswordField pwdPassword;
    public AnchorPane loginContext;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void signInOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        List<UserDTO> allUser = userBO.loadAllUsers();

        for (UserDTO userDTO : allUser) {

            if(userDTO.getUsername().equals(txtUserName.getText()) && userDTO.getPassword().equals(pwdPassword.getText())){

                Stage stage=(Stage) loginContext.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoard.fxml"))));
                stage.centerOnScreen();

            }else{
                new Alert(Alert.AlertType.ERROR,"Invalid Login...!").show();
            }
        }
    }
}
