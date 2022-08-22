package delta.lotro.jukebox.core.utils;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Simple test class for durations.
 * @author DAM
 */
public class DurationTest extends TestCase
{
  /**
   * Test duration format.
   */
  public void test()
  {
    Assert.assertEquals("00:00",Duration.getDurationString(0));
    Assert.assertEquals("00:59",Duration.getDurationString(59));
    Assert.assertEquals("01:00",Duration.getDurationString(60));
    Assert.assertEquals("01:00:00",Duration.getDurationString(3600));
    Assert.assertEquals("01:01:01",Duration.getDurationString(3661));
  }
}
