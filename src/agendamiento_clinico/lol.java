import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class lol {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new lol().crearVentana());
    }
    private void crearVentana() {
        JFrame frame = new JFrame("ComboBox con filtro dinámico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Lista original de elementos
        String[] elementos = {"Argentina", "Brasil", "Chile", "Colombia", "Paraguay", "Perú", "Uruguay", "Venezuela"};
        JComboBox<String> comboBox = new JComboBox<>(elementos);
        comboBox.setEditable(true); // Permite escribir

        JTextField editor = (JTextField) comboBox.getEditor().getEditorComponent();

        // Guardamos los elementos originales para filtrarlos después
        java.util.List<String> listaOriginal = new ArrayList<>(Arrays.asList(elementos));

        // Listener para detectar escritura
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = editor.getText();
                comboBox.hidePopup(); // Oculta el popup antes de actualizar
                comboBox.removeAllItems();

                // Filtrar las coincidencias
                for (String item : listaOriginal) {
                    if (item.toLowerCase().contains(texto.toLowerCase())) {
                        comboBox.addItem(item);
                    }
                }
                editor.setText(texto); // Mantener el texto
                comboBox.showPopup(); // Mostrar el popup actualizado
            }
        });

        frame.add(comboBox);
        frame.setSize(400, 120);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
