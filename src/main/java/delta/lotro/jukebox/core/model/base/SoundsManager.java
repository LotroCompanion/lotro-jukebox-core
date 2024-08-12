package delta.lotro.jukebox.core.model.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import delta.common.utils.id.IdentifiableComparator;
import delta.lotro.jukebox.core.config.DataFiles;
import delta.lotro.jukebox.core.config.LotroJukeboxCoreConfig;
import delta.lotro.jukebox.core.model.base.io.xml.SoundsXMLParser;

/**
 * Sounds manager.
 * @author DAM
 */
public class SoundsManager
{
  private static final Logger LOGGER=LoggerFactory.getLogger(SoundsManager.class);

  private static SoundsManager _instance=null;

  private Map<Integer,SoundDescription> _sounds;

  /**
   * Get the sole instance of this class.
   * @return the sole instance of this class.
   */
  public static SoundsManager getInstance()
  {
    if (_instance==null)
    {
      _instance=load();
    }
    return _instance;
  }

  /**
   * Constructor.
   */
  public SoundsManager()
  {
    _sounds=new HashMap<Integer,SoundDescription>();
  }

  /**
   * Load data from a file.
   * @return the loaded data.
   */
  private static SoundsManager load()
  {
    LotroJukeboxCoreConfig cfg=LotroJukeboxCoreConfig.getInstance();
    File soundsFile=cfg.getFile(DataFiles.SOUNDS);
    long now=System.currentTimeMillis();
    SoundsManager mgr=new SoundsManager();
    List<SoundDescription> sounds=new SoundsXMLParser().parseXML(soundsFile);
    for(SoundDescription sound : sounds)
    {
      mgr.registerSound(sound);
    }
    long now2=System.currentTimeMillis();
    long duration=now2-now;
    int nbSounds=mgr.getAllSounds().size();
    LOGGER.info("Loaded "+nbSounds+" sounds in "+duration+"ms.");
    return mgr;
  }

  /**
   * Register a sound.
   * @param sound Sound to register.
   */
  public void registerSound(SoundDescription sound)
  {
    Integer key=Integer.valueOf(sound.getIdentifier());
    _sounds.put(key,sound);
  }

  /**
   * Get a sound using its identifier.
   * @param soundID Identifier of the sound to get.
   * @return A sound or <code>null</code> if not found.
   */
  public SoundDescription getSound(int soundID)
  {
    return _sounds.get(Integer.valueOf(soundID));
  }

  /**
   * Get all sounds.
   * @return A list of sounds, sorted by identifier.
   */
  public List<SoundDescription> getAllSounds()
  {
    List<SoundDescription> ret=new ArrayList<SoundDescription>();
    ret.addAll(_sounds.values());
    Collections.sort(ret,new IdentifiableComparator<SoundDescription>());
    return ret;
  }
}
