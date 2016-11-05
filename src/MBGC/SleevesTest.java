package MBGC;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2016.07.29 12:19:13 // Generated by ComTest
 *
 */
public class SleevesTest {



  // Generated by ComTest BEGIN
  /** testSleeves15 */
  @Test
  public void testSleeves15() {    // Sleeves: 15
    Sleeve sleeve = new Sleeve("0|Ultra Pro|50|3.50"); 
    assertEquals("From: Sleeves line: 17", "0|Ultra Pro|50|3.5", sleeve.toString());
    Sleeve sleeve1 = new Sleeve("|Ultra Pro|50|3.50"); 
    assertEquals("From: Sleeves line: 19", "-1||0|0.0", sleeve1.toString()); 
    Sleeve sleeve2 = new Sleeve("1||50|3.50"); 
    assertEquals("From: Sleeves line: 21", "1||50|3.5", sleeve2.toString()); 
    Sleeve sleeve3 = new Sleeve("|"); 
    assertEquals("From: Sleeves line: 23", "-1||0|0.0", sleeve3.toString()); 
    Sleeve sleeve4 = new Sleeve(""); 
    assertEquals("From: Sleeves line: 25", "-1||0|0.0", sleeve4.toString()); 
    Sleeves sleeves = new Sleeves(); 
    sleeves.addSleeve(sleeve); 
    sleeves.addSleeve(sleeve1); 
    assertEquals("From: Sleeves line: 30", "0|Ultra Pro|50|3.5\n-1||0|0.0\n", sleeves.toString()); 
    sleeves.removeSleeve(sleeve); 
    assertEquals("From: Sleeves line: 32", "-1||0|0.0\n", sleeves.toString()); 
  } // Generated by ComTest END
}