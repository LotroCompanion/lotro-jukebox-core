package delta.lotro.jukebox.core.model.io.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import delta.common.utils.xml.DOMParsingTools;
import delta.lotro.jukebox.core.model.SoundDescription;
import delta.lotro.jukebox.core.model.SoundFormat;
import delta.lotro.jukebox.core.model.SoundType;

/**
 * Parser for sounds stored in XML.
 * @author DAM
 */
public class SoundsXMLParser
{
  /**
   * Parse the XML file.
   * @param source Source file.
   * @return Parsed sounds.
   */
  public List<SoundDescription> parseXML(File source)
  {
    List<SoundDescription> ret=new ArrayList<SoundDescription>();
    Element root=DOMParsingTools.parse(source);
    if (root!=null)
    {
      List<Element> soundTags=DOMParsingTools.getChildTagsByName(root,SoundsXMLConstants.SOUND_TAG);
      for(Element soundTag : soundTags)
      {
        SoundDescription sound=parseSound(soundTag);
        ret.add(sound);
      }
    }
    return ret;
  }

  private SoundDescription parseSound(Element root)
  {
    NamedNodeMap attrs=root.getAttributes();
    // Identifier
    int id=DOMParsingTools.getIntAttribute(attrs,SoundsXMLConstants.SOUND_ID_ATTR,0);
    SoundDescription sound=new SoundDescription(id);
    // Name
    String name=DOMParsingTools.getStringAttribute(attrs,SoundsXMLConstants.SOUND_NAME_ATTR,"");
    sound.setName(name);
    // Date
    long date=DOMParsingTools.getLongAttribute(attrs,SoundsXMLConstants.SOUND_TIMESTAMP_ATTR,0);
    sound.setTimestamp(date);
    // Format
    SoundFormat format=SoundFormat.OGG_VORBIS;
    String formatStr=DOMParsingTools.getStringAttribute(attrs,SoundsXMLConstants.SOUND_FORMAT_ATTR,null);
    if (formatStr!=null)
    {
      format=SoundFormat.valueOf(formatStr);
    }
    sound.setFormat(format);
    // Size
    long size=DOMParsingTools.getLongAttribute(attrs,SoundsXMLConstants.SOUND_SIZE_ATTR,0);
    sound.setRawSize(size);
    // Duration
    int duration=DOMParsingTools.getIntAttribute(attrs,SoundsXMLConstants.SOUND_SIZE_ATTR,0);
    sound.setDuration(duration);
    // Sample rate
    float sampleRate=DOMParsingTools.getFloatAttribute(attrs,SoundsXMLConstants.SOUND_SAMPLE_RATE_ATTR,0);
    sound.setSampleRate(sampleRate);
    // Type
    String typesStr=DOMParsingTools.getStringAttribute(attrs,SoundsXMLConstants.SOUND_TYPE_ATTR,"");
    if (typesStr.length()>0)
    {
      String[] typeStrs=typesStr.split(",");
      for(String typeStr : typeStrs)
      {
        SoundType type=SoundType.valueOf(typeStr);
        sound.addType(type);
      }
    }
    return sound;
  }
}
