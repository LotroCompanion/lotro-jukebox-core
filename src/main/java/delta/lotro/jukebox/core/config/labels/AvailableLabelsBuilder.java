package delta.lotro.jukebox.core.config.labels;

/**
 * Builds available labels.
 * @author DAM
 */
public class AvailableLabelsBuilder
{
  /**
   * Build the available labels for application.
   * @return A labels configuration.
   */
  public static AvailableLabelsDefinition buildAppLabelsConfiguration()
  {
    AvailableLabelsDefinition cfg=new AvailableLabelsDefinition();
    // English
    LabelsEntry en=new LabelsEntry("en","en","English");
    cfg.registerEntry(en,true);
    // French
    LabelsEntry fr=new LabelsEntry("fr","fr","Français");
    cfg.registerEntry(fr);
    return cfg;
  }
}
