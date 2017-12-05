package localization;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;

/**
 * User: NeuLion(wei.liu@neulion.com.com)
 * Date: 2017-11-30
 * Time: 14:52
 */
public class LocalizationAnAction extends AnAction
{
    private LocalizationGenerator mGenerator;

    @Override
    public void actionPerformed(AnActionEvent e)
    {
        Project project = PlatformDataKeys.PROJECT.getData(e.getDataContext());

        VirtualFile virtualFile = LangDataKeys.VIRTUAL_FILE.getData(e.getDataContext());

        if (project != null)
        {
            try
            {
                VirtualFile localizationFile = FileUtil.getVirtualFile(project.getBaseDir(), "app\\libs", "localization.string");

                if (mGenerator == null)
                {
                    mGenerator = new LocalizationGenerator(project);
                }

                mGenerator.generate(virtualFile, localizationFile.getInputStream());
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }

}
