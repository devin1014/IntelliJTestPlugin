package foldingplugin;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class Settings
{
    @SerializedName("DecomposedFolders")
    private List<String> mComposedFolders;

    @NotNull
    List<String> getComposedFolders()
    {
        if (mComposedFolders == null)
        {
            mComposedFolders = new ArrayList<>();
        }

        return mComposedFolders;
    }
}
