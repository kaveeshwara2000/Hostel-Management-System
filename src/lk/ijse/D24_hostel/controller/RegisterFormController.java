package lk.ijse.D24_hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.D24_hostel.bo.BOFactory;
import lk.ijse.D24_hostel.bo.custom.ReservationBO;
import lk.ijse.D24_hostel.bo.custom.RoomBO;
import lk.ijse.D24_hostel.dto.ReservationDTO;
import lk.ijse.D24_hostel.entity.Room;
import lk.ijse.D24_hostel.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class RegisterFormController {
    public JFXTextField txtRegisterNo;
    public JFXTextField txtRegisterdate;
    public TextField txtContactNo;
    public TextField txtName;
    public ComboBox<String> cmbStudentID;
    public TextField txtDOB;
    public TextField txtAddress;
    public TextField txtGender;
    public TextField txtQuntity;
    public TextField txtRoomType;
    public ComboBox<String> cmbRoomID;
    public TextField txtKeyMoney;
    public Label txtAvilability;
    public JFXButton btnRegister;
    public TextField lblLastStudentId;

    private final RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    private final ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVATION);

    public void initialize(){
        try{
            loadAllStudentIDs();
            loadAllRoomIDs();
            generateNewRegisterId();
            loadDate();
            lastStudentID();
        }catch(Exception e){
            e.printStackTrace();
        }

        cmbStudentID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try{

                setStudentData(newValue);

            }catch (Exception e){
                e.printStackTrace();
            }
        });


        cmbRoomID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue!=null){

                try{

                    setRoomData(newValue);

                }catch (Exception e){
                    e.printStackTrace();
                }

                Room room = null;

                try {

                    room = roomBO.getRoom(newValue);

                } catch (SQLException | ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
                int roomQTY = room.getQty();

                try {

                    List<ReservationDTO> reservationDTOS = reservationBO.reservedRoomByTD(newValue);

                    int registerCount = 0;
                    for (ReservationDTO reservationDTO : reservationDTOS) {
                        registerCount++;
                    }
                    int remaindQuntity = roomQTY-registerCount;

                    if(remaindQuntity == 0){
                        txtAvilability.setText("UNAVEILABLE");
                    }else{
                        txtAvilability.setText("AVEILABLE");
                    }
                } catch (SQLException | ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void lastStudentID() throws SQLException, IOException, ClassNotFoundException {
        lblLastStudentId.setText(reservationBO.lastStudentID());
    }

    private void loadDate() {
        txtRegisterdate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    private void generateNewRegisterId() {
        try{
            txtRegisterNo.setText(reservationBO.generateNewRegisterId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setRoomData(String id) throws SQLException, ClassNotFoundException, IOException {
        Room room = reservationBO.setRoomsData(id);
        txtRoomType.setText(room.getType());
        txtKeyMoney.setText(String.valueOf(room.getKeyMoney()));
        txtQuntity.setText(String.valueOf(room.getQty()));
    }

    private void setStudentData(String id) throws SQLException, ClassNotFoundException, IOException {
        Student student = reservationBO.setStudentsData(id);
        txtName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtContactNo.setText(student.getContactNo());
        txtDOB.setText(String.valueOf(student.getDob()));
        txtGender.setText(student.getGender());

    }

    private void loadAllRoomIDs() throws SQLException, IOException, ClassNotFoundException {
        cmbRoomID.getItems().addAll(reservationBO.setRoomIDs());
    }

    private void loadAllStudentIDs() throws SQLException, IOException, ClassNotFoundException {
        cmbStudentID.getItems().addAll(reservationBO.setStudentIDs());
    }

    public void RegisterOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        if(reservationBO.registerStudent(new ReservationDTO(
                txtRegisterNo.getText(),
                LocalDate.parse(txtRegisterdate.getText()),
                cmbStudentID.getValue(),
                cmbRoomID.getValue(),
                txtAvilability.getText()
        ))){
            new Alert(Alert.AlertType.CONFIRMATION,"Data Added Succussfully...!!!").showAndWait();

        }else{
            new Alert(Alert.AlertType.WARNING,"Data Not Added Succussfully...!!!").showAndWait();
        }
        generateNewRegisterId();
        clearText();
    }

    private void clearText() {
        cmbRoomID.setValue(null);
        cmbStudentID.setValue(null);
        txtName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        txtDOB.clear();
        txtGender.clear();
        txtRoomType.clear();
        txtKeyMoney.clear();
        txtQuntity.clear();
        txtRegisterNo.getText();
        txtAvilability.setText("");
    }
}
