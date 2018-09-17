package exception;

import entity.Store;
import lombok.NoArgsConstructor;

/**
 * The class {@code ShipRunException} and its subclasses are a form of
 * {@code Exception} that indicates conditions that a reasonable
 * application might want to catch.
 * <p>
 * <p>The class {@code ShipRunException} and any subclasses that are not also
 * subclasses of {@link RuntimeException} are <em>checked
 * exceptions</em>.  Checked exceptions need to be declared in a
 * method or constructor's {@code throws} clause if they can be thrown
 * by the execution of the method or constructor and propagate outside
 * the method or constructor boundary.
 */
@NoArgsConstructor
public class StoreException extends Exception {
    /**
     * Exception message.
     */
    private String message = "Store has: " + Store.getInstance().getProduct();

    /**
     * The method returns an exception message if
     * there are not enough products in the store.
     *
     * @return {@code String} exception message.
     */
    public final String getMessageGive() {

        return "Not enough products in store: "
                + Store.getInstance().getProduct();
    }

    /**
     * The method returns an exception message if
     * there are not enough volume in the store.
     *
     * @return {@code String} exception message.
     */
    public final String getMessageAccept() {

        return "Not enough volume in store: "
                + Store.getInstance().getProduct();
    }

    /**
     * A redefined method of the {@code Exception} class.
     * Returns the exception message.
     *
     * @return {@code String} exception message.
     */
    @Override
    public final String getMessage() {
        return message;
    }
}
