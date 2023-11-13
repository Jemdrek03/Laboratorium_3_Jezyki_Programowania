import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BMI calculator = new BMI();
                calculator.display();
            }
        });
    }
}