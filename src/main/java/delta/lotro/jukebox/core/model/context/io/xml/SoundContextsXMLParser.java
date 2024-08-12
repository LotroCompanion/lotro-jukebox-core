package delta.lotro.jukebox.core.model.context.io.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import delta.common.utils.xml.DOMParsingTools;
import delta.lotro.jukebox.core.model.base.SoundDescription;
import delta.lotro.jukebox.core.model.base.SoundsManager;
import delta.lotro.jukebox.core.model.context.SoundContext;
import delta.lotro.jukebox.core.model.context.SoundReference;
import delta.lotro.jukebox.core.model.context.SoundReferences;

/**
 * Parser for sounds stored in XML.
 * @author DAM
 */
public class SoundContextsXMLParser
{
  private String _contextTag;

  /**
   * Constructor.
   * @param contextTag Context tag.
   */
  public SoundContextsXMLParser(String contextTag)
  {
    _contextTag=contextTag;
  }

  /**
   * Parse the XML file.
   * @param source Source file.
   * @return Parsed sound contexts.
   */
  public List<SoundContext> parseXML(File source)
  {
    List<SoundContext> ret=new ArrayList<SoundContext>();
    Element root=DOMParsingTools.parse(source);
    if (root!=null)
    {
      List<Element> contextTags=DOMParsingTools.getChildTagsByName(root,_contextTag);
      for(Element contextTag : contextTags)
      {
        SoundContext sound=parseSoundContext(contextTag);
        ret.add(sound);
      }
    }
    return ret;
  }

  private SoundContext parseSoundContext(Element root)
  {
    NamedNodeMap attrs=root.getAttributes();
    // Identifier
    int id=DOMParsingTools.getIntAttribute(attrs,SoundContextsXMLConstants.CONTEXT_ID_ATTR,0);
    // Name
    String name=DOMParsingTools.getStringAttribute(attrs,SoundContextsXMLConstants.CONTEXT_NAME_ATTR,"");
    // Icon
    String icon=DOMParsingTools.getStringAttribute(attrs,SoundContextsXMLConstants.CONTEXT_ICON_ATTR,"");
    SoundContext context=new SoundContext(id,name,icon);

    // Sound references
    SoundReferences references=context.getSounds();
    List<Element> referenceTags=DOMParsingTools.getChildTagsByName(root,SoundContextsXMLConstants.SOUND_TAG);
    for(Element referenceTag : referenceTags)
    {
      NamedNodeMap referenceAttrs=referenceTag.getAttributes();
      // Identifier
      int soundID=DOMParsingTools.getIntAttribute(referenceAttrs,SoundContextsXMLConstants.SOUND_ID_ATTR,0);
      SoundDescription sound=SoundsManager.getInstance().getSound(soundID);
      if (sound!=null)
      {
        SoundReference reference=new SoundReference(soundID);
        reference.setSound(sound);
        references.addSoundReference(reference);
      }
    }
    return context;
  }
}
