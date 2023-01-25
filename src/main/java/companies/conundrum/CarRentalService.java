package companies.conundrum;

/*
Car Rental Service.
Car names contain an alphabetic manufacturer name and the car number that incremented sequentially.
So that "toyota-1", "toyota-2", "bmw-5" are valid car names.
Write a class with two methods: rent(carType) and drop(carName).
The first should reserve a car for a client and return the next available carName.
The second should release the car from the rent and make the car available for the rent again.




carRentalService = new CarRentalService();

carRentalService.rent("toyota");
>> "toyota-1"

carRentalService.rent("toyota");
>> "toyota-2"

carRentalService.drop("toyota-1");
carRentalService.rent("toyota");
>> "toyota-1"

carRentalService.rent("toyota");
>> "toyota-3"

carRentalService.drop("toyota-2");
carRentalService.rent("toyota");
>> "toyota-2"

carRentalService.rent("bmw");
>> "bmw-1"
 */


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CarRentalService {

    private final Map<String, Boolean> rentData = new TreeMap<>();
    private final Map<String, Integer> rentCount = new HashMap<>();

    public String rent(String carModel) {
        if (rentData.isEmpty()) {
            rentCount.put(carModel, 1);
            rentData.put(carModel, false);
            return carModel.concat("-1");
        }
        String availableCar = rentData.keySet().stream()
                .filter(rentData::get)
                .filter(carName -> carName.contains(carModel))
                .findFirst()
                .orElse(null);

        if (availableCar != null) {
            rentData.put(availableCar, false);
            return availableCar;
        }

        Integer currentCount = rentCount.get(carModel) != null ? rentCount.get(carModel) : 0;
        currentCount++;
        rentCount.put(carModel, currentCount);

        String updatedName = carModel.concat("-").concat(String.valueOf(rentCount.get(carModel)));

        rentData.put(updatedName , false);
        return updatedName;
    }

    public void drop(String carName) {
        rentData.put(carName, true);
    }

}
