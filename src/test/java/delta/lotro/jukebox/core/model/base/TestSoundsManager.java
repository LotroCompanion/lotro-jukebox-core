package delta.lotro.jukebox.core.model.base;

import org.junit.jupiter.api.Test;

/**
 * Test class for the sounds manager.
 * @author DAM
 */
class TestSoundsManager
{
  /**
   * Test the sounds manager.
   */
  @Test
  void test()
  {
    SoundsManager sm=SoundsManager.getInstance();
    for(SoundDescription sound : sm.getAllSounds())
    {
      System.out.println(sound);
    }
  }
}
