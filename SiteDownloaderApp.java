import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileSystemView;
import java.io.IOException;

// Interface for the progress listener
interface ProgressListener {
    void onProgress(int progress);
}

public class SiteDownloaderApp {
    public static void main(String[] args) {
        JFrame App = new JFrame("SiteDownloader");

        // JLabels
        JLabel app_label1_JLabel = new JLabel("Ссылка на сайт:");
        app_label1_JLabel.setBounds(10, 10, 150, 10);

        JLabel app_label2_JLabel = new JLabel("Директория для сохранения:");
        app_label2_JLabel.setBounds(10, 50, 200, 10);

        JLabel app_label3_JLabel = new JLabel("Название папки:");
        app_label3_JLabel.setBounds(401, 50, 200, 10);
        
        JLabel app_label4_JLabel = new JLabel("Тип скачивания:");
        app_label4_JLabel.setBounds(10, 90, 200, 10);

        JLabel app_label5_JLabel = new JLabel("by @wwxriyy");
        app_label5_JLabel.setBounds(10, 240, 200, 10);

        // JtextFields
        JTextField site_link_JTextField = new JTextField();
        site_link_JTextField.setBounds(7, 25, 588, 20);

        JTextField download_path_JTextField = new JTextField();
        download_path_JTextField.setBounds(7, 65, 365, 20);

        JTextField folder_name_JTextField = new JTextField("Downloads");
        folder_name_JTextField.setBounds(397, 65, 199, 20);

        // JButtons
        JButton select_pach_JButton = new JButton("...");
        select_pach_JButton.setBounds(375, 65, 20, 20);

        JButton download_site_JButton = new JButton("Скачать");
        download_site_JButton.setBounds(495, 225, 100, 30);

        // JComboBox
        String download_type[] = {"Скачать сайт", "Скачать страницу"};
        JComboBox<String> download_type_JComboBox = new JComboBox<>(download_type);
        download_type_JComboBox.setBounds(6, 110, 200, 20);

        // JProgressBar
        JProgressBar download_progress_JProgressBar = new JProgressBar();
        download_progress_JProgressBar.setBounds(7, 235, 585, 50);

        // Add JLabels
        App.add(app_label1_JLabel);
        App.add(app_label2_JLabel);
        App.add(app_label3_JLabel);
        App.add(app_label4_JLabel);
        App.add(app_label5_JLabel);

        // Add JtextFields
        App.add(site_link_JTextField);
        App.add(download_path_JTextField);
        App.add(folder_name_JTextField);

        // Add JButtons
        App.add(select_pach_JButton);
        App.add(download_site_JButton);

        // Add JComboBox
        App.add(download_type_JComboBox);

        // Add JProgressBar
        App.add(download_progress_JProgressBar);

        // Set layout
        App.setSize(600, 300);
        App.setLayout(null);
        App.setVisible(true);

        // ActionListener for select_path_JButton
        select_pach_JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.setDialogTitle("Выберите директорию для сохранения");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    download_path_JTextField.setText(fileChooser.getSelectedFile().getAbsolutePath() + "/");
                }
            }
        });

        // ActionListener for download_site_JButton
        download_site_JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                download_progress_JProgressBar.setValue(0);
                String url = site_link_JTextField.getText();
                String downloadPath = download_path_JTextField.getText();
                String folderName = folder_name_JTextField.getText();
                int downloadTypeIndex = download_type_JComboBox.getSelectedIndex();
                String downloadType = String.valueOf(downloadTypeIndex + 1);

                try {
                    String[] command = {"python3", "app_main.py", url, downloadPath, folderName, downloadType};
                    ProcessBuilder processBuilder = new ProcessBuilder(command);
                    Process process = processBuilder.start();
                    
                    int exitCode = process.waitFor();
                    
                    if (exitCode == 0) {
                        JOptionPane.showMessageDialog(null, "Скачивание завершено успешно!");
                        download_progress_JProgressBar.setValue(100);
                    } else {
                        JOptionPane.showMessageDialog(null, "Произошла ошибка при скачивании.");
                    }
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
