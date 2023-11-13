import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEncryption {
    private JFrame frame;
    private JTextField inputTextField;
    private JComboBox<String> encryptionTypeComboBox;
    private JTextArea resultTextArea;
    private JButton encryptButton;

    public TextEncryption() {
        //inicjalizacja frame i jego ustawien
        frame = new JFrame("Encryption App");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        //inicjalizacja pola tekstowego, boxa, pola wynikowego
        inputTextField = new JTextField(20);
        encryptionTypeComboBox = new JComboBox<>(new String[]{"Cezar", "ROT13"});
        resultTextArea = new JTextArea(12,25);
        resultTextArea.setEditable(false);
        encryptButton = new JButton("Encrypt");

        //metoda sluchajaca
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptText();
            }
        });

        //inicjalizacja i dodanie wszystkich pol
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Text to be encrypted: "));
        inputPanel.add(inputTextField);
        inputPanel.add(new JLabel("Type of encryption: "));
        inputPanel.add(encryptionTypeComboBox);
        inputPanel.add(encryptButton);
        inputPanel.add(new JLabel("Encrypted text: "));
        inputPanel.add(resultTextArea);


        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.CENTER);

    }


    private void encryptText() {
        //pobranie tekstu uzytkownika
        String inputText = inputTextField.getText();
        //pobranie wybrany rodzaj szyfrowania
        String encryptionType = encryptionTypeComboBox.getSelectedItem().toString();
        //zmiena przechowujaca zaszyfrowany tekst
        String encryptedText = "";

        //szyfrowanie tekstu szyfrem wybranym przez uzytkownika
        if (encryptionType.equals("Cezar")) {
            encryptedText = caesarCipher(inputText, 3);
        } else if (encryptionType.equals("ROT13")) {
            encryptedText = rot13(inputText);
        }

        resultTextArea.setText(encryptedText);
    }

    //szyfr cezara
    private String caesarCipher(String text, int shift) {
        //zmienna przechowujaca zaszyfrowany tekst
        StringBuilder result = new StringBuilder();

        //petla po kazdym znaku
        for (char character : text.toCharArray()) {

            if (Character.isLetter(character)) {
                char base;
                //sprawdzenie czy litera jest mala czy duza
                //ustawienie "bazy" wedlug ktorej odejmuje
                if(Character.isLowerCase(character))
                {
                    base = 'a';
                }
                else
                {
                    base = 'A';
                }
                //zaszyfrowanie
                result.append((char) (base + (character - base + shift) % 26));
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }

    //szyfr rot13
    private String rot13(String text) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base;
                //sprawdzenie czy litera jest mala czy duza
                //ustawienie "bazy" wedlug ktorej odejmuje
                if(Character.isLowerCase(character))
                {
                    base = 'a';
                }
                else
                {
                    base = 'A';
                }
                //zaszyfrowanie
                result.append((char) (base + (character - base + 13) % 26));
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }
    public void display()
    {
        frame.setVisible(true);
    }
}