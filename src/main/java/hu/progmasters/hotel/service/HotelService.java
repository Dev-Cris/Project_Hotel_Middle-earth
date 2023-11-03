package hu.progmasters.hotel.service;

import hu.progmasters.hotel.domain.Hotel;
import hu.progmasters.hotel.domain.Room;
import hu.progmasters.hotel.dto.request.HotelCreateRequest;
import hu.progmasters.hotel.dto.response.RoomDetails;
import hu.progmasters.hotel.dto.request.RoomForm;
import hu.progmasters.hotel.dto.response.RoomListItem;
import hu.progmasters.hotel.repository.HotelRepository;
import hu.progmasters.hotel.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by szfilep.
 */
@Service
public class HotelService {

    private RoomRepository roomRepository;
    private HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HotelService(RoomRepository roomRepository, ModelMapper modelMapper, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
        this.hotelRepository = hotelRepository;
    }

    public List<RoomListItem> getRoomList() {
        List<RoomListItem> roomListItems = new ArrayList<>();
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            RoomListItem item = new RoomListItem();
            updateRoomListItemValues(item, room);
            roomListItems.add(item);
        }
        return roomListItems;
    }

    public void createRoom(RoomForm roomForm) {
        roomRepository.save(new Room(roomForm));
    }

    public RoomDetails getRoomDetails(Long roomId) {
        RoomDetails roomDetails = new RoomDetails();

        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isPresent()) {
            roomDetails.setId(room.get().getId());
            roomDetails.setName(room.get().getName());
            roomDetails.setNumberOfBeds(room.get().getNumberOfBeds());
            roomDetails.setPricePerNight(room.get().getPricePerNight());
            roomDetails.setDescription(room.get().getDescription());
            roomDetails.setImageUrl(room.get().getImageUrl());
        } else {
            throw new IllegalArgumentException("There is no Room for this id:" + roomId);
        }
        return roomDetails;
    }

    private void updateRoomListItemValues(RoomListItem item, Room room) {
        item.setId(room.getId());
        item.setName(room.getName());
        item.setNumberOfBeds(room.getNumberOfBeds());
        item.setPricePerNight(room.getPricePerNight());
        item.setImageUrl(room.getImageUrl());
    }

    public void createHotel(HotelCreateRequest hotelCreateRequest) {
        hotelRepository.save(modelMapper.map(hotelCreateRequest, Hotel.class));
    }
}
