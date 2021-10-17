package transmission;

/**
 * A class that automatically changes gears according to the car's speed.
 */
public final class AutomaticTransmission implements Transmission {

  private int speed;
  private int gear;

  private final int t1;
  private final int t2;
  private final int t3;
  private final int t4;
  private final int t5;

  /**
   * Initializes this transmission's speed thresholds for the possible gears.
   *
   * @param t1 threshold for gear 2.
   * @param t2 threshold for gear 3.
   * @param t3 threshold for gear 4.
   * @param t4 threshold for gear 5.
   * @param t5 threshold for gear 6.
   */
  public AutomaticTransmission(int t1, int t2, int t3, int t4, int t5) {

    if (t1 <= 0) {
      throw new IllegalArgumentException("threshold 1 should be greater than 0");
    }
    if (t2 <= t1) {
      throw new IllegalArgumentException("threshold 2 should be greater than threshold 1");
    }
    if (t3 <= t2) {
      throw new IllegalArgumentException("threshold 3 should be greater than threshold 2");
    }
    if (t4 <= t3) {
      throw new IllegalArgumentException("threshold 4 should be greater than threshold 3");
    }
    if (t5 <= t4) {
      throw new IllegalArgumentException("threshold 5 should be greater than threshold 4");
    }

    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
    this.t4 = t4;
    this.t5 = t5;
  }

  private void changeGear() {
    if (speed >= t5) {
      gear = 6;
    }
    else if (speed >= t4) {
      gear = 5;
    }
    else if (speed >= t3) {
      gear = 4;
    }
    else if (speed >= t2) {
      gear = 3;
    }
    else if (speed >= t1) {
      gear = 2;
    }
    else if (speed > 0) {
      gear = 1;
    }
    else {
      gear = 0;
    }
  }

  @Override
  public int getSpeed() {
    return speed;
  }

  @Override
  public int getGear() {
    return gear;
  }

  @Override
  public void increaseSpeed() {
    speed += 1;
    changeGear();
  }

  @Override
  public void decreaseSpeed() throws IllegalStateException {
    if (speed == 0) {
      throw new IllegalStateException();
    }
    speed -= 1;
    changeGear();
  }

  @Override
  public String toString() {
    return String.format("Transmission (speed = %d, gear = %d)", speed, gear);
  }
}
