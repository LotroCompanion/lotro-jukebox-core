package delta.lotro.jukebox.core.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Simple test class for durations.
 * @author DAM
 */
class DurationTest
{
  /**
   * Test duration format.
   */
  @Test
  void test()
  {
    assertEquals("00:00",Duration.getDurationString(0));
    assertEquals("00:59",Duration.getDurationString(59));
    assertEquals("01:00",Duration.getDurationString(60));
    assertEquals("01:00:00",Duration.getDurationString(3600));
    assertEquals("01:01:01",Duration.getDurationString(3661));
  }
}
