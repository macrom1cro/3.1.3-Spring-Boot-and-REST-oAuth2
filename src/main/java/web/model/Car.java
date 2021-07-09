package web.model;

import org.springframework.stereotype.Component;

import java.util.Objects;


public class Car {
    private String Brand;
    private int MaxSpeed;
    private int NumberOfSeats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return MaxSpeed == car.MaxSpeed && NumberOfSeats == car.NumberOfSeats && Brand.equals(car.Brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Brand, MaxSpeed, NumberOfSeats);
    }

    public Car(String brand, int maxSpeed, int numberOfSeats) {
        Brand = brand;
        MaxSpeed = maxSpeed;
        NumberOfSeats = numberOfSeats;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        this.Brand = brand;
    }

    public int getMaxSpeed() {
        return MaxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        MaxSpeed = maxSpeed;
    }

    public int getNumberOfSeats() {
        return NumberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        NumberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + Brand + '\'' +
                ", MaxSpeed=" + MaxSpeed +
                ", NumberOfSeats=" + NumberOfSeats +
                '}';
    }
}
