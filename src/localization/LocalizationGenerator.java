package localization;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import kotlin.Pair;
import utils.ActionCompat;
import utils.LogUtil;

/**
 * User: NeuLion(wei.liu@neulion.com.com)
 * Date: 2017-12-04
 * Time: 16:20
 */
class LocalizationGenerator
{
    private static final String LOCALIZATION_NAME = "NLLocalizationKeys.java";

    private static final String FILE_SUFFIX = "java";

    private Project mProject;

    LocalizationGenerator(Project project)
    {
        mProject = project;
    }

    void generate(VirtualFile virtualFile, InputStream inputStream)
    {
        final VirtualFile directoryVirtualFile = virtualFile.isDirectory() ? virtualFile : virtualFile.getParent();

        LogUtil.d("try to generate " + LOCALIZATION_NAME + " in \"" + directoryVirtualFile.getPath() + "\"");

        final List<Pair<String, String>> list = parseInputStream(inputStream);

        ActionCompat.runWriteCommand(mProject, () -> {

            VirtualFile localizationFile = directoryVirtualFile.findChild(LOCALIZATION_NAME);

            try
            {
                if (localizationFile == null || !localizationFile.exists())
                {
                    localizationFile = directoryVirtualFile.createChildData(null, LOCALIZATION_NAME);

                    LogUtil.d("create file.");
                }

                localizationFile.setBinaryContent(createJavaFile(localizationFile, list).getBytes(), System.currentTimeMillis(), System.currentTimeMillis());

                LogUtil.d("reset file content.");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });

        Messages.showInfoMessage(String.format(GENERATOR_SUCCESS, list.size()), "Generator Success");
    }

    private static final String GENERATOR_SUCCESS = "成功生成 %d 个Key";

    private List<Pair<String, String>> parseInputStream(InputStream inputStream)
    {
        List<Pair<String, String>> resultList = new LinkedList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try
        {
            String s = reader.readLine();

            while (s != null)
            {
                if (s.length() > 0 && s.charAt(0) != '#' && s.contains("="))
                {
                    String[] result = s.split("=");

                    String key = result[0].trim();

                    String javaKey = key.replaceAll("\\.", "_").toUpperCase(Locale.US);

                    //String value = result[1];

                    resultList.add(new Pair<>(javaKey, key));
                }

                s = reader.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return resultList;
    }

    private String createJavaFile(VirtualFile virtualFile, List<Pair<String, String>> list)
    {
        StringBuilder builder = new StringBuilder();

        //package
        builder.append(addPackage(virtualFile));

        //class
        builder.append(addClass(virtualFile.getName()));

        //field
        for (Pair<String, String> keyValue : list)
        {
            builder.append(addField(keyValue));
        }

        //end
        builder.append("}\n");

        return builder.toString();
    }

    private static final String PACKAGE = "package %s;\n\n";

    private String addPackage(VirtualFile virtualFile)
    {
        String path = virtualFile.getPath().replaceAll("/", ".");

        int startIndex = path.indexOf(FILE_SUFFIX) + FILE_SUFFIX.length() + 1;

        int endIndex = path.indexOf(LOCALIZATION_NAME) - 1;

        path = path.substring(startIndex, endIndex);

        return String.format(PACKAGE, path);
    }

    private static final String CLASS = "public class %s {\n";

    private String addClass(String name)
    {
        if (name.contains("."))
        {
            return String.format(CLASS, name.substring(0, name.indexOf(".")));
        }

        return String.format(CLASS, name);
    }

    private static final String FIELD = "\tpublic static final String %s = \"%s\";\n";

    private String addField(Pair<String, String> pair)
    {
        return String.format(FIELD, pair.getFirst(), pair.getSecond());
    }
}
