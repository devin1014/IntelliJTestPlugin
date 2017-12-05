package utils;

/**
 * User: NeuLion(wei.liu@neulion.com.com)
 * Date: 2017-12-04
 * Time: 15:49
 */
public class LogUtil
{
    public static final boolean DEBUG = true;

    public static void d(String output)
    {
        if (DEBUG)
        {
            System.out.println(output);
        }
    }

    public static void e(Exception e)
    {
        e.printStackTrace();
    }
}
