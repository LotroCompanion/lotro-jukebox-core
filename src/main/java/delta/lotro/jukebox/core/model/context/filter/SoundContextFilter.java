package delta.lotro.jukebox.core.model.context.filter;

import java.util.HashSet;
import java.util.Set;

import delta.common.utils.collections.filters.Filter;
import delta.lotro.jukebox.core.model.base.SoundDescription;
import delta.lotro.jukebox.core.model.context.SoundContext;
import delta.lotro.jukebox.core.model.context.SoundContextsManager;
import delta.lotro.jukebox.core.model.context.SoundReference;

/**
 * Filter on sound context.
 * <p>
 * Accepts the sound description that belong to a given context ID.
 * @author DAM
 */
public class SoundContextFilter implements Filter<SoundDescription>
{
  private Set<Integer> _allowedIDs;
  private SoundContextsManager _mgr;
  private Integer _contextID;

  /**
   * Constructor.
   * @param mgr Contexts manager to use.
   */
  public SoundContextFilter(SoundContextsManager mgr)
  {
    _mgr=mgr;
    _contextID=null;
    _allowedIDs=new HashSet<Integer>();
  }

  /**
   * Get the selected context ID.
   * @return An ID or <code>null</code> to accept all.
   */
  public Integer getContextID()
  {
    return _contextID;
  }

  /**
   * Set the context ID.
   * @param contextID ID to set, <code>null</code> to accept all.
   */
  public void setContextID(Integer contextID)
  {
    _allowedIDs.clear();
    _contextID=contextID;
    if (contextID!=null)
    {
      SoundContext context=_mgr.getSoundContext(contextID.intValue());
      if (context!=null)
      {
        for(SoundReference reference : context.getSounds().getSoundReferences())
        {
          _allowedIDs.add(Integer.valueOf(reference.getIdentifier()));
        }
      }
    }
  }

  @Override
  public boolean accept(SoundDescription sound)
  {
    if (_contextID==null)
    {
      return true;
    }
    Integer key=Integer.valueOf(sound.getIdentifier());
    return _allowedIDs.contains(key);
  }
}
