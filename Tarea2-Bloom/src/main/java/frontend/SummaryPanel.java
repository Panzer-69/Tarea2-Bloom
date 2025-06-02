/**
 *
 * @author vicho (panzer69) y el joaco que ayudo algo
 */

package frontend;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Este panel muestra un resumen estadístico del desempeño del usuario
 * una vez que termina de revisar las preguntas del quiz.
 */
public class SummaryPanel extends JPanel {

    /**
     * Constructor del panel de resumen.
     * @param manager El objeto que gestiona los ítems y respuestas.
     * @param window La ventana principal (no se usa activamente aquí pero puede ser útil si se expande).
     */
    public SummaryPanel(QuizManager manager, MainWindow window) {
        setLayout(new BorderLayout()); // Establece un layout con regiones: norte, sur, centro, etc.

        JTextArea summaryArea = new JTextArea(); // Área de texto para mostrar el resumen.
        summaryArea.setEditable(false); // No editable por el usuario.
        StringBuilder resumen = new StringBuilder(); // Acumula todo el texto a mostrar.

        // ###### Sección 1: Cantidad de preguntas por nivel de Bloom ######
        resumen.append("Preguntas por nivel Bloom:\n");
        Map<BloomLevel, Long> porNivel = manager.countByLevel(); // Conteo de ítems por nivel de Bloom
        for (BloomLevel level : BloomLevel.values()) {
            resumen.append(level)
                   .append(": ")
                   .append(porNivel.getOrDefault(level, 0L)) // Si no hay, muestra 0
                   .append("\n");
        }

        // ###### Sección 2: Cantidad de preguntas por tipo de ítem ######
        resumen.append("\nPreguntas por tipo:\n");
        Map<ItemType, Long> porTipo = manager.countByType(); // Conteo de ítems por tipo
        for (ItemType type : ItemType.values()) {
            resumen.append(type)
                   .append(": ")
                   .append(porTipo.getOrDefault(type, 0L))
                   .append("\n");
        }

        // ###### Sección 3: Porcentaje de respuestas correctas por nivel de Bloom ######
        resumen.append("\nPorcentaje de respuestas correctas por nivel Bloom:\n");
        Map<BloomLevel, Double> correctosNivel = manager.getCorrectByBloomLevel(); // % correctas por nivel
        for (BloomLevel level : correctosNivel.keySet()) {
            resumen.append(level)
                   .append(": ")
                   .append(String.format("%.2f", correctosNivel.get(level)))
                   .append("%\n");
        }

        // ###### Sección 4: Porcentaje de respuestas correctas por tipo de ítem ######
        resumen.append("\nPorcentaje de respuestas correctas por tipo:\n");
        Map<ItemType, Double> correctosTipo = manager.getCorrectByItemType(); // % correctas por tipo
        for (ItemType type : correctosTipo.keySet()) {
            resumen.append(type)
                   .append(": ")
                   .append(String.format("%.2f", correctosTipo.get(type)))
                   .append("%\n");
        }

        // Muestra el resumen en un área con scroll
        summaryArea.setText(resumen.toString());
        add(new JScrollPane(summaryArea), BorderLayout.CENTER);

        // Botón para cerrar el programa
        JButton salirBtn = new JButton("Salir");
        salirBtn.addActionListener(e -> System.exit(0)); // Cierra la aplicación
        add(salirBtn, BorderLayout.SOUTH);
    }
}