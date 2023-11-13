import javax.swing.*;
import java.awt.event.*;
import java.io.*;
public class Notepad {
    private JFrame frame;
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File currentFile;

    public void start() {
        //tworzenie frame, miejsca do pisania, wybieranie pliku
        frame = new JFrame("Notepad");
        textArea = new JTextArea();
        fileChooser = new JFileChooser();
        currentFile = null;

        //Ustawienie rozmiaru i zamkniecie aplikacji przy wyjsciu z niej
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        //Pasek menu i opcje schowane "pod" nim
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");


        //Metody odpowiadajace za poszczegolna "akcje" znajdujaca sie w filemenu
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFile();
            }
        });

        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);
        frame.add(new JScrollPane(textArea));
        frame.setVisible(true);
    }

    private void newFile() {
        textArea.setText("");
        currentFile = null;
    }

    private void openFile() {
        //otwarcie i wyswietlenie okna dialogowego
        int returnValue = fileChooser.showOpenDialog(frame);
        //Sprawdzenie czy uzytkownik wybral plik
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            //Pobranie wybranego pliku do zmiennej
            File selectedFile = fileChooser.getSelectedFile();
            try {
                //odczytanie zawartosci pliku
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                //Wyczyszczenie okna tekstowego w celu przygotowania do wczytania nowego
                textArea.setText("");
                //utworzenie line i wczytywanie przez niego zawartosci pliku
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                reader.close();
                //zapisanie wybranego pliku do zmiennej currentFile
                currentFile = selectedFile;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        if (currentFile == null) {
            //Wyswietlenie okna dialogowego
            int returnValue = fileChooser.showSaveDialog(frame);
            //Sprawdzenie czy uzytkownik potwierdzil wybor
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                //zapisanie danego pliku w zmiennej currentFile
                currentFile = fileChooser.getSelectedFile();
            } else {
                return;
            }
        }

        try {
            //obiekt sluzacy do zapisania danych do pliku tekstowego, o nazwie znajdujacej sie w currentFile
            //Dodalem rozszerzenie txt, poniewaz z natury jest to notatnik, wiec chcialem zeby uzytkownik nie musial samemu wpisywac rozszerzenia
            BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile + ".txt"));
            //zapisanie tekstu do pliku
            writer.write(textArea.getText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}