import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import transmission.AutomaticTransmission;
import transmission.Transmission;

/**
 * Test class for AutomaticTransmission class.
 */
public class TransmissionTest {

  Transmission myTransmission;

  Transmission setUp(int t1, int t2, int t3, int t4, int t5) {
    return new AutomaticTransmission(t1, t2, t3, t4, t5);
  }

  private void increaseSpeedBy(int n) {
    for (int i = 0; i < n; i++) {
      myTransmission.increaseSpeed();
    }
  }

  private void decreaseSpeedBy(int n) {
    for (int i = 0; i < n; i ++) {
      myTransmission.decreaseSpeed();
    }
  }

  /**
   * test case to check illegal negative speeds.
   */
  @Test(expected = IllegalStateException.class)
  public void testNegativeSpeed() {
    myTransmission = setUp(10, 20, 30, 40, 50);
    decreaseSpeedBy(1);
    fail("Speed cannot be Negative");
  }

  /**
   * test case to check illegal threshold speeds.
   */
  @Test
  public void testIllegalThresholdSpeeds() {
    try {
      myTransmission = setUp(0, 20, 30, 40, 50);
      fail("should throw an IllegalArgumentException");
    }
    catch (IllegalArgumentException iAE) {
      assertEquals("threshold 1 should be greater than 0", iAE.getMessage());
    }

    try {
      myTransmission = setUp(10, 5, 30, 40, 50);
      fail("should throw an IllegalArgumentException");
    }
    catch (IllegalArgumentException iAE) {
      assertEquals("threshold 2 should be greater than threshold 1", iAE.getMessage());
    }

    try {
      myTransmission = setUp(10, 20, 15, 40, 50);
      fail("should throw an IllegalArgumentException");
    }
    catch (IllegalArgumentException iAE) {
      assertEquals("threshold 3 should be greater than threshold 2", iAE.getMessage());
    }

    try {
      myTransmission = setUp(10, 20, 30, 25, 50);
      fail("should throw an IllegalArgumentException");
    }
    catch (IllegalArgumentException iAE) {
      assertEquals("threshold 4 should be greater than threshold 3", iAE.getMessage());
    }

    try {
      myTransmission = setUp(10, 20, 30, 40, 35);
      fail("should throw an IllegalArgumentException");
    }
    catch (IllegalArgumentException iAE) {
      assertEquals("threshold 5 should be greater than threshold 4", iAE.getMessage());
    }
  }

  /**
   * test cases to check the functionalities of
   * getGear(), getSpeed(), increaseSpeed(), decreaseSpeed() methods.
   */
  @Test
  public void testTransmission() {
    myTransmission = setUp(10, 20, 30, 40, 50);

    assertEquals(0, myTransmission.getSpeed());
    assertEquals(0, myTransmission.getGear());

    increaseSpeedBy(9);
    assertEquals(9, myTransmission.getSpeed());
    assertEquals(1, myTransmission.getGear());

    increaseSpeedBy(10);
    assertEquals(19, myTransmission.getSpeed());
    assertEquals(2, myTransmission.getGear());

    increaseSpeedBy(10);
    assertEquals(29, myTransmission.getSpeed());
    assertEquals(3, myTransmission.getGear());

    increaseSpeedBy(10);
    assertEquals(39, myTransmission.getSpeed());
    assertEquals(4, myTransmission.getGear());

    increaseSpeedBy(10);
    assertEquals(49, myTransmission.getSpeed());
    assertEquals(5, myTransmission.getGear());

    increaseSpeedBy(1);
    assertEquals(50, myTransmission.getSpeed());
    assertEquals(6, myTransmission.getGear());

    decreaseSpeedBy(1);
    assertEquals(49, myTransmission.getSpeed());
    assertEquals(5, myTransmission.getGear());

    decreaseSpeedBy(10);
    assertEquals(39, myTransmission.getSpeed());
    assertEquals(4, myTransmission.getGear());

    decreaseSpeedBy(10);
    assertEquals(29, myTransmission.getSpeed());
    assertEquals(3, myTransmission.getGear());

    decreaseSpeedBy(10);
    assertEquals(19, myTransmission.getSpeed());
    assertEquals(2, myTransmission.getGear());

    decreaseSpeedBy(10);
    assertEquals(9, myTransmission.getSpeed());
    assertEquals(1, myTransmission.getGear());

    decreaseSpeedBy(9);
    assertEquals(0, myTransmission.getSpeed());
    assertEquals(0, myTransmission.getGear());
  }

  /**
   * test case to check if the toString() method is returning the correct values.
   */
  @Test
  public void testToString() {
    myTransmission = setUp(10, 20, 30, 40, 50);
    increaseSpeedBy(45);
    String expectedString = "Transmission (speed = 45, gear = 5)";
    assertEquals(expectedString, myTransmission.toString());
  }
}