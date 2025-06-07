import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BasketballCombinations extends JFrame {
    private JTextField nField, kField, resultField;
    private JButton computeButton, clearButton, exitButton;

    public BasketballCombinations() {
        setTitle("Συνδυασμοί Πεντάδων Καλαθοσφαίρισης");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Components
        JLabel nLabel = new JLabel("Αριθμός παικτών (n):");
        nField = new JTextField();

        JLabel kLabel = new JLabel("Παίκτες στην ομάδα (k):");
        kField = new JTextField();

        JLabel resultLabel = new JLabel("Συνολικοί συνδυασμοί:");
        resultField = new JTextField();
        resultField.setEditable(false);

        computeButton = new JButton("Υπολογισμός");
        clearButton = new JButton("Εκκαθάριση");
        exitButton = new JButton("Έξοδος");

        // Listeners
        computeButton.addActionListener(e -> computeCombinations());
        clearButton.addActionListener(e -> clearFields());
        exitButton.addActionListener(e -> System.exit(0));

        // Layout
        add(nLabel);
        add(nField);
        add(kLabel);
        add(kField);
        add(resultLabel);
        add(resultField);
        add(computeButton);
        add(clearButton);
        add(exitButton);

        setLocationRelativeTo(null); // Κέντρο οθόνης
        setVisible(true);
    }

    // Αναδρομική μέθοδος παραγοντικού
    private long factorial(int x) {
        if (x < 0) throw new IllegalArgumentException("Αρνητικό παραγοντικό!");
        if (x == 0 || x == 1) return 1;
        return x * factorial(x - 1);
    }

    // Υπολογισμός συνδυασμών
    private void computeCombinations() {
        try {
            int n = Integer.parseInt(nField.getText().trim());
            int k = Integer.parseInt(kField.getText().trim());

            if (n < 0 || k < 0) {
                showError("Οι αριθμοί πρέπει να είναι μη αρνητικοί.");
                return;
            }
            if (k > n) {
                showError("Το k δεν μπορεί να είναι μεγαλύτερο από το n.");
                return;
            }

            long combinations = factorial(n) / (factorial(k) * factorial(n - k));
            resultField.setText(String.valueOf(combinations));
        } catch (NumberFormatException ex) {
            showError("Παρακαλώ δώστε έγκυρους ακέραιους αριθμούς.");
        } catch (ArithmeticException | IllegalArgumentException ex) {
            showError("Σφάλμα υπολογισμού: " + ex.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        nField.setText("");
        kField.setText("");
        resultField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BasketballCombinations::new);
    }
}
