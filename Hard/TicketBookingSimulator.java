import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class exp4hard {
    private static final int TOTAL_SEATS = 10;
    private final boolean[] seats = new boolean[TOTAL_SEATS]; // false = available, true = booked
    private final Lock lock = new ReentrantLock();

    // Method to book a seat
    public void bookSeat(int seatNumber, String userType) {
        lock.lock();
        try {
            if (seatNumber < 0 || seatNumber >= TOTAL_SEATS) {
                System.out.println(userType + " tried booking an invalid seat: " + seatNumber);
                return;
            }
            if (!seats[seatNumber]) {
                seats[seatNumber] = true;
                System.out.println(userType + " successfully booked Seat #" + seatNumber);
            } else {
                System.out.println(userType + " tried booking an already booked Seat #" + seatNumber);
            }
        } finally {
            lock.unlock();
        }
    }
}

// Booking Thread
class BookingThread extends Thread {
    private final exp4hard system;
    private final int seatNumber;
    private final String userType;

    public BookingThread(exp4hard system, int seatNumber, String userType, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.userType = userType;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, userType);
    }
}

// Main Class to Run the System
public class TicketBookingSimulator {
    public static void main(String[] args) {
        exp4hard bookingSystem = new exp4hard();

        Thread[] users = new Thread[10];

        // Creating VIP users with higher priority
        users[0] = new BookingThread(bookingSystem, 2, "VIP User 1", Thread.MAX_PRIORITY);
        users[1] = new BookingThread(bookingSystem, 5, "VIP User 2", Thread.MAX_PRIORITY);
        
        // Creating Regular users with normal priority
        for (int i = 2; i < 10; i++) {
            users[i] = new BookingThread(bookingSystem, i % 10, "Regular User " + i, Thread.NORM_PRIORITY);
        }

        // Start all threads
        for (Thread user : users) {
            user.start();
        }

        // Join all threads
        for (Thread user : users) {
            try {
                user.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All bookings are complete.");
    }
}
