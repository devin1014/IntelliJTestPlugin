package localization;

import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;

/**
 * User: NeuLion(wei.liu@neulion.com.com)
 * Date: 2017-12-05
 * Time: 10:38
 */
class FileUtil
{
    // path: app/main/java/lib
    static VirtualFile getVirtualFile(VirtualFile rootFile, String path, String name) throws IOException
    {
        String[] pathArray = path.split("\\\\");

        VirtualFile directoryFile = getVirtualDirector(rootFile, pathArray);

        VirtualFile virtualFile = directoryFile.findChild(name);

        if (virtualFile == null || !virtualFile.exists())
        {
            virtualFile = directoryFile.createChildData(null, name);
        }

        return virtualFile;
    }

    private static VirtualFile getVirtualDirector(VirtualFile rootFile, String[] path)
    {
        VirtualFile virtualFile = rootFile;

        for (String p : path)
        {
            assert virtualFile != null;
            virtualFile = virtualFile.findChild(p);
        }

        return virtualFile;
    }
}
