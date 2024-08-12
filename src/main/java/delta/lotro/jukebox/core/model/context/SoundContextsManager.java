package delta.lotro.jukebox.core.model.context;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import delta.common.utils.id.IdentifiableComparator;
import delta.lotro.jukebox.core.model.context.io.xml.SoundContextsXMLParser;

/**
 * Sound contexts manager.
 * @author DAM
 */
public class SoundContextsManager
{
  private static final Logger LOGGER=LoggerFactory.getLogger(SoundContextsManager.class);

  private Map<Integer,SoundContext> _soundContexts;

  /**
   * Constructor.
   */
  public SoundContextsManager()
  {
    _soundContexts=new HashMap<Integer,SoundContext>();
  }

  /**
   * Load data from a file.
   * @param from File to read from.
   * @param contextTag Context tag to use.
   * @return the loaded data.
   */
  public static SoundContextsManager load(File from, String contextTag)
  {
    long now=System.currentTimeMillis();
    SoundContextsManager mgr=new SoundContextsManager();
    List<SoundContext> soundContexts=new SoundContextsXMLParser(contextTag).parseXML(from);
    for(SoundContext soundContext : soundContexts)
    {
      mgr.registerSoundContext(soundContext);
    }
    long now2=System.currentTimeMillis();
    long duration=now2-now;
    int nbContexts=mgr.getAllSoundContexts().size();
    LOGGER.info("Loaded "+nbContexts+" contexts in "+duration+"ms.");
    return mgr;
  }

  /**
   * Register a sound context.
   * @param soundContext Sound context to register.
   */
  public void registerSoundContext(SoundContext soundContext)
  {
    Integer key=Integer.valueOf(soundContext.getIdentifier());
    _soundContexts.put(key,soundContext);
  }

  /**
   * Get a sound context using its identifier.
   * @param contextID Identifier of the context to get.
   * @return A sound context or <code>null</code> if not found.
   */
  public SoundContext getSoundContext(int contextID)
  {
    return _soundContexts.get(Integer.valueOf(contextID));
  }

  /**
   * Get all managed sound contexts.
   * @return A list of sound contexts, sorted by identifier.
   */
  public List<SoundContext> getAllSoundContexts()
  {
    List<SoundContext> ret=new ArrayList<SoundContext>();
    ret.addAll(_soundContexts.values());
    Collections.sort(ret,new IdentifiableComparator<SoundContext>());
    return ret;
  }
}
