package hu.progmasters.hotel.domain;

import hu.progmasters.hotel.dto.request.RoomForm;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(unique = true)
    private String name;

    @Min(1)
    @Max(50)
    private Integer numberOfBeds;

    @Min(1)
    @Max(1000000)
    private Integer pricePerNight;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    private List<String> imageUrls = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

    private boolean isDeleted;

    @ManyToOne
    private Hotel hotel;


    public Room(RoomForm roomForm) {
        this.name = roomForm.getName();
        this.numberOfBeds = roomForm.getNumberOfBeds();
        this.pricePerNight = roomForm.getPricePerNight();
        this.description = roomForm.getDescription();
        this.isDeleted = false;
    }
}
