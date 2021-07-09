package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceListCarImpl implements ServiceListCar {

    public List<Car> getCarList(int count){
        List<Car> listCar = new ArrayList<>();
        List<Car> listOfCarsRequiredLength = new ArrayList<>();

        listCar.add(new Car("Mazda",200,4));
        listCar.add(new Car("Mitsubishi",250,2));
        listCar.add(new Car("Volkswagen",150,4));
        listCar.add(new Car("BMW",220,2));
        listCar.add(new Car("Ford",190,4));

        if (count > 5) {
            count = 5;
        }
        for (int i=0; i <= (count -1); i++){
            listOfCarsRequiredLength.add(i, listCar.get(i));
        }
        return listOfCarsRequiredLength;
    }
}
