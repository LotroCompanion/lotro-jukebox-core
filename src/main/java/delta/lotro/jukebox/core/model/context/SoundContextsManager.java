package delta.lotro.jukebox.core.model.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import delta.common.utils.id.IdentifiableComparator;

/**
 * Sound contexts manager.
 * @author DAM
 */
public class SoundContextsManager
{
  private Map<Integer,SoundContext> _soundContexts;

  /**
   * Constructor.
   */
  public SoundContextsManager()
  {
    _soundContexts=new HashMap<Integer,SoundContext>();
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
