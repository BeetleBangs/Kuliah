import java.util.ArrayList;
import java.util.Scanner;

// Abstract class (Abstraction)
abstract class Vehicle {
    protected String licensePlate;
    
    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }


    public abstract String getVehicleType();  // Abstract method

    public abstract void park();  // Abstract method
}

// Inheritance (Car class extends Vehicle)
class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

    @Override
    public void park() {
        System.out.println("Car with plate number " + licensePlate + " is parked.");
    }
}

// Inheritance (Motorcycle class extends Vehicle)
class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }

    @Override
    public void park() {
        System.out.println("Motorcycle with plate number " + licensePlate + " is parked.");
    }
}

// ParkingLot class (Encapsulation)
class ParkingLot {
    private ArrayList<Vehicle> parkedVehicles;
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedVehicles = new ArrayList<>();
    }

    // Polymorphism - This method can accept both Car and Motorcycle (because both extend Vehicle)
    public void addVehicle(Vehicle vehicle) {
        if (parkedVehicles.size() < capacity) {
            parkedVehicles.add(vehicle);
            vehicle.park();
        } else {
            System.out.println("Parking lot is full.");
        }
    }

    public void removeVehicle(String licensePlate) {
        boolean found = false;
        for (Vehicle v : parkedVehicles) {
            if (v.getLicensePlate().equals(licensePlate)) {
                parkedVehicles.remove(v);
                System.out.println(v.getVehicleType() + " with plate number " + licensePlate + " has left the parking lot.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Vehicle not found.");
        }
    }

    public void showStatus() {
        if (parkedVehicles.isEmpty()) {
            System.out.println("Parking lot is empty.");
        } else {
            System.out.println("Vehicles currently parked:");
            for (Vehicle v : parkedVehicles) {
                System.out.println("- " + v.getVehicleType() + ": " + v.getLicensePlate());
            }
        }
    }
}

// Main class
public class ParkingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = new ParkingLot(5); // Create a parking lot with capacity 5

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Parkir kendaraan");
            System.out.println("2. Keluarkan kendaraan");
            System.out.println("3. Lihat status parkir");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
    System.out.print("Masukkan nomor plat kendaraan: ");
    scanner.nextLine(); // Tambahkan ini untuk menangani newline character setelah nextInt()
    String licensePlate = scanner.nextLine(); // Gunakan nextLine untuk menangkap input dengan spasi
    System.out.println("Pilih tipe kendaraan: 1. Mobil  2. Motor");
    int type = scanner.nextInt();
    if (type == 1) {
        Car car = new Car(licensePlate);
        parkingLot.addVehicle(car);
    } else if (type == 2) {
        Motorcycle motorcycle = new Motorcycle(licensePlate);
        parkingLot.addVehicle(motorcycle);
    } 
        else if (type == 3) {
            System.out.println("Halo.");
        }

    else {
        System.out.println("Tipe kendaraan tidak valid.");
    }
    break;

                case 2:
                    System.out.print("Masukkan nomor plat kendaraan yang akan dikeluarkan: ");
                    String plateToRemove = scanner.next();
                    parkingLot.removeVehicle(plateToRemove);
                    break;
                case 3:
                    parkingLot.showStatus();
                    break;
                case 4:
                    running = false;
                    System.out.println("Terima kasih telah menggunakan sistem parkir.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }
}
