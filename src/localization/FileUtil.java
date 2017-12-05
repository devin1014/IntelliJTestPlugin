package localization;

import com.intellij.openapi.vfs.VirtualFile;

/**
 * User: NeuLion(wei.liu@neulion.com.com)
 * Date: 2017-12-05
 * Time: 10:38
 */
class FileUtil
{
    // path: app/main/java/lib
    static VirtualFile getVirtualFile(VirtualFile rootFile, String path, String name)
    {
        String[] pathArray = path.split("\\\\");

        VirtualFile directoryFile = getVirtualDirector(rootFile, pathArray);

        return directoryFile.findChild(name);
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
