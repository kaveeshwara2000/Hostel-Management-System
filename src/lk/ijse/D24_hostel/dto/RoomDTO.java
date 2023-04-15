package lk.ijse.D24_hostel.dto;


public class RoomDTO {
    private String roomId;
    private String type;
    private double keyMoney;
    private int qty;

    public RoomDTO() {
    }

    public RoomDTO(String roomId, String type, double keyMoney, int qty) {
        this.roomId = roomId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(double keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "roomId='" + roomId + '\'' +
                ", type='" + type + '\'' +
                ", keyMoney=" + keyMoney +
                ", qty=" + qty +
                '}';
    }
}
