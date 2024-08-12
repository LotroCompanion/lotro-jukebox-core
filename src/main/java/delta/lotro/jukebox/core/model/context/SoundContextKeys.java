package delta.lotro.jukebox.core.model.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import delta.lotro.jukebox.core.config.DataFiles;

/**
 * Sound context keys.
 * @author DAM
 */
public class SoundContextKeys
{
  /**
   * The list of known keys. 
   */
  public static List<String> KEYS=getKeys();

  private static List<String> getKeys()
  {
    List<String> ret=new ArrayList<String>();
    ret.add(DataFiles.AREAS);
    ret.add(DataFiles.DUNGEONS);
    ret.add(DataFiles.INSTRUMENTS);
    ret.add(DataFiles.MUSIC_ITEMS);
    return Collections.unmodifiableList(ret);
  }
}
