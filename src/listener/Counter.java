package listener;

/**
 * this class represent counter and his methods.
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Counter {

    private int value;

    /**
     * this method is the constructor of the counter.
     * @param value is the value of the new counter
     */
    public Counter(int value) {
        this.value = value;
    }
    /**
     * this method add number to current count.
     * @param number is the amount we add to the current count
     */
    public void increase(int number) {
        this.value = this.value + number;
    }

    /**
     * this method subtract number from current count.
     * @param number is the number we subtract from current count
     */
    public void decrease(int number) {
        this.value = this.value - number;
    }


    /**
     * this method return the value of the current counter.
     * @return the value of the current counter
     */
    public int getValue() {
        return this.value;
    }
}
