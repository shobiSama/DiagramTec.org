package DataTrees;

public class Splay<T extends Comparable<T>> {

    private NodoSplay<T> root;

    public Splay() { }

    /**
     * Se encarga de realizar los cambios splay del arbol
     * @param node Nodo para el splay*/
    
    private void splay(NodoSplay<T> node) {
        while (node.parentExists()) {
            NodoSplay parent = node.getParent();
            if (!parent.parentExists()) {
                if (parent.getLeft() == node) {
                    rotateRight(parent);
                } else {
                    rotateLeft(parent);
                }
            } else {
                NodoSplay gparent = parent.getParent();
                if (parent.getLeft() == node && gparent.getLeft() == parent) {
                    rotateRight(gparent);
                    rotateRight(node.getParent());
                } else if (parent.getRight() == node && gparent.getRight() == parent) {
                    rotateLeft(gparent);
                    rotateLeft(node.getParent());
                } else if (parent.getLeft() == node && gparent.getRight() == parent) {
                    rotateRight(parent);
                    rotateLeft(node.getParent());
                } else {
                    rotateLeft(parent);
                    rotateRight(node.getParent());
                }
            }
        }
    }

    /**
     * Se encarga de realizar la rotacion hacia la izquierda
     * @param x El nodo por rotar*/
    private void rotateLeft(NodoSplay<T> x) {
        NodoSplay y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != null) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            root = y;
        } else {
            if (x == x.getParent().getLeft()) {
                x.getParent().setLeft(y);
            } else {
                x.getParent().setRight(y);
            }
        }
        y.setLeft(x);
        x.setParent(y);
    }

    /**
     * Se encarga de realizar la rotacion hacia la derecha
     * @param x El nodo por rotar*/
    private void rotateRight(NodoSplay<T> x) {
        NodoSplay y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != null) {
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            root = y;
        } else {
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
     * Inserta un nuevo nodo
     * @param key Corresponde a la informacion del nuevo nodo*/
    public void insertarNodo(T key) {
        if (root == null) {
            root = new NodoSplay(key, null);
            return;
        }

        insertarNodo(key, root);
        search(key);
    }

    /**
     * Busca el nodo mas pequeno
     * @return Nodo pequeno*/
    public T findMin( )
	{ 
    	return encontrarMin (root);

	}
    /**Busca el nodo mas grande
     * @return Nodo grande*/
    public T findMax( )
	{ 
    	return encontrarMax (root);

	}
    
    /**
     * Hace nulo el arbol*/
    public void limpiarArbol( )
	{ 
    	root = null; 
    }
    
    /**Revisa si el arbol esta sin nodos
     * @return true si no hay ningun nodo o false si si hay*/
	public boolean esLimpio( )
	{ 
		return root == null; 
	}
    
	/**
     * Inserta un nuevo nodo, metodo privado
     * @param key Corresponde a la informacion del nuevo nodo
     * @param node Corresponde al nodo del cual se parte a buscar*/
    private void insertarNodo(T key, NodoSplay<T> node) {
        if (key.compareTo( node.getKey() ) < 0) {
            if (node.leftExists()) {
                insertarNodo(key, node.getLeft());
            } else {
                node.setLeft(new NodoSplay(key, node));
            }
        }

        if (key.compareTo(node.getKey())>0) {
            if (node.rightExists()) {
                insertarNodo(key, node.getRight());
            } else {
                node.setRight(new NodoSplay(key, node));
            }
        }
    }

    /**
     * Elimina un nodo
     * @param key Corresponde a la informacion del nodo a buscar */
    public void removerNodo(T key) {
        if (root == null) {
            return;
        }

        search(key);
        removerNodo(key, root);
    }

    /**
     * Elimina un nodo
     * @param key Corresponde a la informacion del nodo a buscar 
     * @param node Es el nodo a partir de donde se empieza a buscar*/
    private void removerNodo(T key, NodoSplay<T> node) {
        if (key.compareTo(node.getKey())< 0) {
            if (node.leftExists()) {
                removerNodo(key, node.getLeft());
            }
            if (node.getLeft().isDeleted()) {
                node.setLeft(null);
            }
            return;
        }

        if (key.compareTo(node.getKey()) > 0) {
            if (node.rightExists()) {
                removerNodo(key, node.getRight());
            }
            if (node.getRight().isDeleted()) {
                node.setRight(null);
            }
            return;
        }

        eliminar(node);
    }

    /**
     * Elimina un nodo
     * @param node Es el nodo a partir de donde se empieza a buscar*/
    private void eliminar(NodoSplay<T> node) {
        if (!(node.leftExists() || node.rightExists())) {
            node.setDeleted(true);
            return;
        }

        if (node.leftExists() && !node.rightExists()) {
            node.setKey(node.getLeft().getKey());
            if (node.getLeft().rightExists()) {
                node.setRight(node.getLeft().getRight());
            }
            if (node.getLeft().leftExists()) {
                node.setLeft(node.getLeft().getLeft());
            } else {
                node.setLeft(null);
            }
            return;
        }

        if (node.rightExists() && !node.leftExists()) {
            node.setKey(node.getRight().getKey());
            if (node.getRight().leftExists()) {
                node.setLeft(node.getLeft().getLeft());
            }
            if (node.getRight().rightExists()) {
                node.setRight(node.getLeft().getRight());
            } else {
                node.setRight(null);
            }
            return;
        }

        // both exist, replace with minimum from right sub-tree
        T min = encontrarMin(node.getRight());
        node.setKey(min);
    }

    /**
     * Busca el nodo mas pequeno
     * @param node Corresponde al nodo donde se comienza a buscar
     * @return Nodo pequeno*/
    private T encontrarMin(NodoSplay<T> node) {
        if (!node.leftExists()) {
            node.setDeleted(true);
            return node.getKey();
        }

        T min = encontrarMin(node.getLeft());
        if (node.getLeft().isDeleted()) {
            node.setLeft(null);
        }
        return min;
    }
    
    /**
     * Busca el nodo mas grande
     * @param node Corresponde al nodo donde se comienza a buscar
     * @return Nodo grande*/
    private T encontrarMax(NodoSplay<T> node) {
        if (!node.rightExists()) {
            node.setDeleted(true);
            return node.getKey();
        }

        T max = encontrarMax(node.getRight());
        if (node.getRight().isDeleted()) {
            node.setRight(null);
        }
        return max;
    }

    /**
     * Busca un nodo
     * @param key Corresponde a la informacion del nodo a buscar
     * @return true si lo encuentra, de lo contrario false*/
    public boolean search(T key) {
        if (root == null) {
            return false;
        }

        NodoSplay<T> node = contiene(key, root);
        splay(node);
        return node != null;
    }
    
   
    /**
     * Busca un nodo
     * @param key Corresponde a la informacion del nodo a buscar
     * @param node Nodo a partir del que se comienza a buscar
     * @return true si lo encuentra, de lo contrario false*/
    private NodoSplay<T> contiene(T key, NodoSplay<T> node) {
        if (key == node.getKey()) {
            return node;
        }

        if (key.compareTo(node.getKey()) < 0) {
            if (!node.leftExists()) {
                return null;
            }
            return contiene(key, node.getLeft());
        }

        if (key.compareTo(node.getKey()) > 0) {
            if (!node.rightExists()) {
                return null;
            }
            return contiene(key, node.getRight());
        }

        return null;
    }

    /**
     * Convierte el arbol en string
     * @return Arbol en string*/
    public String imprimirArbol() {
        return root.toString();
    }
    
    
    private class NodoSplay<T extends Comparable<T>> {

        private final String nullNodeString = "_";
        private NodoSplay<T> left;
        private NodoSplay<T> right;
        private NodoSplay<T> parent;

        private T key;
        private boolean isDeleted = false;

        public NodoSplay(T key, NodoSplay<T> parent) {
            this.key = key;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return key + " : { " +
                    (leftExists() ? left.toString() : nullNodeString) + " , " +
                    (rightExists() ? right.toString() : nullNodeString) + " }";
        }

        public boolean leftExists() {
            return left != null;
        }

        public boolean rightExists() {
            return right != null;
        }

        public boolean parentExists() {
            return parent != null;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public NodoSplay<T> getLeft() {
            return left;
        }

        public void setLeft(NodoSplay<T> left) {
            this.left = left;
        }

        public NodoSplay<T> getRight() {
            return right;
        }

        public void setRight(NodoSplay<T> right) {
            this.right = right;
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
        }

        public NodoSplay<T> getParent() {
            return parent;
        }

        public void setParent(NodoSplay<T> parent) {
            this.parent = parent;
        }

    }

}



