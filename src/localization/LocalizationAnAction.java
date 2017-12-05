package localization;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;

import utils.LogUtil;

/**
 * User: NeuLion(wei.liu@neulion.com.com)
 * Date: 2017-11-30
 * Time: 14:52
 */
public class LocalizationAnAction extends AnAction
{
    private LocalizationGenerator mGenerator;

    @Override
    public void actionPerformed(AnActionEvent actionEvent)
    {
        Project project = PlatformDataKeys.PROJECT.getData(actionEvent.getDataContext());

        VirtualFile virtualFile = LangDataKeys.VIRTUAL_FILE.getData(actionEvent.getDataContext());

        if (project != null)
        {
            VirtualFile localizationFile = FileUtil.getVirtualFile(project.getBaseDir(), "app\\libs", "localization.string");

            if (localizationFile == null)
            {
                LogUtil.d("can not find file: " + "app\\libs\\localization.string");

                return;
            }

            if (mGenerator == null)
            {
                mGenerator = new LocalizationGenerator(project);
            }

            try
            {
                mGenerator.generate(virtualFile, localizationFile.getInputStream());
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void update(AnActionEvent e)
    {
        VirtualFile selectedFile = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());

        boolean available = selectedFile != null && selectedFile.getPath().contains("/src/main/java");

        e.getPresentation().setEnabled(available);
    }
}
