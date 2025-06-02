/**
 *
 * @author vicho (panzer69) y el joaco que ayudo algo
 */

package backend;

import java.io.*;
import java.util.*;


public class FileLoader {


    public static List<Item> loadItemsFromFile(File file) throws Exception {
        List<Item> items = new ArrayList<>(); // Lista que almacenará los ítems leídos

        // Bloque try-with-resources para cerrar automáticamente el BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            // Lee línea por línea hasta el final del archivo
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Ignora líneas vacías

                String[] parts = line.split(";"); // Separa cada campo por ";"
                if (parts.length < 6)
                    throw new Exception("Formato inválido"); // Validación mínima

                // Extrae y transforma los datos de cada campo
                String pregunta = parts[0];
                String[] options = parts[1].split("\\|"); // Opciones separadas por "|"
                String correctAnswer = parts[2];
                BloomLevel level = BloomLevel.valueOf(parts[3].toUpperCase()); // Convierte a enum
                ItemType type = ItemType.valueOf(parts[4].toUpperCase());      // Convierte a enum
                int tiempo = Integer.parseInt(parts[5]);                       // Convierte a int

                // Crea un nuevo objeto Item y lo agrega a la lista
                Item item = new Item(pregunta, options, correctAnswer, level, type, tiempo);
                items.add(item);
            }
        }

        // Devuelve la lista completa de preguntas
        return items;
    }
}