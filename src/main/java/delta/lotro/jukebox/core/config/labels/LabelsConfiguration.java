package delta.lotro.jukebox.core.config.labels;

import delta.common.utils.misc.Preferences;
import delta.common.utils.misc.TypedProperties;
import delta.lotro.jukebox.core.config.UserConfig;

/**
 * Configuration of the labels system.
 * @author DAM
 */
public class LabelsConfiguration
{
  // Labels
  private static final String LABELS_CONFIGURATION="Labels";
  private static final String APP_LABELS_KEY="AppLabelsKey";

  private String _appLabelsKey;

  /**
   * Constructor.
   */
  public LabelsConfiguration()
  {
    DefinitionOfAvailableLabels cfg=new DefinitionOfAvailableLabels();
    LabelsEntry defaultAppEntry=cfg.getAppLabels().getDefault();
    _appLabelsKey=defaultAppEntry.getKey();
  }

  /**
   * Get the key for application labels.
   * @return a key.
   */
  public String getAppLabelsKey()
  {
    return _appLabelsKey;
  }

  /**
   * Set the key for application labels.
   * @param appLabelsKey the key to use.
   */
  public void setAppLabelsKey(String appLabelsKey)
  {
    _appLabelsKey=appLabelsKey;
  }

  /**
   * Save configuration.
   * @param userCfg User configuration.
   */
  public void save(UserConfig userCfg)
  {
    userCfg.setStringValue(LABELS_CONFIGURATION,APP_LABELS_KEY,_appLabelsKey);
  }

  /**
   * Initialize from preferences.
   * @param preferences Preferences to use.
   */
  public void fromPreferences(Preferences preferences)
  {
    TypedProperties props=preferences.getPreferences(LABELS_CONFIGURATION);
    String appLabelsKey=props.getStringProperty(APP_LABELS_KEY,getAppLabelsKey());
    setAppLabelsKey(appLabelsKey);
  }
}
