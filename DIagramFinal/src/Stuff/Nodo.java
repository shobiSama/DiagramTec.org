package Stuff;

public class Nodo {
    private Object Data;
    private Nodo LeftChild;
    private Nodo RightChild;
    private Nodo Father;
    private int Altura;
    private String Color;
    
    //Constructor
    public Nodo (Object Dato){
        Data = Dato;
        Altura = 0;
    }

    /**
     * @return the Data
     */
    public Object getData() {
        return Data;
    }

    /**
     * @param Data the Data to set
     */
    public void setData(Object Data) {
        this.Data = Data;
    }

    /**
     * @return the LeftChild
     */
    public Nodo getLeftChild() {
        return LeftChild;
    }

    /**
     * @param LeftChild the LeftChild to set
     */
    public void setLeftChild(Nodo LeftChild) {
        this.LeftChild = LeftChild;
    }

    /**
     * @return the RightChild
     */
    public Nodo getRightChild() {
        return RightChild;
    }

    /**
     * @param RightChild the RightChild to set
     */
    public void setRightChild(Nodo RightChild) {
        this.RightChild = RightChild;
    }

    /**
     * @return the Father
     */
    public Nodo getFather() {
        return Father;
    }

    /**
     * @param Father the Father to set
     */
    public void setFather(Nodo Father) {
        this.Father = Father;
    }

    /**
     * @return the Altura
     */
    public int getAltura() {
        return Altura;
    }

    /**
     * @deprecated: Aumenta la altura del nodo en 1
     */
    public void AumentaAltura() {
        this.setAltura(getAltura()+1);
    }
    
    /**
     * @deprecated: Disminuye la altura del nodo en 1
     */
    public void DisminuyeAltura() {
        this.setAltura(getAltura()-1);
    }

    /**
     * @param Altura the Altura to set
     */
    public void setAltura(int Altura) {
        this.Altura = Altura;
    }

    /**
     * @return the Color
     */
    public String getColor() {
        return Color;
    }

    /**
     * @param Color the Color to set
     */
    public void setColor(String Color) {
        this.Color = Color;
    }
    
}
