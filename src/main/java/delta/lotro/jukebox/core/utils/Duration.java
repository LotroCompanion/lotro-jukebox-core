package delta.lotro.jukebox.core.utils;

/**
 * Utility methods related to duration. 
 * @author DAM
 */
public class Duration
{
  private static final int SECONDS_IN_HOUR=3600;
  private static final int SECONDS_IN_MINUTE=60;

  /**
   * Get a displayable for the given duration.
   * @param seconds A duration in seconds.
   * @return A displayable string.
   */
  public static String getDurationString(int seconds)
  {
    StringBuilder sb=new StringBuilder();
    int hour=0;
    if (seconds>=SECONDS_IN_HOUR)
    {
      hour=seconds/SECONDS_IN_HOUR;
      append2Digits(sb,hour);
      sb.append(':');
    }
    long minute=seconds-hour*SECONDS_IN_HOUR;
    if (minute>=SECONDS_IN_MINUTE)
    {
      minute=minute/SECONDS_IN_MINUTE;
      append2Digits(sb,minute);
    }
    else
    {
      sb.append("00");
    }
    sb.append(':');
    long second=seconds%SECONDS_IN_MINUTE;
    append2Digits(sb,second);
    return sb.toString();
  }

  private static void append2Digits(StringBuilder sb, long value)
  {
    if (value<10)
    {
      sb.append('0');
    }
    sb.append(value);
  }
}
