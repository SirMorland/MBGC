package MBGC;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2016.07.29 12:16:46 // Generated by ComTest
 *
 */
public class SleeveTest {



  // Generated by ComTest BEGIN
  /** testSleeve18 */
  @Test
  public void testSleeve18() {    // Sleeve: 18
    Sleeve sleeve = new Sleeve("0|Ultra Pro|50|3.50"); 
    assertEquals("From: Sleeve line: 20", "0|Ultra Pro|50|3.5", sleeve.toString());
    Sleeve sleeve1 = new Sleeve("|Ultra Pro|50|3.50"); 
    assertEquals("From: Sleeve line: 22", "-1||0|0.0", sleeve1.toString()); 
    Sleeve sleeve2 = new Sleeve("1||50|3.50"); 
    assertEquals("From: Sleeve line: 24", "1||50|3.5", sleeve2.toString()); 
    Sleeve sleeve3 = new Sleeve("|"); 
    assertEquals("From: Sleeve line: 26", "-1||0|0.0", sleeve3.toString()); 
    Sleeve sleeve4 = new Sleeve(""); 
    assertEquals("From: Sleeve line: 28", "-1||0|0.0", sleeve4.toString()); 
  } // Generated by ComTest END
}