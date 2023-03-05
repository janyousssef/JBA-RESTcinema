package cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public final class Seat {
    private Integer row;
    private Integer column;
    private Long price;
    private boolean isReserved;

    public Seat() {
    }

    public Seat(Integer row, Integer column, Long price, boolean isReserved) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.isReserved = isReserved;
    }

    public Seat(Integer row, Integer column, Long price) {
        this(row, column, price, false);
    }

    public void reserve() {
        isReserved = true;
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

    public boolean invalidInstance() {
        return row > 9 || column > 9 || row < 1 || column < 1;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Seat) obj;
        return Objects.equals(this.row, that.row) &&
                Objects.equals(this.column, that.column) &&
                Objects.equals(this.price, that.price) &&
                this.isReserved == that.isReserved;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, price, isReserved);
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
