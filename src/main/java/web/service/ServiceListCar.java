package web.service;

import web.model.Car;

import java.util.List;

public interface ServiceListCar {
    List<Car> getCarList(int n);
}
