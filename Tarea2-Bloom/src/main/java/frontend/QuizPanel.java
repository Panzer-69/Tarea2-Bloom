/**
 *
 * @author vicho (panzer69) y el joaco que ayudo algo
 */

package frontend;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class QuizPanel extends JPanel {
    // Referencia al administrador del cuestionario (controla el flujo y los datos)
    private final QuizManager manager;

    // Referencia a la ventana principal para poder cambiar de panel
    private final MainWindow window;

    // Componentes gráficos
    private final JTextArea preguntaArea;     
    private final ButtonGroup opcionesGroup;  
    private final JPanel opcionesPanel;       
    private final JButton siguienteBtn;       

    // Constructor: configura el panel y los componentes gráficos
    public QuizPanel(QuizManager manager, MainWindow window) {
        this.manager = manager;
        this.window = window;
        setLayout(new BorderLayout()); // Usamos BorderLayout para organizar mejor las secciones

        // Configuración del área de pregunta (parte superior)
        preguntaArea = new JTextArea();
        preguntaArea.setEditable(false);
        preguntaArea.setLineWrap(true);
        preguntaArea.setWrapStyleWord(true);
        add(new JScrollPane(preguntaArea), BorderLayout.NORTH); // Scroll en caso de preguntas largas

        // Configuración de las opciones (parte central)
        opcionesPanel = new JPanel();
        opcionesPanel.setLayout(new GridLayout(0, 1)); // Una opción por fila
        opcionesGroup = new ButtonGroup();
        add(opcionesPanel, BorderLayout.CENTER);

        // Configuración del botón de siguiente/finalizar (parte inferior)
        siguienteBtn = new JButton("Siguiente");
        siguienteBtn.addActionListener(e -> {
            guardarRespuestaSeleccionada(); // Guarda lo que eligió el usuario

            if (manager.hasNext()) {
                manager.next();         // Avanza a la siguiente pregunta
                actualizarPregunta();   // Refresca la pantalla
            } else {
                window.showReview();    // Si no hay más va a la revisión
            }
        });

        // Panel que contiene el botón
        JPanel controlPanel = new JPanel();
        controlPanel.add(siguienteBtn);
        add(controlPanel, BorderLayout.SOUTH);

        // Carga la primera pregunta en pantalla
        actualizarPregunta();
    }

    // Refresca los datos del panel con la pregunta actual
    private void actualizarPregunta() {
        Item item = manager.getCurrentItem();
        preguntaArea.setText(item.getQuestion()); // Muestra la pregunta

        opcionesPanel.removeAll();                // Limpia opciones anteriores
        opcionesGroup.clearSelection();           // Deselecciona todo

        // Crea los botones de las nuevas opciones
        for (String opcion : item.getOptions()) {
            JRadioButton radio = new JRadioButton(opcion);

            // Marca como seleccionada si el usuario ya había respondido
            if (opcion.equalsIgnoreCase(item.getUserAnswer())) {
                radio.setSelected(true);
            }

            opcionesGroup.add(radio);
            opcionesPanel.add(radio);
        }

        // Cambia el texto del botón si es la última pregunta
        if (!manager.hasNext()) {
            siguienteBtn.setText("Finalizar");
        } else {
            siguienteBtn.setText("Siguiente");
        }

        // Refresca el panel
        revalidate();
        repaint();
    }

    // Guarda la respuesta que seleccionó el usuario
    private void guardarRespuestaSeleccionada() {
        for (Enumeration<AbstractButton> buttons = opcionesGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                manager.setUserAnswer(button.getText());
                break;
            }
        }
    }
}