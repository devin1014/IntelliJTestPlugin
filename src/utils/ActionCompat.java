package utils;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;

/**
 * User: NeuLion(wei.liu@neulion.com.com)
 * Date: 2017-12-04
 * Time: 16:34
 */
public class ActionCompat
{
    private ActionCompat()
    {
    }

    public static void runReadCommand(Project project, Runnable cmd)
    {
        CommandProcessor.getInstance().executeCommand(project, new ActionCompat.ReadAction(cmd), "Foo", "Bar");
    }

    public static void runWriteCommand(Project project, Runnable cmd)
    {
        CommandProcessor.getInstance().executeCommand(project, new ActionCompat.WriteAction(cmd), "Foo", "Bar");
    }

    // ---- Action
    static class ReadAction implements Runnable
    {
        Runnable cmd;

        ReadAction(Runnable cmd)
        {
            this.cmd = cmd;
        }

        public void run()
        {
            ApplicationManager.getApplication().runReadAction(cmd);
        }
    }

    static class WriteAction implements Runnable
    {
        Runnable cmd;

        WriteAction(Runnable cmd)
        {
            this.cmd = cmd;
        }

        public void run()
        {
            ApplicationManager.getApplication().runWriteAction(cmd);
        }
    }
}
