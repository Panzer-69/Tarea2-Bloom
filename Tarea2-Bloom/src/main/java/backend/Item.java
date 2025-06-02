/**
 *
 * @author vicho (panzer69) y el joaco que ayudo algo
 */

package backend;


public class Item {
    // Enunciado de la pregunta
    private String question;

    // Opciones de respuesta (tipo test)
    private String[] options;

    // Respuesta correcta
    private String correctAnswer;

    // Respuesta que seleccionó el usuario (se llena durante la prueba)
    private String userAnswer;

    // Nivel de la Taxonomía de Bloom asociado a esta pregunta
    private BloomLevel bloomLevel;

    // Tipo de pregunta (por ejemplo: selección, verdadero/falso, etc.)
    private ItemType itemType;

    // Tiempo estimado para responder esta pregunta (en segundos)
    private int timeEstimate;


    public Item() {}


    public Item(String question, String[] options, String correctAnswer, BloomLevel bloomLevel, ItemType itemType, int timeEstimate) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.bloomLevel = bloomLevel;
        this.itemType = itemType;
        this.timeEstimate = timeEstimate;
        this.userAnswer = ""; // Inicialmente sin respuesta del usuario
    }

    // Métodos getters para acceder a los datos del ítem
    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    // Setter para registrar la respuesta del usuario
    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public BloomLevel getBloomLevel() {
        return bloomLevel;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public int getTimeEstimate() {
        return timeEstimate;
    }
}