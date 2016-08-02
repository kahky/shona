
import controllers.CarsJpaController;
import entities.Cars;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bassem
 */
public class shona {

    public static void main(String[] args) {
        try {
            CarsJpaController carController = new CarsJpaController();
//        Cars car = new Cars();
//        car.setCreatedat(new Date());
//        car.setCreatedbyid(null);
//        car.setOwnerName("Bassem Salama");
//        car.setIsOurs(1);
//        carController.create(car);
            Cars car = carController.findCars(1);
            System.out.println(car.getOwnerName());
            car.setOwnerName("testupdate");
            carController.edit(car);
        } catch (Exception ex) {
            Logger.getLogger(shona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
