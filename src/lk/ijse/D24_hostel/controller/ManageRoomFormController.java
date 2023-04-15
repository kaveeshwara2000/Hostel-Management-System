package lk.ijse.D24_hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.D24_hostel.bo.custom.RoomBO;
import lk.ijse.D24_hostel.dto.RoomDTO;
import lk.ijse.D24_hostel.util.ValidationUtil;
import lk.ijse.D24_hostel.view.tm.RoomTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ManageRoomFormController {
    public TableView<RoomDTO> tblAllRooms;
    public TableColumn colId;
    public TableColumn colRoomType;
    public TableColumn colKeyMoney;
    public TableColumn colQTY;
    public TextField txtSearchId;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtId;
    public JFXTextField txtQTY;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnAdd;
    public JFXComboBox cmbRoomType;

    private final RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    public void initialize(){
        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        colId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));

        try{
            loadAllRoom();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        cmbLoard();

        tblAllRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setaData(newValue);
            }
        });

        Pattern idPattern = Pattern.compile("^(RI-)[0-9]{3,5}$");
        Pattern keyMoneyPattern = Pattern.compile("^[0-9]{1,}(.00)$");
        Pattern qtyPattern = Pattern.compile("^[0-9]{1,}$");

        map.put(txtId, idPattern);
        map.put(txtKeyMoney, keyMoneyPattern);
        map.put(txtQTY, qtyPattern);
    }

    private void loadAllRoom() {
        try{
            List<RoomDTO> allRooms = roomBO.loadAllRooms();
            ObservableList<RoomDTO> obList = FXCollections.observableArrayList();
            for (RoomDTO room : allRooms) {
                obList.add(new RoomTM(
                       room.getRoomId(),
                        room.getType(),
                        room.getKeyMoney(),
                        room.getQty()
                ));
            }

            tblAllRooms.setItems(obList);

            FilteredList<RoomDTO> filterData = new FilteredList(obList, b -> true);

            txtSearchId.textProperty().addListener((observable, oldValue, newValue) -> {
                filterData.setPredicate(RoomDTO -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (RoomDTO.getRoomId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    else
                        return false;
                });
            });

            SortedList<RoomDTO> sortedData = new SortedList<>(filterData);
            sortedData.comparatorProperty().bind(tblAllRooms.comparatorProperty());
            tblAllRooms.setItems(sortedData);

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void cmbLoard() {
        cmbRoomType.getItems().add("AC");
        cmbRoomType.getItems().add("NON-AC");
        cmbRoomType.getItems().add("AC/Food");
        cmbRoomType.getItems().add("NON-AC/Food");
    }

    private void setaData(RoomDTO r) {
        txtId.setText(r.getRoomId());
        cmbRoomType.setValue(r.getType());
        txtKeyMoney.setText(String.valueOf(r.getKeyMoney()));
        txtQTY.setText(String.valueOf(r.getQty()));

        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        btnAdd.setDisable(true);
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearText();
    }

    private void clearText() {
        txtId.clear();
        cmbRoomType.getSelectionModel().clearSelection();
        txtKeyMoney.clear();
        txtQTY.clear();
        tblAllRooms.refresh();
    }

    public void updateOnAction(ActionEvent actionEvent) {
        try{
            if (roomBO.update(new RoomDTO(
                    txtId.getText(), cmbRoomType.getValue().toString(), Double.parseDouble(txtKeyMoney.getText()), Integer.parseInt(txtQTY.getText())))) {

                new Alert(Alert.AlertType.CONFIRMATION,"Data Updated Succussfully...!!!").showAndWait();

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        loadAllRoom();
        clearText();
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = tblAllRooms.getSelectionModel().getSelectedItem().getRoomId();

        try {
            roomBO.delete(id);
            tblAllRooms.getItems().remove(tblAllRooms.getSelectionModel().getSelectedItem());
            tblAllRooms.getSelectionModel().clearSelection();

            new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure...?").showAndWait();

        }catch (Exception e) {
            e.printStackTrace();
        }
        loadAllRoom();
    }

    public void addOnAction(ActionEvent actionEvent) {
        try{
            if (roomBO.add(new RoomDTO(
                    txtId.getText(), cmbRoomType.getValue().toString(), Double.parseDouble(txtKeyMoney.getText()), Integer.parseInt(txtQTY.getText())))) {

                new Alert(Alert.AlertType.CONFIRMATION,"Data Added Succussfully...!!!").showAndWait();

            }

        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Failed to save the Student!!!" + e.getMessage()).showAndWait();
            e.printStackTrace();
        }

        loadAllRoom();
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
