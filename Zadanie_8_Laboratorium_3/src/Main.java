import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TextEditor editor = new TextEditor();
                editor.display();
            }
        });
    }
}