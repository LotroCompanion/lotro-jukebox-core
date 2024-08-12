package delta.lotro.jukebox.core.model.context;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import delta.lotro.jukebox.core.config.LotroJukeboxCoreConfig;
import delta.lotro.jukebox.core.model.context.io.xml.SoundContextsXMLParser;

/**
 * Facade to access to sound contexts.
 * @author DAM
 */
public class SoundContextsFacade
{
  private static final Logger LOGGER=LoggerFactory.getLogger(SoundContextsFacade.class);

  private static SoundContextsFacade _instance=null;

  private Map<String,SoundContextsManager> _mgrsMap;

  /**
   * Get the sole instance of this class.
   * @return the sole instance of this class.
   */
  public static SoundContextsFacade getInstance()
  {
    if (_instance==null)
    {
      _instance=new SoundContextsFacade();
    }
    return _instance;
  }

  private SoundContextsFacade()
  {
    _mgrsMap=new HashMap<String,SoundContextsManager>();
  }

  /**
   * Get a context using its identifying key.
   * @param contextKey Identifying key.
   * @return A context.
   */
  public SoundContextsManager getContext(String contextKey)
  {
    SoundContextsManager mgr=_mgrsMap.get(contextKey);
    if (mgr==null)
    {
      mgr=loadContext(contextKey);
      _mgrsMap.put(contextKey,mgr);
    }
    return mgr;
  }

  private SoundContextsManager loadContext(String contextKey)
  {
    SoundContextsManager mgr=new SoundContextsManager();
    File file=LotroJukeboxCoreConfig.getInstance().getFile(contextKey);
    if (file.exists())
    {
      return load(file);
    }
    return mgr;
  }

  /**
   * Load data from a file.
   * @param from File to read from.
   * @return the loaded data.
   */
  private static SoundContextsManager load(File from)
  {
    long now=System.currentTimeMillis();
    SoundContextsManager mgr=new SoundContextsManager();
    List<SoundContext> soundContexts=new SoundContextsXMLParser().parseXML(from);
    for(SoundContext soundContext : soundContexts)
    {
      mgr.registerSoundContext(soundContext);
    }
    long now2=System.currentTimeMillis();
    long duration=now2-now;
    int nbContexts=mgr.getAllSoundContexts().size();
    LOGGER.info("Loaded {} contexts in {}ms.", Integer.valueOf(nbContexts), Long.valueOf(duration));
    return mgr;
  }
}
