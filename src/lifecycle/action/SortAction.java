package lifecycle.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;

import lifecycle.Sorter;


/**
 * Created by armand on 3/1/15.
 */
@SuppressWarnings("ComponentNotRegistered")
public class SortAction extends AnAction
{
    protected Sorter.SortPosition mSortPosition;

    @Override
    public void actionPerformed(AnActionEvent e)
    {
        Messages.showInfoMessage("message", "title");

        PsiClass psiClass = getPsiClassFromContext(e);

        if (psiClass != null)
        {
            sortLifecycleMethods(psiClass);
        }
    }

    private void sortLifecycleMethods(final PsiClass psiClass)
    {
        new WriteCommandAction.Simple(psiClass.getProject(), psiClass.getContainingFile())
        {
            @Override
            protected void run() throws Throwable
            {
                new Sorter(psiClass, mSortPosition).sort();
            }
        }.execute();
    }

    /**
     * @param e the action event that occurred
     * @return The PSIClass object based on which class your mouse cursor was in
     */
    private PsiClass getPsiClassFromContext(AnActionEvent e)
    {
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);

        Editor editor = e.getData(PlatformDataKeys.EDITOR);

        if (psiFile == null || editor == null)
        {
            return null;
        }

        int offSet = editor.getCaretModel().getOffset();

        PsiElement elementAt = psiFile.findElementAt(offSet);

        return PsiTreeUtil.getParentOfType(elementAt, PsiClass.class);
    }
}
