package delta.lotro.jukebox.core.model.context.io.xml;

import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.xml.transform.sax.TransformerHandler;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import delta.common.utils.id.IdentifiableComparator;
import delta.common.utils.io.xml.XmlFileWriterHelper;
import delta.common.utils.io.xml.XmlWriter;
import delta.common.utils.text.EncodingNames;
import delta.lotro.jukebox.core.model.base.SoundDescription;
import delta.lotro.jukebox.core.model.context.SoundContext;
import delta.lotro.jukebox.core.model.context.SoundReference;
import delta.lotro.jukebox.core.model.context.SoundReferences;

/**
 * Writes sound contexts to XML files.
 * @author DAM
 */
public class SoundContextsXMLWriter
{
  private String _contextTag;

  /**
   * Constructor.
   * @param contextTag Context tag.
   */
  public SoundContextsXMLWriter(String contextTag)
  {
    _contextTag=contextTag;
  }

  /**
   * Write a file with sound contexts.
   * @param toFile Output file.
   * @param soundContexts Data to write.
   * @return <code>true</code> if it succeeds, <code>false</code> otherwise.
   */
  public boolean writeSoundContextsFile(File toFile, List<SoundContext> soundContexts)
  {
    Collections.sort(soundContexts,new IdentifiableComparator<SoundContext>());
    boolean ok=writeSoundContexts(toFile,soundContexts,EncodingNames.UTF_8);
    return ok;
  }

  /**
   * Write sound contexts to a XML file.
   * @param outFile Output file.
   * @param soundContexts Data to write.
   * @param encoding Encoding to use.
   * @return <code>true</code> if it succeeds, <code>false</code> otherwise.
   */
  private boolean writeSoundContexts(File outFile, final List<SoundContext> soundContexts, String encoding)
  {
    XmlFileWriterHelper helper=new XmlFileWriterHelper();
    XmlWriter writer=new XmlWriter()
    {
      @Override
      public void writeXml(TransformerHandler hd) throws Exception
      {
        writeSoundContexts(hd,soundContexts);
      }
    };
    boolean ret=helper.write(outFile,encoding,writer);
    return ret;
  }

  private void writeSoundContexts(TransformerHandler hd, List<SoundContext> soundContexts) throws SAXException
  {
    hd.startElement("","",SoundContextsXMLConstants.SOUND_CONTEXTS_TAG,new AttributesImpl());
    for(SoundContext soundContext : soundContexts)
    {
      writeSoundContext(hd,soundContext);
    }
    hd.endElement("","",SoundContextsXMLConstants.SOUND_CONTEXTS_TAG);
  }

  private void writeSoundContext(TransformerHandler hd, SoundContext soundContext) throws SAXException
  {
    AttributesImpl attrs=new AttributesImpl();
    // Identifier
    int id=soundContext.getIdentifier();
    attrs.addAttribute("","",SoundContextsXMLConstants.CONTEXT_ID_ATTR,XmlWriter.CDATA,String.valueOf(id));
    // Name
    String name=soundContext.getName();
    attrs.addAttribute("","",SoundContextsXMLConstants.CONTEXT_NAME_ATTR,XmlWriter.CDATA,name);
    // Icon
    String icon=soundContext.getIcon();
    if (icon!=null)
    {
      attrs.addAttribute("","",SoundContextsXMLConstants.CONTEXT_ICON_ATTR,XmlWriter.CDATA,icon);
    }
    hd.startElement("","",_contextTag,attrs);
    // Sound references
    SoundReferences references=soundContext.getSounds();
    writeSoundReferences(hd,references);
    hd.endElement("","",_contextTag);
  }

  private void writeSoundReferences(TransformerHandler hd, SoundReferences references) throws SAXException
  {
    AttributesImpl attrs=new AttributesImpl();
    for(SoundReference reference : references.getSoundReferences())
    {
      // Identifier
      int id=reference.getIdentifier();
      attrs.addAttribute("","",SoundContextsXMLConstants.CONTEXT_ID_ATTR,XmlWriter.CDATA,String.valueOf(id));
      // Name
      SoundDescription sound=reference.getSound();
      if (sound!=null)
      {
        String name=sound.getName();
        attrs.addAttribute("","",SoundContextsXMLConstants.CONTEXT_NAME_ATTR,XmlWriter.CDATA,name);
      }
      hd.startElement("","",SoundContextsXMLConstants.SOUND_TAG,attrs);
      hd.endElement("","",SoundContextsXMLConstants.SOUND_TAG);
    }
  }
}
