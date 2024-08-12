package delta.lotro.jukebox.core.model.context;

import java.util.ArrayList;
import java.util.List;

/**
 * Set of sound references.
 * @author DAM
 */
public class SoundReferences
{
  private List<SoundReference> _references;

  /**
   * Constructor.
   */
  public SoundReferences()
  {
    _references=new ArrayList<SoundReference>();
  }

  /**
   * Add a sound reference.
   * @param soundReference Sound reference.
   */
  public void addSoundReference(SoundReference soundReference)
  {
    _references.add(soundReference);
  }

  /**
   * Get the managed sound references.
   * @return the managed sound references.
   */
  public List<SoundReference> getSoundReferences()
  {
    return _references;
  }
}
