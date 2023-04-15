package lk.ijse.D24_hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.D24_hostel.bo.BOFactory;
import lk.ijse.D24_hostel.bo.custom.StudentBO;
import lk.ijse.D24_hostel.dto.StudentDTO;
import lk.ijse.D24_hostel.util.ValidationUtil;
import lk.ijse.D24_hostel.view.tm.StudentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ManageStudentFormController {
    public TableView<StudentDTO> tblAllStudents;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDOB;
    public TableColumn colGender;
    public TextField txtSearchId;
    public JFXTextField txtAddress;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXComboBox cmbGender;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnAdd;
    public JFXDatePicker txtBirth;

    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);


    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    
    public void initialize(){
        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        try{
            loadAllStudents();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        cmbLoard();

        tblAllStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                setData(newValue);
            }
        });


        Pattern idPattern = Pattern.compile("^(SI-)[0-9]{3,5}$");
        Pattern NamePattern = Pattern.compile("^[A-z]{3,20}$");
        Pattern AddressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern contact = Pattern.compile("^(0)(71|77|76|70|75|78|91)-[0-9]{7}$");


        map.put(txtId, idPattern);
        map.put(txtName, NamePattern);
        map.put(txtAddress, AddressPattern);
        map.put(txtContact, contact);
        
    }

    private void cmbLoard() {
        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("Female");
    }

    private void setData(StudentDTO s) {
        txtId.setText(s.getStudentId());
        txtName.setText(s.getName());
        txtAddress.setText(s.getAddress());
        txtContact.setText(s.getContactNo());
        txtBirth.setValue(s.getDob());
        cmbGender.setValue(s.getGender());

        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        btnAdd.setDisable(true);
    }

    private void loadAllStudents() {
        try{
            List<StudentDTO> allStudents = studentBO.loadAllStudents();
            ObservableList<StudentDTO> obList = FXCollections.observableArrayList();
            for (StudentDTO student : allStudents) {
                obList.add(new StudentTM(
                        student.getStudentId(),
                        student.getName(),
                        student.getAddress(),
                        student.getContactNo(),
                        student.getDob(),
                        student.getGender()
                ));
            }

            tblAllStudents.setItems(obList);

            FilteredList<StudentDTO> filterData = new FilteredList(obList, b -> true);

            txtSearchId.textProperty().addListener((observable, oldValue, newValue) -> {
                filterData.setPredicate(StudentDTO -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (StudentDTO.getStudentId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    else
                        return false;
                });
            });

            SortedList<StudentDTO> sortedData = new SortedList<>(filterData);
            sortedData.comparatorProperty().bind(tblAllStudents.comparatorProperty());
            tblAllStudents.setItems(sortedData);

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearText();
    }

    private void clearText() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtBirth.setValue(null);
        cmbGender.getSelectionModel().clearSelection();
        tblAllStudents.refresh();
    }

    public void updateOnAction(ActionEvent actionEvent) {
        try{
            if (studentBO.update(new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText(),
                    txtContact.getText(), LocalDate.parse(txtBirth.getValue().toString()), cmbGender.getValue().toString()))) {

                new Alert(Alert.AlertType.CONFIRMATION,"Data Updated Succussfully...!!!").showAndWait();

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        loadAllStudents();
        clearText();
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = tblAllStudents.getSelectionModel().getSelectedItem().getStudentId();

        try {
            studentBO.delete(id);
            new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure...?").showAndWait();
            tblAllStudents.getItems().remove(tblAllStudents.getSelectionModel().getSelectedItem());
            tblAllStudents.getSelectionModel().clearSelection();



        }catch (Exception e) {
            e.printStackTrace();
        }
        loadAllStudents();
    }

    public void addOnAction(ActionEvent actionEvent) {
        try{
            if (studentBO.add(new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText(),
                    txtContact.getText(), LocalDate.parse(txtBirth.getValue().toString()), cmbGender.getValue().toString()))) {

                new Alert(Alert.AlertType.CONFIRMATION,"Data Added Succussfully...!!!").showAndWait();

            }

        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Failed to save the Student!!! " + e.getMessage()).showAndWait();
            e.printStackTrace();
        }

        loadAllStudents();
        clearText();
    }


    public void TextFieldsReleased(KeyEvent keyEvent) {
        ValidationUtil.validate(map, btnAdd);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = ValidationUtil.validate(map, btnAdd);

            if (response instanceof JFXTextField) {
                JFXTextField textField = (JFXTextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }
}
