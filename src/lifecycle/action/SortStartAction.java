package lifecycle.action;

import com.intellij.openapi.actionSystem.AnActionEvent;

import lifecycle.Sorter;


/**
 * Created by akopian on 5/24/15.
 */
public class SortStartAction extends SortAction
{
    @Override
    public void actionPerformed(AnActionEvent e)
    {
        mSortPosition = Sorter.SortPosition.START;

        super.actionPerformed(e);
    }
}
