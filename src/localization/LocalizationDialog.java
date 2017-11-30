package localization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * User: NeuLion(wei.liu@neulion.com.com)
 * Date: 2017-11-30
 * Time: 16:16
 */
class LocalizationDialog extends JDialog
{
    private JPanel mContentPanel;
    private JTextField mTextField1;
    private JTextField mTextField2;
    private JButton mButton1;

    public LocalizationDialog()
    {
        setContentPane(mContentPanel);

        mButton1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mTextField1.getText();
            }
        });
    }
}
