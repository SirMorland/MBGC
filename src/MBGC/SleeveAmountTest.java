package MBGC;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2016.07.29 11:57:52 // Generated by ComTest
 *
 */
public class SleeveAmountTest {



  // Generated by ComTest BEGIN
  /** testSleeveAmount17 */
  @Test
  public void testSleeveAmount17() {    // SleeveAmount: 17
    SleeveAmount sleeveAmount = new SleeveAmount("0|0|50"); 
    assertEquals("From: SleeveAmount line: 19", "0|0|50", sleeveAmount.toString()); 
    SleeveAmount sleeveAmount1 = new SleeveAmount("|0|50"); 
    assertEquals("From: SleeveAmount line: 21", "-1|0|0", sleeveAmount1.toString()); 
    SleeveAmount sleeveAmount2 = new SleeveAmount("|"); 
    assertEquals("From: SleeveAmount line: 23", "-1|0|0", sleeveAmount2.toString()); 
    SleeveAmount sleeveAmount3 = new SleeveAmount(""); 
    assertEquals("From: SleeveAmount line: 25", "-1|0|0", sleeveAmount3.toString()); 
  } // Generated by ComTest END
}