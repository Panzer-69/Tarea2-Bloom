/**
 *
 * @author vicho (panzer69) y el joaco que ayudo algo
 */

package frontend;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * Clase principal de la interfaz gráfica.
 * Representa la ventana principal del sistema y coordina los otros paneles.
 */
public class MainWindow extends JFrame {
    private QuizManager manager;         // Controlador principal de la lógica del quiz
    private final JPanel mainPanel;      // Panel principal donde se cargan los subpaneles dinámicamente

    /**
     * Constructor: configura la ventana y lanza el selector de archivos.
     */
    public MainWindow() {
        setTitle("Sistema de Evaluación - Taxonomía de Bloom");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        showFileChooser(); // Inicia con el selector de archivo
    }

    /**
     * Muestra un JFileChooser para seleccionar el archivo de preguntas.
     * Si se selecciona un archivo válido, lo carga en el sistema y muestra un resumen inicial.
     */
    private void showFileChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar archivo de ítems");

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                List<Item> items = FileLoader.loadItemsFromFile(file); // Carga desde archivo
                manager = new QuizManager(items); // Inicializa el controlador

                // Muestra resumen inicial de cantidad de preguntas y tiempo estimado
                JPanel infoPanel = new JPanel(new GridLayout(3, 1));
                infoPanel.add(new JLabel("Cantidad de ítems: " + items.size()));
                int tiempoTotal = items.stream().mapToInt(Item::getTimeEstimate).sum();
                infoPanel.add(new JLabel("Tiempo total estimado: " + tiempoTotal + " segundos"));

                // Botón para iniciar el quiz
                JButton iniciar = new JButton("Iniciar prueba");
                iniciar.addActionListener(e -> startQuiz());

                infoPanel.add(iniciar);
                mainPanel.add(infoPanel, BorderLayout.CENTER);
                revalidate();
                repaint();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar archivo: " + e.getMessage());
            }
        }
    }

    /**
     * Cambia el contenido principal por el panel del cuestionario.
     */
    private void startQuiz() {
        QuizPanel quizPanel = new QuizPanel(manager, this);
        setContentPanel(quizPanel);
    }

    /**
     * Cambia el contenido principal por el panel de revisión de respuestas.
     */
    public void showReview() {
        ReviewPanel reviewPanel = new ReviewPanel(manager, this);
        setContentPanel(reviewPanel);
    }

    /**
     * Cambia el contenido principal por el panel del resumen de resultados.
     */
    public void showSummary() {
        SummaryPanel panel = new SummaryPanel(manager, this);
        setContentPanel(panel);
    }

    /**
     * Método utilitario para actualizar el contenido mostrado en la ventana.
     * @param panel El nuevo panel a mostrar (quiz, review o summary)
     */
    public void setContentPanel(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }
}
