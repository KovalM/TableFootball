package chooser.choose;

import chooser.FileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class EnterListener implements ActionListener {
    private FileChooser fileChooser;
    private ChooseFilePanel chooseFilePanel;

    public EnterListener(FileChooser fileChooser,ChooseFilePanel chooseFilePanel){
        this.fileChooser = fileChooser;
        this.chooseFilePanel = chooseFilePanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = chooseFilePanel.getConsistDirectoryPanel().getCurrentDirectory() +
                "\\"+
                chooseFilePanel.getSelectedFile();
        if (!chooseFilePanel.checkFile(name)){
            name += chooseFilePanel.getFormat();
        }
        File selectFile = new File(name);
        System.out.println(selectFile.exists());
        if (!selectFile.exists() && fileChooser.getMode() == ChooserConst.SAVE_MODE){
            try {
                selectFile.createNewFile();
                fileChooser.setSelectedFlag(true);
                fileChooser.setSelectedFile(selectFile);
                fileChooser.closeDialog();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (selectFile.exists()){
            fileChooser.setSelectedFlag(true);
            fileChooser.setSelectedFile(selectFile);
            fileChooser.closeDialog();
        }

    }
}
