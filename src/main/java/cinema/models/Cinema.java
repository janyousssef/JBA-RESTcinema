package cinema.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@JsonPropertyOrder(value = {"total_rows", "total_columns", "available_seats"})
public class Cinema {
    @JsonProperty("total_rows")
    public final Integer TOTAL_ROWS;
    @JsonProperty("total_columns")
    public final Integer TOTAL_COLUMNS;
    private final Map<UUID, Seat> purchasedSeats;
    @JsonProperty("available_seats")
    private final Seat[] seats;

    public Cinema(Integer TOTAL_ROWS, Integer TOTAL_COLUMNS) {
        this.TOTAL_ROWS = TOTAL_ROWS;
        this.TOTAL_COLUMNS = TOTAL_COLUMNS;
        this.seats = new Seat[TOTAL_ROWS * TOTAL_COLUMNS];
        purchasedSeats = new HashMap<>();
        initializeSeats();
    }

    private void initializeSeats() {
        Seat seat;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (row < 4) {
                    seat = Seat.withHighPrice(row + 1, col + 1);
                } else {
                    seat = Seat.withLowPrice(row + 1, col + 1);
                }

                seats[row * TOTAL_ROWS + col] = seat;
            }
        }
    }


    public Seat[] getSeats() {
        return seats;
    }

    public void registerPurchase(UUID id, Seat seat) {
        purchasedSeats.put(id, seat);
    }

    public boolean isPurchased(UUID id) {
        return purchasedSeats.containsKey(id);
    }

    public Seat returnSeat(UUID id) {
        purchasedSeats.get(id).unreserve();
        return purchasedSeats.remove(id);
    }

}
