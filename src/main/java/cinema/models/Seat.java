package cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public final class Seat {
    private final Integer row;
    private final Integer column;
    private final Long price;
    private boolean isReserved;

    public Seat(Integer row, Integer column, Long price) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.isReserved = false;
    }


    public static Seat withHighPrice(Integer row, Integer column) {
        return new Seat(row, column, 10L);
    }

    public static Seat withLowPrice(Integer row, Integer column) {
        return new Seat(row, column, 8L);
    }

    public Seat reserve() {
        isReserved = true;
        return this;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public Long getPrice() {
        return price;
    }

    @JsonIgnore
    public boolean isReserved() {
        return isReserved;
    }

    @JsonIgnore
    public boolean isInvalidInstance() {
        return row > 9 || column > 9 || row < 1 || column < 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Seat) obj;
        return Objects.equals(this.row, that.row) &&
                Objects.equals(this.column, that.column);


    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "Seat[" +
                "row=" + row + ", " +
                "column=" + column + ", " +
                "price=" + price + ", " +
                "isReserved=" + isReserved + ']';
    }

}
