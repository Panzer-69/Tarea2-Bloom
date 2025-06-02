/**
 *
 * @autor vicho (panzer69) y el joaco que ayudo algo
 */

// Importa la ventana principal del sistema
import frontend.MainWindow;

/**
 * Clase principal del programa.
 * Punto de entrada que lanza la interfaz gráfica de la aplicación.
 */
public class Main {

    public static void main(String[] args){
        // Inicia la interfaz gráfica en el hilo de eventos de Swing (recomendado por Java)
        javax.swing.SwingUtilities.invokeLater(() -> 
            new MainWindow().setVisible(true) // Crea y muestra la ventana principal
        );
    }
}