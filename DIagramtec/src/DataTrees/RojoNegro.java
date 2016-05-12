package DataTrees;

import DataTrees.NodoRN.Color;

public class RojoNegro<T extends Comparable<T>> {

    private NodoRN root;

    public RojoNegro() { }

    /**
     * Inserta un nuevo dato
     * @param key El dato a insertar
     * @return true al crearlo*/
    
    public boolean insertarNodo(T key) {
        NodoRN<T> parent = null;
        NodoRN<T> node = root;
        while (node != null && !node.isNilNode()) {
            parent = node;
            int compare = key.compareTo(parent.getKey());
            if (compare == 0) {
                return false;
            }
            if (compare < 0) {
                node = parent.getLeft();
            } else {
                node = parent.getRight();
            }
        }
        if (parent == null) {
            node = new NodoRN(key, null);
            root = node;
        } else {
            node.setParent(parent);
            node.setKey(key);
            node.setNilNode(false);
            node.setColor(NodoRN.Color.RED);
        }
        node.setColor(NodoRN.Color.RED);
        insertFixup(node);
        return true;
    }
    
    /**
     * Se encarga de arreglar el acomodo de los nodos
     * @param node El nodo al que se desea arreglar*/
    private void insertFixup(NodoRN<T> node) {
        while (node.getParent() != null && node.getGrandparent() != null && node.getParent().getColor() == NodoRN.Color.RED) {

            if (node.getParent() == node.getGrandparent().getLeft()) {
                NodoRN<T> uncle = node.getGrandparent().getRight();
                if (uncle.getColor() == NodoRN.Color.RED) {
                    node.getParent().setColor(NodoRN.Color.BLACK);
                    uncle.setColor(NodoRN.Color.BLACK);
                    node = node.getGrandparent();
                    node.setColor(NodoRN.Color.RED);
                } else {
                    if (node == node.getParent().getRight()) {
                        node = node.getParent();
                        rotateLeft(node);
                    }
                    node.getParent().setColor(NodoRN.Color.BLACK);
                    node.getGrandparent().setColor(NodoRN.Color.RED);
                    rotateRight(node.getGrandparent());
                }
            } else if (node.getParent() == node.getGrandparent().getRight()) {
                NodoRN<T> uncle = node.getGrandparent().getLeft();
                if (uncle.getColor() == NodoRN.Color.RED) {
                    node.getParent().setColor(NodoRN.Color.BLACK);
                    uncle.setColor(NodoRN.Color.BLACK);
                    node = node.getGrandparent();
                    node.setColor(NodoRN.Color.RED);
                } else {
                    if (node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rotateRight(node);
                    }
                    node.getParent().setColor(NodoRN.Color.BLACK);
                    node.getGrandparent().setColor(NodoRN.Color.RED);
                    rotateLeft(node.getGrandparent());
                }
            }
        }
        root.setColor(NodoRN.Color.BLACK);
    }

    /**
     * Le cambia la posicion a dos nodos, hacia la izquierda
     * @param x Nodo a partir de donde se realiza el cambio de posicion*/
    private void rotateLeft(NodoRN<T> x) {
        NodoRN<T> y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != null) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            root = y;
        } else {
            if (x == x.getParent().getLeft())
                x.getParent().setLeft(y);
            else
                x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    /**
     * Le cambia la posicion a dos nodos, hacia la derecha
     * @param x Nodo a partir de donde se realiza el cambio de posicion*/
    private void rotateRight(NodoRN<T> x) {
        NodoRN<T> y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != null) {
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null)
            root = y;
        else {
            if (x == x.getParent().getLeft()) {
                x.getParent().setLeft(y);
            } else {
                x.getParent().setRight(y);
            }
        }
        y.setRight(x);
        x.setParent(y);
    }

    /**
     * Busca un nodo con cierta informacion y lo borra
     * @param key Informacion del nodo a buscar*/
    public void removerNodo(T key) {
        NodoRN<T> node = search(key);
        NodoRN<T> y, x;
        if (node.getLeft().isNilNode() || node.getRight().isNilNode()) {
            y = node;
        } else {
            y = encontrarMax(node);
        }
        if (y.getLeft() != null && !y.getLeft().isNilNode()) {
            x = y.getLeft();
        } else {
            x = y.getRight();
        }
        x.setParent(y.getParent());
        if (y.getParent() == null) {
            root = x;
        } else {
            if (y == y.getParent().getLeft())
                y.getParent().setLeft(x);
            else
                y.getParent().setRight(x);
        }
        if (y != node) {
            node.setKey(y.getKey());
        }
        if (y.getColor() == NodoRN.Color.BLACK) {
            deleteFixup(x);
        }
    }

    /**
     * Despues de eliminar el nodo revisa y corrige las violaciones a los parametros de un arbol rojo y negro
     * @param node El nodo a eliminar*/
    private void deleteFixup(NodoRN<T> node) {
        while (node != root && node.getColor() == NodoRN.Color.BLACK) {
            if (node == node.getParent().getLeft()) {
                NodoRN w = node.getParent().getRight();
                if (w.getColor() == NodoRN.Color.RED) {
                    w.setColor(NodoRN.Color.BLACK);
                    node.getParent().setColor(NodoRN.Color.RED);
                    rotateLeft(node.getParent());
                }
                if (w.getLeft().getColor() == NodoRN.Color.BLACK &&
                    w.getRight().getColor() == NodoRN.Color.BLACK) {

                    w.setColor(NodoRN.Color.RED);
                    node = node.getParent();
                } else  {
                    if (w.getRight().getColor() == NodoRN.Color.BLACK) {
                        w.getLeft().setColor(NodoRN.Color.BLACK);
                        w.setColor(NodoRN.Color.RED);
                        rotateRight(w);
                        w = node.getParent().getRight();
                    }
                    w.setColor(node.getParent().getColor());
                    node.getParent().setColor(NodoRN.Color.BLACK);
                    w.getRight().setColor(NodoRN.Color.BLACK);
                    rotateLeft(node.getParent());
                    node = root;
                }
            } else {
                NodoRN w = node.getParent().getLeft();
                if (w.getColor() == NodoRN.Color.RED) {
                    w.setColor(NodoRN.Color.BLACK);
                    node.getParent().setColor(NodoRN.Color.RED);
                    rotateRight(node.getParent());
                }
                if (w.getRight().getColor() == NodoRN.Color.BLACK &&
                    w.getLeft().getColor() == NodoRN.Color.BLACK) {

                    w.setColor(NodoRN.Color.RED);
                    node = node.getParent();
                } else  {
                    if (w.getLeft().getColor() == NodoRN.Color.BLACK) {
                        w.getRight().setColor(NodoRN.Color.BLACK);
                        w.setColor(NodoRN.Color.RED);
                        rotateLeft(w);
                        w = node.getParent().getLeft();
                    }
                    w.setColor(node.getParent().getColor());
                    node.getParent().setColor(NodoRN.Color.BLACK);
                    w.getLeft().setColor(NodoRN.Color.BLACK);
                    rotateRight(node.getParent());
                    node = root;
                }
            }
        }
        node.setColor(NodoRN.Color.BLACK);
    }

    /**Busca el nodo siguiente*/
    private NodoRN<T> encontrarMax(NodoRN<T> node) {
        if (node.getRight() != null && !node.isNilNode()) {
            return encontrarMin(node.getRight());
        }
        NodoRN<T> successor = node.getParent();
        while (successor != null && !successor.isNilNode() && node == successor) {
            node = successor;
            successor = node.getParent();
        }
        return successor;
    }

    /**Busca el nodo de menor valor
     * @param node La raiz de donde se empieza a buscar
     * @return El nodo mas pequeno*/
    private NodoRN<T> encontrarMin(NodoRN<T> node) {
        while (!node.getLeft().isNilNode() && !node.isNilNode()) {
            node = node.getLeft();
        }
        return node;
    }

    /**Busca un nodo 
     * @param key Informacion del nodo a buscar
     * @return El nodo buscado*/
    public NodoRN<T> search(T key) {
        if (root == null) {
            return null;
        }

        return search(key, root);
      }

    /**
     * Busca un nodo en un arbol
     * @param key Corresponde a la informacion por buscar
     * @param node Corresponde al nodo raiz
     * @return El nodo buscado*/
    private NodoRN<T> search( T key, NodoRN<T> node) {
        if (key == node.getKey()) {
            return node;
        }

        if (key.compareTo( node.getKey() ) < 0) {
            if (!node.leftExists()) {
                return null;
            }
            return search(key, node.getLeft());
        }

        if (key.compareTo( node.getKey() ) >= 0) {
            if (!node.rightExists()) {
                return null;
            }
            return search(key, node.getRight());
        }

        return null;
    }

    /**Imprime el arbol en consola
     * @return String del arbol*/
    public String imprimirArbol() {
        if (root == null) {
            return "(empty)";
        }
        return root.toString();
    }
} 