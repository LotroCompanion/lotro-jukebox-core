package delta.lotro.jukebox.core.model.base.io.xml;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.xml.transform.sax.TransformerHandler;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import delta.common.utils.id.IdentifiableComparator;
import delta.common.utils.io.xml.XmlFileWriterHelper;
import delta.common.utils.io.xml.XmlWriter;
import delta.common.utils.text.EncodingNames;
import delta.lotro.jukebox.core.model.base.SoundDescription;
import delta.lotro.jukebox.core.model.base.SoundFormat;
import delta.lotro.jukebox.core.model.base.SoundType;

/**
 * Writes sounds to XML files.
 * @author DAM
 */
public class SoundsXMLWriter
{
  /**
   * Write a file with sounds.
   * @param toFile Output file.
   * @param sounds Data to write.
   * @return <code>true</code> if it succeeds, <code>false</code> otherwise.
   */
  public static boolean writeSoundsFile(File toFile, List<SoundDescription> sounds)
  {
    SoundsXMLWriter writer=new SoundsXMLWriter();
    Collections.sort(sounds,new IdentifiableComparator<SoundDescription>());
    boolean ok=writer.writeSounds(toFile,sounds,EncodingNames.UTF_8);
    return ok;
  }

  /**
   * Write sounds to a XML file.
   * @param outFile Output file.
   * @param sounds Data to write.
   * @param encoding Encoding to use.
   * @return <code>true</code> if it succeeds, <code>false</code> otherwise.
   */
  public boolean writeSounds(File outFile, final List<SoundDescription> sounds, String encoding)
  {
    XmlFileWriterHelper helper=new XmlFileWriterHelper();
    XmlWriter writer=new XmlWriter()
    {
      @Override
      public void writeXml(TransformerHandler hd) throws Exception
      {
        writeSounds(hd,sounds);
      }
    };
    boolean ret=helper.write(outFile,encoding,writer);
    return ret;
  }

  private void writeSounds(TransformerHandler hd, List<SoundDescription> sounds) throws SAXException
  {
    hd.startElement("","",SoundsXMLConstants.SOUNDS_TAG,new AttributesImpl());
    for(SoundDescription sound : sounds)
    {
      writeSound(hd,sound);
    }
    hd.endElement("","",SoundsXMLConstants.SOUNDS_TAG);
  }

  private void writeSound(TransformerHandler hd, SoundDescription sound) throws SAXException
  {
    AttributesImpl attrs=new AttributesImpl();

    // In-game identifier
    int id=sound.getIdentifier();
    attrs.addAttribute("","",SoundsXMLConstants.SOUND_ID_ATTR,XmlWriter.CDATA,String.valueOf(id));
    // Name
    String name=sound.getName();
    attrs.addAttribute("","",SoundsXMLConstants.SOUND_NAME_ATTR,XmlWriter.CDATA,name);
    // Date
    long date=sound.getTimestamp();
    attrs.addAttribute("","",SoundsXMLConstants.SOUND_TIMESTAMP_ATTR,XmlWriter.CDATA,String.valueOf(date));
    // Format
    SoundFormat format=sound.getFormat();
    attrs.addAttribute("","",SoundsXMLConstants.SOUND_FORMAT_ATTR,XmlWriter.CDATA,format.name());
    // Size
    long size=sound.getRawSize();
    attrs.addAttribute("","",SoundsXMLConstants.SOUND_SIZE_ATTR,XmlWriter.CDATA,String.valueOf(size));
    // Duration
    int duration=sound.getDuration();
    attrs.addAttribute("","",SoundsXMLConstants.SOUND_DURATION_ATTR,XmlWriter.CDATA,String.valueOf(duration));
    // Sample rate
    float sampleRate=sound.getSampleRate();
    attrs.addAttribute("","",SoundsXMLConstants.SOUND_SAMPLE_RATE_ATTR,XmlWriter.CDATA,String.valueOf(sampleRate));
    // Type
    String types=getSoundTypesStr(sound.getTypes());
    attrs.addAttribute("","",SoundsXMLConstants.SOUND_TYPE_ATTR,XmlWriter.CDATA,types);
    hd.startElement("","",SoundsXMLConstants.SOUND_TAG,attrs);
    hd.endElement("","",SoundsXMLConstants.SOUND_TAG);
  }

  private String getSoundTypesStr(Set<SoundType> types)
  {
    if (types.isEmpty())
    {
      return "";
    }
    StringBuilder sb=new StringBuilder();
    for(SoundType type : SoundType.values())
    {
      if (types.contains(type))
      {
        if (sb.length()>0)
        {
          sb.append(',');
        }
        sb.append(type.name());
      }
    }
    return sb.toString();
  }
}
