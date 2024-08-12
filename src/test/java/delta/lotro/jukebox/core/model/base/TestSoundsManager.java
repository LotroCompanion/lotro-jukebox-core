package delta.lotro.jukebox.core.model.base;

import junit.framework.TestCase;

/**
 * Test class for the sounds manager.
 * @author DAM
 */
public class TestSoundsManager extends TestCase
{
  /**
   * Test the sounds manager.
   */
  public void test()
  {
    SoundsManager sm=SoundsManager.getInstance();
    for(SoundDescription sound : sm.getAllSounds())
    {
      System.out.println(sound);
    }
  }
}
