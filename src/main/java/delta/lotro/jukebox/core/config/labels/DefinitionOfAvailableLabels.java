package delta.lotro.jukebox.core.config.labels;

/**
 * Definition of available labels.
 * @author DAM
 */
public class DefinitionOfAvailableLabels
{
  private AvailableLabelsDefinition _appLabels;

  /**
   * Constructor.
   */
  public DefinitionOfAvailableLabels()
  {
    _appLabels=AvailableLabelsBuilder.buildAppLabelsConfiguration();
  }

  /**
   * Get the definition of available labels for application.
   * @return a labels definition.
   */
  public AvailableLabelsDefinition getAppLabels()
  {
    return _appLabels;
  }
}
