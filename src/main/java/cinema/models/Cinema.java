package cinema.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"total_rows", "total_columns", "available_seats"})
public class Cinema {
    @JsonProperty("total_rows")
    private final Integer TOTAL_ROWS = 9;
    @JsonProperty("total_columns")
    private final Integer TOTAL_COLUMNS = 9;
    @JsonProperty("available_seats")
    private final Seat[] availableSeats = new Seat[TOTAL_ROWS * TOTAL_COLUMNS];

    public Cinema() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Seat seat;
                if (row < 4)
                    seat = new Seat(row + 1, col + 1, 10L);
                else
                    seat = new Seat(row + 1, col + 1, 8L);

                availableSeats[row * TOTAL_ROWS + col] = seat;
            }
        }
    }


}
