package hotelManagementSystem;

public class Room {
    private final String id;
    private final RoomType roomType;
    private final double price;
    private RoomStatus roomStatus;

    public Room(String id, RoomType roomType, double price) {
        this.id = id;
        this.roomType = roomType;
        this.price = price;
        this.roomStatus=RoomStatus.AVAILABLE;
    }

    public synchronized void book(){
        if(roomStatus.equals(RoomStatus.AVAILABLE)){
            roomStatus=RoomStatus.BOOKED;
        }
        else
            throw new IllegalStateException("Room is not avaiable for booking");
    }
    public synchronized void checkin(){
        if(roomStatus.equals(RoomStatus.BOOKED)){
            roomStatus=RoomStatus.OCCUPIED;
        }
        else
            throw new IllegalStateException("Room is not booked");
    }

    public synchronized void checkout(){
        if(roomStatus.equals(RoomStatus.OCCUPIED)){
            roomStatus=RoomStatus.AVAILABLE;
        }
        else
            throw new IllegalStateException("Room is not occupied");
    }


    public String getId() {
        return id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }
}
