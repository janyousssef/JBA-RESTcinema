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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                availableSeats[i * TOTAL_ROWS + j] = new Seat(i + 1, j + 1);
            }
        }
    }


}
