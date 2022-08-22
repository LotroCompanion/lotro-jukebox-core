package delta.lotro.jukebox.core.model;

import delta.common.utils.id.Identifiable;
import delta.lotro.jukebox.core.utils.Duration;

/**
 * Description of a sound entry.
 * @author DAM
 */
public class SoundDescription implements Identifiable
{
  private int _identifier;
  private String _name;
  private long _timestamp;
  private SoundFormat _format;
  private long _rawSize;
  private int _duration;
  private SoundType _type;

  /**
   * Constructor.
   * @param identifier Sound identifier.
   */
  public SoundDescription(int identifier)
  {
    _identifier=identifier;
    _name="";
    _timestamp=0;
    _format=SoundFormat.OGG_VORBIS;
    _rawSize=0;
    _duration=0;
    _type=SoundType.MUSIC;
  }

  /**
   * Get the sound identifier.
   * @return the identifier.
   */
  public int getIdentifier()
  {
    return _identifier;
  }

  /**
   * Get the sound name.
   * @return the sound name.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Set the sound name.
   * @param name the name to set.
   */
  public void setName(String name)
  {
    if (name==null)
    {
      name="";
    }
    _name=name;
  }

  /**
   * Get the timestamp of this sound.
   * @return a timestamp.
   */
  public long getTimestamp()
  {
    return _timestamp;
  }

  /**
   * Set the timestamp of this sound.
   * @param timestamp the timestamp to set.
   */
  public void setTimestamp(long timestamp)
  {
    _timestamp=timestamp;
  }

  /**
   * Get the format of this sound.
   * @return the format of this sound.
   */
  public SoundFormat getFormat()
  {
    return _format;
  }

  /**
   * Set the format of this sound.
   * @param format the format to set.
   */
  public void setFormat(SoundFormat format)
  {
    _format=format;
  }

  /**
   * Get the raw size for this sound.
   * @return a size in bytes.
   */
  public long getRawSize()
  {
    return _rawSize;
  }

  /**
   * Set the raw size for this sound.
   * @param rawSize the size to set (bytes).
   */
  public void setRawSize(long rawSize)
  {
    _rawSize=rawSize;
  }

  /**
   * Get the duration of this sound.
   * @return a duration (milliseconds).
   */
  public int getDuration()
  {
    return _duration;
  }

  /**
   * Set the duration of this sound.
   * @param duration the duration to set (milliseconds).
   */
  public void setDuration(int duration)
  {
    _duration=duration;
  }

  /**
   * Get the sound type.
   * @return a sound type.
   */
  public SoundType getType()
  {
    return _type;
  }

  /**
   * Set the sound type.
   * @param type the type to set.
   */
  public void setType(SoundType type)
  {
    _type=type;
  }

  @Override
  public String toString()
  {
    StringBuilder sb=new StringBuilder();
    sb.append("Sound: ID=").append(_identifier);
    if ((_name!=null) && (_name.length()>0))
    {
      sb.append(", name=").append(_name);
    }
    if (_type!=null)
    {
      sb.append(", type=").append(_type);
    }
    if (_duration!=0)
    {
      sb.append(", duration=").append(Duration.getDurationString(_duration));
    }
    return sb.toString();
  }
}
