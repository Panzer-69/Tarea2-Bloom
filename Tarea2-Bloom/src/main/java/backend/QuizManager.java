/**
 *
 * @author vicho (panzer69) y el joaco que ayudo algo
 */

package backend;

import java.util.*;
import java.util.stream.Collectors;

public class QuizManager {
    // Lista de preguntas (ítems) del cuestionario
    private final List<Item> items;

    // Índice actual que indica en qué pregunta está el usuario
    private int currentIndex;

    // Constructor que recibe la lista de ítems
    public QuizManager(List<Item> items) {
        this.items = items;
        this.currentIndex = 0; // Empieza en la primera pregunta
    }

    // Devuelve el ítem actual según el índice
    public Item getCurrentItem() {
        return items.get(currentIndex);
    }

    // Guarda la respuesta del usuario en el ítem actual
    public void setUserAnswer(String answer) {
        items.get(currentIndex).setUserAnswer(answer);
    }

    // Indica si hay una siguiente pregunta
    public boolean hasNext() {
        return currentIndex < items.size() - 1;
    }

    // Indica si hay una pregunta anterior
    public boolean hasPrevious() {
        return currentIndex > 0;
    }

    // Avanza a la siguiente pregunta si existe
    public void next() {
        if (hasNext()) currentIndex++;
    }

    // Retrocede a la pregunta anterior si existe
    public void previous() {
        if (hasPrevious()) currentIndex--;
    }

    // Calcula el porcentaje de respuestas correctas por nivel de Bloom
    public Map<BloomLevel, Double> getCorrectByBloomLevel() {
        Map<BloomLevel, Integer> correct = new HashMap<>();
        Map<BloomLevel, Integer> total = new HashMap<>();

        // Contar totales y aciertos por nivel
        for (Item item : items) {
            BloomLevel level = item.getBloomLevel();
            total.put(level, total.getOrDefault(level, 0) + 1);

            if (item.getCorrectAnswer().equalsIgnoreCase(item.getUserAnswer())) {
                correct.put(level, correct.getOrDefault(level, 0) + 1);
            }
        }

        // Calcular porcentaje correcto por nivel
        Map<BloomLevel, Double> result = new HashMap<>();
        for (BloomLevel level : total.keySet()) {
            result.put(level, (correct.getOrDefault(level, 0) * 100.0) / total.get(level));
        }

        return result;
    }

    // Calcula el porcentaje de respuestas correctas por tipo de ítem
    public Map<ItemType, Double> getCorrectByItemType() {
        Map<ItemType, Integer> correct = new HashMap<>();
        Map<ItemType, Integer> total = new HashMap<>();

        // Contar totales y aciertos por tipo
        for (Item item : items) {
            ItemType type = item.getItemType();
            total.put(type, total.getOrDefault(type, 0) + 1);

            if (item.getCorrectAnswer().equalsIgnoreCase(item.getUserAnswer())) {
                correct.put(type, correct.getOrDefault(type, 0) + 1);
            }
        }

        // Calcular porcentaje correcto por tipo
        Map<ItemType, Double> result = new HashMap<>();
        for (ItemType type : total.keySet()) {
            result.put(type, (correct.getOrDefault(type, 0) * 100.0) / total.get(type));
        }

        return result;
    }

    // Cuenta cuántos ítems hay por cada nivel de Bloom
    public Map<BloomLevel, Long> countByLevel() {
        return items.stream()
                .collect(Collectors.groupingBy(Item::getBloomLevel, Collectors.counting()));
    }

    // Cuenta cuántos ítems hay por cada tipo
    public Map<ItemType, Long> countByType() {
        return items.stream()
                .collect(Collectors.groupingBy(Item::getItemType, Collectors.counting()));
    }
}
//Nota: no se como lo hice funcionar... (horas gastadas en esta wea:4.5)
//Nota2: funciona al menos xD
//Nota3: movi algo y no se que y ahora se fue al carajo
//Nota4:ahora si no lo vuelvo a tocar (oh netbeans tu escuchas mi descenso a la locura)