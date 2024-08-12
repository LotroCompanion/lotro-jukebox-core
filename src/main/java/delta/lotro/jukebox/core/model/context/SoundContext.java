package delta.lotro.jukebox.core.model.context;

import delta.common.utils.id.Identifiable;

/**
 * Description of a sound context (area, dungeon, music item...).
 * @author DAM
 */
public class SoundContext implements Identifiable
{
  private int _id;
  private String _name;
  private String _icon;
  private SoundReferences _sounds;

  /**
   * Constructor.
   * @param id Item identifier.
   * @param name Item name.
   * @param icon Item icon.
   */
  public SoundContext(int id, String name, String icon)
  {
    _id=id;
    _name=name;
    _icon=icon;
    _sounds=new SoundReferences();
  }

  @Override
  public int getIdentifier()
  {
    return _id;
  }

  /**
   * Get the item name.
   * @return the item name.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Get the item icon.
   * @return the icon.
   */
  public String getIcon()
  {
    return _icon;
  }

  /**
   * Get the sound references.
   * @return the sound references.
   */
  public SoundReferences getSounds()
  {
    return _sounds;
  }
}
