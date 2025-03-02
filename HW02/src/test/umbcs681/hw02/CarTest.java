package umbcs681.hw02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CarTest {
    private List<Car> cars;

    @BeforeEach
    public void setUp() {
        Car car1 = new Car("Toyota", "Camry", 50000, 2020, 25000.0f);
        Car car2 = new Car("Honda", "Civic", 30000, 2019, 20000.0f);
        Car car3 = new Car("Ford", "Focus", 80000, 2018, 15000.0f);
        Car car4 = new Car("Tesla", "Model 3", 10000, 2021, 35000.0f);

        cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);

        Car[] carsArray = cars.toArray(new Car[0]);
        for (Car car : cars) {
            car.setDominationCount(carsArray);
        }
    }

    @Test
    public void testLowestAndHighestPrice() {
        // Find the lowest price using the 2nd version of reduce()
        float lowestPrice = cars.stream()
                .map(Car::getPrice)
                .reduce(Float.MAX_VALUE, (result, price) -> price < result ? price : result);

        // Find the highest price using the 2nd version of reduce()
        float highestPrice = cars.stream()
                .map(Car::getPrice)
                .reduce(0.0f, (result, price) -> price > result ? price : result);

        System.out.println("Lowest car price: $" + lowestPrice);
        System.out.println("Highest car price: $" + highestPrice);

        assertEquals(15000.0f, lowestPrice);
        assertEquals(35000.0f, highestPrice);
    }

    @Test
    void testAveragePrice() {
        // Compute average price using the 3rd version of reduce()
        // Using ArrayList to track: [count, average]
        ArrayList<Float> avgResult = cars.stream()
                .map(Car::getPrice)
                .reduce(
                        new ArrayList<Float>() {{ add(0.0f); add(0.0f); }}, // Initial value [count, avg]
                        (result, price) -> {
                            float currentCount = result.get(0);
                            float currentAvg = result.get(1);

                            // Increment count
                            float newCount = currentCount + 1;
                            result.set(0, newCount);

                            // Update average directly: (avg * count + new_price) / new_count
                            float newAvg = (currentAvg * currentCount + price) / newCount;
                            result.set(1, newAvg);

                            return result;
                        },
                        (finalResult, intermediateResult) -> finalResult // Combiner (just return finalResult)
                );

        // Get the average directly
        float averagePrice = avgResult.get(1);

        System.out.println("Average car price: $" + averagePrice);
        System.out.println("Total cars: " + avgResult.get(0));

        assertEquals(23750.0f, averagePrice);
    }
}
