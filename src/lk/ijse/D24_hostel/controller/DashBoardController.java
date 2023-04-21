package lk.ijse.D24_hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.D24_hostel.bo.BOFactory;
import lk.ijse.D24_hostel.bo.custom.ReservationBO;
import lk.ijse.D24_hostel.bo.custom.RoomBO;
import lk.ijse.D24_hostel.bo.custom.StudentBO;
import lk.ijse.D24_hostel.bo.custom.UserBO;
import lk.ijse.D24_hostel.dto.ReservationDTO;
import lk.ijse.D24_hostel.dto.StudentDTO;
import lk.ijse.D24_hostel.dto.UserDTO;
import lk.ijse.D24_hostel.entity.Room;
import lk.ijse.D24_hostel.util.NavigationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class DashBoardController implements NavigationUtil {

    public AnchorPane AdminDashBoardContext;
    public Label lblStudentCount;
    public Label lblRoomAvilableCount1;
    public Label lblRoomReseivedCount1;
    public Label lblDay;
    public Label lblDate;
    public Label lblTime;
    public Button btnDashboard;
    public Button btnStudent;
    public Button btnRoom;
    public Button btnRegister;
    public Button btnReport1;
    public AnchorPane loardFormContext;
    public Label lblAllRooms;
    public JFXComboBox<String> cmbRoomIds;
    public JFXTextField txtPassword;
    public JFXTextField txtUserName;
    public JFXButton btnUpdate;

    private final RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    private final ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVATION);
    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);


    public void initialize() {
        btnUpdate.setDisable(true);

        try {
            loadAllDashLabels();
            DateTime();
            setRoomIDs();
            setUserNamePassword();
            btnUpdate.setDisable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        cmbRoomIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue!=null){
                Room room = null;

                try {

                    room = roomBO.getRoom(newValue);

                } catch (SQLException | ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
                int roomQTY = room.getQty();
                lblAllRooms.setText(String.valueOf(roomQTY));

                try {

                    List<ReservationDTO> reservationDTOS = reservationBO.reservedRoomByTD(newValue);

                    int registerCount = 0;
                    for (ReservationDTO reservationDTO : reservationDTOS) {
                        registerCount++;
                    }
                    int remaindQuntity = roomQTY-registerCount;
                    lblRoomReseivedCount1.setText(String.valueOf(registerCount));
                    lblRoomAvilableCount1.setText(String.valueOf(remaindQuntity));

                } catch (SQLException | ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setUserNamePassword() throws SQLException, IOException, ClassNotFoundException {
        List<UserDTO> userDTOS = userBO.loadAllUsers();
        for (UserDTO userDTO : userDTOS) {
            txtUserName.setText(userDTO.getUsername());
            txtPassword.setText(userDTO.getPassword());
        }
    }

    private void setRoomIDs() throws SQLException, IOException, ClassNotFoundException {
        cmbRoomIds.getItems().addAll(reservationBO.setRoomIDs());
    }

    private void loadAllDashLabels() throws SQLException, IOException, ClassNotFoundException {
        int studentCount = 0;
        for (StudentDTO loadAllStudent : studentBO.loadAllStudents()) {
            studentCount++;

        }
        lblStudentCount.setText(String.valueOf(studentCount));
    }

    private void DateTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();

            lblDay.setText(LocalDate.now().getDayOfWeek().toString());
            lblDate.setText(currentDate.getYear()+"-"+ currentDate.getMonthValue()
                    +"-"+ currentDate.getDayOfMonth());

            lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":"+ currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );

        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void LogoutOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure...?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            Stage stage = (Stage) AdminDashBoardContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("lk/ijse/D24_hostel/view/LoginForm.fxml"))));
            stage.centerOnScreen();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        userBO.update(new UserDTO(
                "UI-001",txtUserName.getText(),txtPassword.getText()

        ));
    }

    public void DashboardOnAction(ActionEvent actionEvent) throws IOException {
        CloseWindowUi(AdminDashBoardContext);
        Parent parent = FXMLLoader.load(getClass().getResource("lk/ijse/D24_hostel/view/DashBoard.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    public void StudentOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ManageStudentForm");
    }

    public void RoomOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ManageRoomForm");
    }

    public void RegisterOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("RegisterForm");
    }

    public void ReportOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AllDetails");
    }

    @Override
    public void loadUi(String location) throws IOException {
        loardFormContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("lk/ijse/D24_hostel/view/"+location+".fxml"));
        loardFormContext.getChildren().add(parent);
    }

    @Override
    public void CloseWindowUi(AnchorPane a) throws IOException {
        Stage stage= (Stage)a.getScene().getWindow();
        stage.close();
    }


}
