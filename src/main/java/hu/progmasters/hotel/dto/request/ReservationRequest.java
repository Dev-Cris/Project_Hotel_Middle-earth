package hu.progmasters.hotel.dto.request;

import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ReservationRequest {

    @NotNull(message = "User ID must not be null")
    private Long userId;
    @FutureOrPresent(message = "Date must be present date or in the future")
    private LocalDate startDate;
    @FutureOrPresent(message = "Date must in the future")
    private LocalDate endDate;
    @NotNull(message = "Must not be null")
    private Long roomId;
}
