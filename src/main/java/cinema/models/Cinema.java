package cinema.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"total_rows", "total_columns", "available_seats"})
public class Cinema {
    @JsonProperty("total_rows")
    private final Integer TOTAL_ROWS;
    @JsonProperty("total_columns")
    private final Integer TOTAL_COLUMNS;
    @JsonProperty("available_seats")
    private final Seat[] availableSeats;


    public Cinema(Integer TOTAL_ROWS, Integer TOTAL_COLUMNS) {
        this.TOTAL_ROWS = TOTAL_ROWS;
        this.TOTAL_COLUMNS = TOTAL_COLUMNS;
        this.availableSeats = new Seat[TOTAL_ROWS * TOTAL_COLUMNS];
        initializeSeats();
    }

    private void initializeSeats() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Seat seat;
                if (row < 4)
                    seat = new Seat(row + 1, col + 1, 10L,false);
                else
                    seat = new Seat(row + 1, col + 1, 8L,false);

                availableSeats[row * TOTAL_ROWS + col] = seat;
            }
        }
    }

    public Seat getSeat(Integer row,Integer col){
        row--;
        col--;
        return availableSeats[row * TOTAL_ROWS + col];
    }

}
