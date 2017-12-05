package foldingplugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;

import org.jetbrains.annotations.Nullable;

final class Utils
{
    @Nullable
    static Project getCurrentProject()
    {
        Project[] openProjects = ProjectManager.getInstance().getOpenProjects();

        return openProjects.length > 0 ? openProjects[0] : null;
    }
}
