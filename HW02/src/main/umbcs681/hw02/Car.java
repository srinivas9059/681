package umbcs681.hw02;

public class Car {
    private String make, model;
    private int mileage, year;
    private float price;
    private int dominationCount;

    public Car(String make, String model, int mileage, int year, float price) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
        this.dominationCount = 0;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public float getPrice() {
        return price;
    }

    public int getDominationCount() {
        return dominationCount;
    }

    public void setDominationCount(Car[] cars) {
        int count = 0;
        for (Car otherCar : cars) {
            if (otherCar != this) {
                if (isDominates(otherCar)) {
                    count++;
                }
            }
        }
        this.dominationCount = count;
    }

    private boolean isDominates(Car otherCar) {
        boolean iAmBetter = false;

        boolean myPriceIsEqual = this.price <= otherCar.getPrice();
        boolean myYearIsEqual = this.year >= otherCar.getYear();
        boolean myMileageIsEqual = this.mileage <= otherCar.getMileage();

        boolean myPriceIsStrictlyBetter = this.price < otherCar.getPrice();
        boolean myYearIsStrictlyBetter = this.year > otherCar.getYear();
        boolean myMileageIsStrictlyBetter = this.mileage < otherCar.getMileage();

        if (myPriceIsEqual && myYearIsEqual && myMileageIsEqual &&
                (myPriceIsStrictlyBetter || myYearIsStrictlyBetter || myMileageIsStrictlyBetter)) {
            iAmBetter = true;
        }

        return iAmBetter;
    }
}
