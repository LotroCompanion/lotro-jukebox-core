package delta.lotro.jukebox.core.model.context;

import delta.common.utils.id.Identifiable;
import delta.lotro.jukebox.core.model.base.SoundDescription;

/**
 * Sound reference.
 * @author DAM
 */
public class SoundReference implements Identifiable
{
  private int _soundID;
  private SoundDescription _sound;

  /**
   * Constructor.
   * @param soundID Sound identifier.
   */
  public SoundReference(int soundID)
  {
    _soundID=soundID;
  }

  @Override
  public int getIdentifier()
  {
    return _soundID;
  }

  /**
   * Get the managed sound.
   * @return a sound or <code>null</code> if not resolved (yet).
   */
  public SoundDescription getSound()
  {
    return _sound;
  }

  /**
   * Set the managed sound.
   * @param sound Sound to set.
   */
  public void setSound(SoundDescription sound)
  {
    _sound=sound;
  }
}
