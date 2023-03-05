package cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record Seat(Integer row, Integer column, Long price, @JsonIgnore boolean isReserved) {
}
