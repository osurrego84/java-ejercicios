package ejercicios.preguntas;

import java.util.Objects;

class Vehicle implements IVehicle {
    private final String make;
    private final String model;
    private final int year;
    private final int price;

    public Vehicle(String make, String model, int year, int price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    @Override public String getMake() { return make; }
    @Override public String getModel() { return model; }
    @Override public int getYear() { return year; }
    @Override public int getPrice() { return price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle v = (Vehicle) o;
        return year == v.year && price == v.price
                && Objects.equals(make, v.make) && Objects.equals(model, v.model);
    }

    @Override
    public int hashCode() { return Objects.hash(make, model, year, price); }

    @Override
    public String toString() { return make + " " + model + " " + year; }
}
