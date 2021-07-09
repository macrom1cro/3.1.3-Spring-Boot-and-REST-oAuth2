package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.ServiceListCar;

@Controller
public class CarsController {
    @Autowired
    ServiceListCar serviceListCar;
    @GetMapping("/cars")
    public String listCars (@RequestParam (value = "count", defaultValue = "5") int count, ModelMap model){
        model.addAttribute("carList", serviceListCar.getCarList(count));
        return "cars";
    }
}
