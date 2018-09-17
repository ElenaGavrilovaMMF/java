package entity;

import exception.StoreException;
import log.StoreLogger;
import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class 2, which stores the products brought.
 * The Singleton pattern is implemented.
 */
@Data
public final class Store {
    /**
     * Store class object.
     */
    private static final Store INSTANCE = new Store();
    /**
     * Constant storing the maximum volume of the store.
     */
    private static final Double NOMINAL_VOLUME = 100.0;
    /**
     * Number of products in the store.
     */
    private static Double product = 0.0;
    /**
     * ReentrantLock object to block the store.
     */
    private ReentrantLock locker;
    /**
     * The condition of the locker.
     */
    private Condition condition;
    /**
     * Berths available to ships.
     */
    private Berth berth;

    /**
     * Initializes the newly created object {@code Store},
     * so he represented the stored products.
     * Pay attention to the initialization of the
     * {@code ReentrantLock} and {@code Berth},
     * which provides the lock of the store and the number
     * of berths, respectively.
     */
    private Store() {
        locker = new ReentrantLock();
        condition = locker.newCondition();
        berth = new Berth();
        StoreLogger.logInfo("Products in Store: " + product);
    }

    /**
     * The method unloads the store, that is, removes object
     * {@code volume} that are loaded onto the ship.
     * If there are not enough products in the store,
     * an exception is thrown {@code StoreException}.
     * <p>
     * The method has a locker that ensures thread safety.
     *
     * @param volume shiploaded products.
     */
    public void giveProducts(final Double volume) {
        locker.lock();
        try {
            if (product >= volume) {
                product -= volume;
            } else {
                try {
                    throw new StoreException();
                } catch (StoreException e) {
                    StoreLogger.logError(e.getMessageGive());
                }
            }
        } finally {
            locker.unlock();
        }
    }

    /**
     * The method loads the store, that is, adds {@code volume}
     * to the current products.
     * <p>
     * The method has a locker that ensures thread safety.
     *
     * @param volume number of products that the ship is trying
     *               to unload into the store.
     */
    public void acceptProducts(final Double volume) {
        locker.lock();
        try {
            if (NOMINAL_VOLUME - product >= volume) {
                product += volume;
            }
            if (product < volume) {
                final Double volumeNew = NOMINAL_VOLUME - product;
                product += volumeNew;
                try {
                    throw new StoreException();
                } catch (StoreException e) {
                    StoreLogger.logError(e.getMessageAccept());
                }
            }
            condition.signalAll();
        } finally {
            locker.unlock();
        }
    }

    /**
     * Returns current product current of the {@code Store}.
     *
     * @return {@code double} current product.
     */
    public double getProduct() {
        return product;
    }

    /**
     * Returns nominal volume of the {@code Store}.
     *
     * @return {@code double} nominal volume.
     */
    public double getNominalVolume() {
        return NOMINAL_VOLUME;
    }

    /**
     * The method returns an object of class {@code Store}.
     *
     * @return {@code Store}.
     */
    public static Store getInstance() {
        return INSTANCE;
    }

}
