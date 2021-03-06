package stylecreator;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.ui.MessageType;

import org.jetbrains.annotations.Nullable;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Created by Aleksandr on 24.09.2015.
 */
public class PasteAction extends EditorAction
{
    @SuppressWarnings("unused")
    public PasteAction()
    {
        super(new StylePasteHandler());
    }

    //    private PasteAction(EditorActionHandler defaultHandler)
    //    {
    //        super(defaultHandler);
    //    }

    private static class StylePasteHandler extends EditorWriteActionHandler
    {
        private StylePasteHandler()
        {
        }

        @Override
        public void doExecute(Editor editor, @Nullable Caret caret, DataContext dataContext)
        {
            super.doExecute(editor, caret, dataContext);
        }

        @Override
        public void executeWriteAction(Editor editor, @Nullable Caret caret, DataContext dataContext)
        {
            Document document = editor.getDocument();

            if (!document.isWritable())
            {
                return;
            }

            // get text from clipboard
            String source = getCopiedText();

            if (source == null)
            {
                StylerUtils.showBalloonPopup(dataContext, Constants.ERROR_CLIPBOARD, MessageType.ERROR);

                return;
            }

            // get result
            try
            {
                // String styleName = getStyleName();
                //FIXME!!!
                String styleName = "test_style";

                if (styleName == null || styleName.isEmpty())
                {
                    StylerUtils.showBalloonPopup(dataContext, Constants.ERROR_NAME, MessageType.ERROR);
                    return;
                }
                String output = StylerEngine.style(styleName, source);
                // delete text that is selected now
                deleteSelectedText(editor, document);

                CaretModel caretModel = editor.getCaretModel();
                // insert new duplicated string into the document
                document.insertString(caretModel.getOffset(), output);
                // move caret to the end of inserted text
                caretModel.moveToOffset(caretModel.getOffset() + output.length());
                // scroll to the end of inserted text
                editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
            }
            catch (ParserConfigurationException | TransformerException e)
            {
                e.printStackTrace();

                StylerUtils.showBalloonPopup(dataContext, Constants.XML_ERROR, MessageType.ERROR);
            }
            catch (Exception e)
            {
                e.printStackTrace();

                StylerUtils.showBalloonPopup(dataContext, Constants.WRONG_INPUT, MessageType.ERROR);
            }
        }

        private static String getStyleName()
        {
            return (String) JOptionPane.showInputDialog(
                    new JFrame(), Constants.DIALOG_NAME_CONTENT, Constants.DIALOG_NAME_TITLE, JOptionPane.PLAIN_MESSAGE,
                    null, null, "");
        }

        private String getCopiedText()
        {
            try
            {
                return (String) CopyPasteManager.getInstance().getContents().getTransferData(DataFlavor.stringFlavor);
            }
            catch (NullPointerException | IOException | UnsupportedFlavorException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        private void deleteSelectedText(Editor editor, Document document)
        {
            SelectionModel selectionModel = editor.getSelectionModel();

            document.deleteString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd());
        }
    }
}
