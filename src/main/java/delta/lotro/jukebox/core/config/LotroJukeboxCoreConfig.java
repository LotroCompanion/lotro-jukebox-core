package delta.lotro.jukebox.core.config;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import delta.common.utils.io.StreamTools;
import delta.common.utils.misc.TypedProperties;
import delta.common.utils.url.URLTools;

/**
 * Configuration.
 * @author DAM
 */
public final class LotroJukeboxCoreConfig
{
  private static final Logger LOGGER=LoggerFactory.getLogger(LotroJukeboxCoreConfig.class);

  private static LotroJukeboxCoreConfig _instance=new LotroJukeboxCoreConfig();

  // Locations
  private TypedProperties _locations;

  // Root directory for user data
  private File _userDataDir;
  // Parameters
  private TypedProperties _parameters;

  /**
   * Get the sole instance of this class.
   * @return the sole instance of this class.
   */
  public static LotroJukeboxCoreConfig getInstance()
  {
    return _instance;
  }

  /**
   * Private constructor.
   */
  private LotroJukeboxCoreConfig()
  {
    _locations=getLocations();

    // Parameters
    File parametersFile=getFile(DataFiles.PARAMETERS);
    _parameters=new TypedProperties();
    if (parametersFile.canRead())
    {
      _parameters.loadFromFile(parametersFile);
    }

    // User data
    File userHomeDir=new File(System.getProperty("user.home"));
    File userApplicationDir=new File(userHomeDir,".lotrojukebox");
    _userDataDir=new File(userApplicationDir,"data");
  }

  private TypedProperties getLocations()
  {
    TypedProperties props=null;
    URL url=URLTools.getFromClassPath("locations-jukebox.properties", getClass().getClassLoader());
    InputStream is=null;
    try
    {
      is=url.openStream();
      props=new TypedProperties();
      props.loadFromInputStream(is);
    }
    catch(Throwable t)
    {
      LOGGER.error("Could not load locations!",t);
    }
    finally
    {
      StreamTools.close(is);
    }
    return props;
  }

  /**
   * Get a file path.
   * @param id Location identifier.
   * @return An absolute file or <code>null</code>.
   */
  public File getFile(String id)
  {
    String path=_locations.getStringProperty(id,null);
    return path!=null?new File(path).getAbsoluteFile():null;
  }

  /**
   * Get the configuration parameters.
   * @return the configuration parameters.
   */
  public TypedProperties getParameters()
  {
    return _parameters;
  }

  /**
   * Get the directory for user data.
   * @return the directory for user data.
   */
  public File getUserDataDir()
  {
    return _userDataDir;
  }
}
