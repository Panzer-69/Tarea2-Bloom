/**
 *
 * @author vicho (panzer69) y el joaco que ayudo algo
 */

package frontend;

import backend.*;

import javax.swing.*;
import java.awt.*;

/**
 * Panel de revisión de respuestas. Muestra cada pregunta con su opción correcta e incorrecta.
 * Permite navegar entre preguntas ya respondidas y salir del sistema al finalizar.
 */
public class ReviewPanel extends JPanel {
    private final QuizManager manager;   // Controlador del estado del quiz
    private final MainWindow window;     // Referencia a la ventana principal
    private JTextArea questionArea;      // Área de texto para la pregunta
    private JLabel resultLabel;          // Indicador si la respuesta fue correcta o no

    /**
     * Constructor que configura el panel de revisión.
     */
    public ReviewPanel(QuizManager manager, MainWindow window) {
        this.manager = manager;
        this.window = window;
        setLayout(new BorderLayout());
        setupUI(); // Inicializa la interfaz
    }

    /**
     * Método central para armar la interfaz gráfica de revisión.
     * Muestra la pregunta actual, sus opciones y controles de navegación.
     */
    private void setupUI() {
        removeAll(); // Limpia el panel antes de actualizar

        Item item = manager.getCurrentItem(); // Obtiene el ítem actual

        // Muestra la pregunta en un área de texto no editable
        questionArea = new JTextArea(item.getQuestion());
        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        add(new JScrollPane(questionArea), BorderLayout.NORTH);

        // Panel central con las opciones de respuesta
        JPanel panel = new JPanel(new GridLayout(0, 1));
        for (String opt : item.getOptions()) {
            JLabel label = new JLabel(opt);
            if (opt.equalsIgnoreCase(item.getCorrectAnswer())) {
                label.setForeground(Color.GREEN); // Opción correcta en verde
            } else if (opt.equalsIgnoreCase(item.getUserAnswer())) {
                label.setForeground(Color.RED);   // Opción incorrecta seleccionada en rojo
            }
            panel.add(label);
        }
        add(panel, BorderLayout.CENTER);

        // Muestra un resultado textual abajo indicando si fue correcto o no
        resultLabel = new JLabel(item.getCorrectAnswer().equalsIgnoreCase(item.getUserAnswer())
                ? "✔ Correcto" : "✘ Incorrecto");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        // Controles de navegación (anterior, siguiente o salir)
        JPanel nav = new JPanel();

        JButton prev = new JButton("Anterior");
        prev.setEnabled(manager.hasPrevious()); // Desactiva si es la primera pregunta
        prev.addActionListener(e -> {
            manager.previous(); // Retrocede una pregunta
            setupUI();          // Actualiza interfaz
            revalidate();
            repaint();
        });
        nav.add(prev);

        // Si hay otra pregunta, muestra botón "Siguiente", si no, muestra botón "Salir"
        if (manager.hasNext()) {
            JButton next = new JButton("Siguiente");
            next.addActionListener(e -> {
                manager.next(); // Avanza a la siguiente
                setupUI();
                revalidate();
                repaint();
            });
            nav.add(next);
        } else {
            JButton exitBtn = new JButton("Salir");
            exitBtn.addActionListener(e -> System.exit(0)); // Finaliza la aplicación
            nav.add(exitBtn);
        }

        add(nav, BorderLayout.NORTH);
    }
}