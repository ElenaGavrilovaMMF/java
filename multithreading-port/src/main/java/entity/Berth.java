package entity;

import lombok.Data;

import java.util.concurrent.Semaphore;

/**
 * The {@code Berth} class is an entity class
 * that stores a variable count - the number
 * of berths in the port and the semaphore
 * for these berths.
 */
@Data
class Berth {

    /**
     * Number of berths at the storage.
     */
    private int count = 2;
    /**
     * The semaphore is responsible for
     * accessing the ships to the berths.
     * Ensures thread safety of the berth.
     */
    private Semaphore sem;

    /**
     * Initializes the newly created {@code Berth} object
     * so that it represents their quantity at the {@code Store}.
     * Pay attention to the initialization of the semaphore,
     * which ensures thread safety of the berth.
     */
    Berth() {
        sem = new Semaphore(count);
    }
}
