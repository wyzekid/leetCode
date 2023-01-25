package companies.conundrum;

import companies.conundrum.CarRentalService;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CarRentalServiceTest {

    @Test
    public void rent() {
        CarRentalService carRentalService = new CarRentalService();

        String rent_1 = carRentalService.rent("toyota");
        Assert.assertEquals("toyota-1", rent_1);

        String rent_2 = carRentalService.rent("toyota");
        Assert.assertEquals("toyota-2", rent_2);

        carRentalService.drop("toyota-1");
        String rent_3 = carRentalService.rent("toyota");
        Assert.assertEquals("toyota-1", rent_3);

        String rent_4 = carRentalService.rent("toyota");
        Assert.assertEquals("toyota-3", rent_4);

        carRentalService.drop("toyota-2");
        String rent_5 = carRentalService.rent("toyota");
        Assert.assertEquals("toyota-2", rent_5);

        String rent_6 = carRentalService.rent("bmw");
        Assert.assertEquals("bmw-1", rent_6);
    }

    @Test
    public void drop() {
    }
}