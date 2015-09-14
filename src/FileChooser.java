import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileChooser extends JFrame implements ActionListener {
    JPanel panel1;
    JButton openFileButton;
    JTextArea textArea;
    JFileChooser fileChoser;
    JScrollPane scroll;

    public File[] files;

    public FileChooser(){
        super("FileChooserApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        initPanel();
        add(panel1);
        setVisible(true);
    }

    private void initPanel() {
        panel1 = new JPanel(new BorderLayout());

        if (textArea==null){
            textArea = new JTextArea(20, 20);
            textArea.setEditable(false);
            scroll = new JScrollPane (textArea);
        }

        if (fileChoser==null){
            fileChoser = new JFileChooser();
            fileChoser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            FileFilter filter = new FileFilter() {

                @Override
                public boolean accept(File file) {
                    if (file.isDirectory()) return true;
                    if (file.getAbsolutePath().toLowerCase().endsWith(".txt")) {
                        return true;
                    } else {
                        return false;
                    }
                }

                @Override
                public String getDescription() {
                    // TODO Auto-generated method stub
                    return "Text files";
                }
            };
            fileChoser.setFileFilter(filter);
        }

        if (openFileButton==null){
            openFileButton = new JButton("Open");
            openFileButton.addActionListener(this);
        }
        panel1.add(openFileButton, BorderLayout.SOUTH);
        panel1.add(scroll, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == openFileButton) {
            fileChoser.setMultiSelectionEnabled(true);
            int returnValue = fileChoser.showOpenDialog(FileChooser.this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                files = fileChoser.getSelectedFiles();
                textArea.setCaretPosition(textArea.getDocument().getLength());

                for (int i = 0; i < files.length; i++) {
                    //System.out.println(files[i].getName());
                    textArea.append(i+1 + ". " + files[i].getName()+ ";" + '\n');
                }
            }
        }

    }
}
