package delta.lotro.jukebox.core.model.filter;

import delta.common.utils.collections.filters.Filter;
import delta.lotro.jukebox.core.model.SoundDescription;
import delta.lotro.jukebox.core.model.SoundFormat;

/**
 * Filter for sound of a given format.
 * @author DAM
 */
public class SoundFormatFilter implements Filter<SoundDescription>
{
  private SoundFormat _format;

  /**
   * Constructor.
   * @param format Format to select (may be <code>null</code>).
   */
  public SoundFormatFilter(SoundFormat format)
  {
    _format=format;
  }

  /**
   * Get the format to use.
   * @return A format or <code>null</code>.
   */
  public SoundFormat getFormat()
  {
    return _format;
  }

  /**
   * Set the format to select.
   * @param format Format to use, may be <code>null</code>.
   */
  public void setFormat(SoundFormat format)
  {
    _format=format;
  }

  @Override
  public boolean accept(SoundDescription sound)
  {
    if (_format==null)
    {
      return true;
    }
    return sound.getFormat()==_format;
  }
}
