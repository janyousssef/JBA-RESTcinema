package cinema.models;

import cinema.services.CinemaService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"total_rows", "total_columns", "available_seats"})
public class Cinema {
    @JsonProperty("total_rows")
    private final Integer TOTAL_ROWS;
    @JsonProperty("total_columns")
    private final Integer TOTAL_COLUMNS;
    @JsonProperty("available_seats")
    private final Seat[] seats;
    private final CinemaService cinemaService = new CinemaService(this);
    long HIGH_PRICE = 10L;
    long LOW_PRICE = 8L;

    public Cinema(Integer TOTAL_ROWS, Integer TOTAL_COLUMNS) {
        this.TOTAL_ROWS = TOTAL_ROWS;
        this.TOTAL_COLUMNS = TOTAL_COLUMNS;
        this.seats = new Seat[TOTAL_ROWS * TOTAL_COLUMNS];
        initializeSeats();
    }

    private void initializeSeats() {
        Seat seat;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (row < 4) {
                    seat = new Seat(row + 1, col + 1, HIGH_PRICE, false);
                } else {
                    seat = new Seat(row + 1, col + 1, LOW_PRICE, false);
                }

                seats[row * TOTAL_ROWS + col] = seat;
            }
        }
    }

    public Integer getTOTAL_ROWS() {
        return TOTAL_ROWS;
    }

    public Seat[] getSeats() {
        return seats;
    }


}
