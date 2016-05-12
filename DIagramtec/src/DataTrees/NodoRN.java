package DataTrees;

public class NodoRN<T extends Comparable<T>> implements Comparable<NodoRN<T>> {

    private final String nullNodeString = "_B";
    private NodoRN left;
    private NodoRN right;
    private NodoRN parent;

    private T key;
    private boolean isNilNode;
    private Color color;

    /**
     * Crea un nuevo nodo negro
     * @param dato Es la infrmacion del nodo
     * @param parent El padre del nodo     
     * */
    public NodoRN(T dato, NodoRN parent) {
        this.key = dato;
        this.parent = parent;
        this.color = Color.RED;
        this.setNilNode(false);
    }

    /**
     * Crea un nuevo nodo rojo
     * @param dato Es la infrmacion del nodo
     * @param parent El padre del nodo     
     * */
    private NodoRN(NodoRN parent) {
        this.parent = parent;
        this.color = Color.BLACK;
        this.setNilNode(true);
    }

    /**
     * Pasa un nodo a string
     * @return Un string con la informacion del nodo
     */
    @Override
    public String toString() {
        if (isNilNode) {
            return nullNodeString;
        }
        return key + getColorCode() + " : { " +
                (leftExists() ? left.toString() : nullNodeString) + " , " +
                (rightExists() ? right.toString() : nullNodeString) + " }";
    }

    /**
     * Se encarga de encontrar el color de un nodo
     * @return El color de un nodo en el arbol
     */
    private String getColorCode() {
        if (color == Color.BLACK) {
            return "B";
        }
        return "R";
    }

    /**
     * Revisa si el hijo izquierdo existe
     * @return true si existe o false si no
     */
    public boolean leftExists() {
        return left != null;
    }

    /**
	 *Revisa si el hijo derecho existe
     * @return true si existe o false si no
     */
    public boolean rightExists() {
        return right != null;
    }

    /**
     * Revisa la informacion de un nodo
     * @return La informacion de un nodo
     */
    public T getKey() {
        return key;
    }

    /**
     * Determina la informacion de  un nodo derecho
     *
     * @param key El nuevo dato o informacion
     */
    public void setKey(T key) {
        this.key = key;
    }

    /**
     * @return El hijo izquierdo del nodo
     */
    public NodoRN getLeft() {
        // Create nil leaf nodes lazily
        if (left == null) {
            left = new NodoRN(this);
        }
        return left;
    }

    /**
     * Determina el nodo izquierdo de un nodo padre
     *
     * @param key El nuevo dato o informacion
     */
    public void setLeft(NodoRN left) {
        this.left = left;
    }

    /**
     * @return El hijo derecho del nodo
     */
    public NodoRN getRight() {
        // Create nil leaf nodes lazily
        if (right == null) {
            right = new NodoRN(this);
        }
        return right;
    }

    /**
     * Determina el nodo derecho de un nodo padre
     *
     * @param right El nuevo hijo derecho
     */
    public void setRight(NodoRN right) {
        this.right = right;
    }

    /**
     * @return El padre de un nodo
     */
    public NodoRN getParent() {
        return parent;
    }

    /**
     * @return El abuelo de un nodo
     */
    public NodoRN getGrandparent() {
        if (parent != null && parent.getParent() != null) {
            return parent.getParent();
        }
        return null;
    }

    /**
     * Sets the parent of this node.
     *
     * @param parent Le asigna un padre a un nodo
     */
    public void setParent(NodoRN parent) {
        this.parent = parent;
    }

    /**
     * @return El color de un nodo
     */
    public Color getColor() {
        return color;
    }

    /**
     * Asigna el color al nodo
     *
     * @param color El nuevo color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return Revisa si el nodo  es nulo
     */
    public boolean isNilNode() {
        return isNilNode;
    }

    /**
     * Establece un nodo como nulo
     * @param isNilNode Si es nodo nulo
     */
    public final void setNilNode(boolean isNilNode) {
        this.isNilNode = isNilNode;
    }

    /** {@inheritDoc} */
    @Override
    public int compareTo(NodoRN<T> o) {
        return this.key.compareTo(o.getKey());
    }

    /**
     * Representa el color rojo o negro de un arbol
     */
    public enum Color {
        BLACK,
        RED
    }
}
