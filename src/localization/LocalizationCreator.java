package localization;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;

import java.io.IOException;

/**
 * User: NeuLion(wei.liu@neulion.com.com)
 * Date: 2017-11-30
 * Time: 14:52
 */
public class LocalizationCreator extends AnAction
{
    @Override
    public void actionPerformed(AnActionEvent e)
    {
        Project project = PlatformDataKeys.PROJECT.getData(e.getDataContext());

        printlnProject(project);

        Editor editor = PlatformDataKeys.EDITOR.getData(e.getDataContext());

        //        VirtualFile virtualFile = PlatformDataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        //
        //        VirtualFile writeableFile = null;
        //
        //        try
        //        {
        //            writeableFile = virtualFile.createChildData(this, "Test.java");
        //
        //            //writeableFile.setBinaryContent(type.getBinaryContent(packageName, name));
        //
        //            VirtualFileManager.getInstance().syncRefresh();
        //        }
        //        catch (IOException e1)
        //        {
        //            e1.printStackTrace();
        //        }
        //
        //        Module module = LangDataKeys.MODULE.getData(e.getDataContext());
    }

    private void printlnProject(Project project)
    {
        System.out.println("project: name=" + project.getName());
        System.out.println("project: basePath=" + project.getBasePath());
        System.out.println("project: projectFilePath" + project.getProjectFilePath());
    }

}
