package DataTrees;
import java.lang.Exception;

/**
 * Clase del arbol Avl
 * */
public class AVL<AnyType extends Comparable<? super AnyType>>
{
	private NodoAvl<AnyType> root;
	
	/**Crea una raiz nula cuando se instancia el arbol
	 * */
	public AVL( )
	{ 
		root = null; 
	}
	
	/**Elimina todo del arbol*/
	public void limpiarArbol( )
	{ 
		root = null; 
	}

	/**Se encarga de revisar si esta vacio el arbol
	 * @return true si esta limpio de lo contrario false
	 * */
	public boolean esLimpio( )
	{ 
		return root == null;
	}
	
	/**Metodo contiene
	 * @param x corresponde a lo que se desea buscar 
	 * @return true si lo encuentra y false si pasa lo contrario
	 * */
	public boolean contiene( AnyType x )
	{ 
		return contiene( x, root ); 
	}
	
	/**
	* Encuentra el elemento mas pequeno de un arbol
	* @exception java.lang.Exception
	* @return el nodo menor
	*/	
	public AnyType encontrarMin( ) throws Exception
	{ 
		if( esLimpio( ) ) throw new Exception( );
		return encontrarMin( root ).dato;
	}
	
	/**
	* Encuentra el elemento mas grande de un arbol
	* @exception java.lang.Exception
	* @return el nodo mayor
	*/
	public AnyType encontrarMax( ) throws Exception
	{ 
		if( esLimpio( ) ) throw new Exception( );
		return encontrarMax( root ).dato;
	}
	
	/**
	* Inserta un nodo en el lugar especifico por su correcta posicion
	* @param x corresponde al nodo por insertar
	*/
	public void insertarNodo( AnyType x )
	{ 
		root = insertarNodo( x, root ); 
	}
	
	/**Se encarga de eliminar un nodo especifico
	 * @param x nodo a eliminar
	 * */
	public void eliminarNodo( AnyType x )
	{ 
		root = removerNodo( x, root ); 
	}
	
	/**
	 * Imprime el arbol, es privado
	 * @return el arbol
	 */
	public synchronized void imprimirArbol()
	{
		ayudanteInorden(root);
	}
	
///////////////////////METODOS INTERNOS DE LA CLASE/////////////////////////////////////////////
	
	/**Clase NodoAVL
	 * @param Data corresponde a la informacion del nodo
	 * @return crea el nodo 
	 * */
	private static class NodoAvl<AnyType>
	{
		AnyType dato; // The data in the node
		NodoAvl<AnyType> left; // Left child
		NodoAvl<AnyType> right; // Right child
		int height; // Height

		NodoAvl( AnyType theElement )
		{ 
			this( theElement, null, null ); 
		}
		NodoAvl( AnyType theElement, NodoAvl<AnyType> lAVL, NodoAvl<AnyType> rAVL)
		{ 
			dato = theElement; 
			left = lAVL; 
			right = rAVL; 
			height = 0; 
		}
	}
	
	/**Metodo contiene privado
	 * @param x corresponde a lo que se desea buscar 
	 * @param r corresponde al nodo que desea buscar
	 * @return true si lo encuentra y false si pasa lo contrario
	 * */
	private boolean contiene( AnyType x, NodoAvl<AnyType> r )
	{
		if( r == null )
			return false;
		int compareResult = x.compareTo( r.dato );
		if( compareResult < 0 )
			return contiene( x, r.left );
		else if( compareResult > 0 )
			return contiene( x, r.right );
		else
			return true;
	}
	
	/**
	* Encuentra el elemento mas pequeno de un arbol, es privado
	* @param r corresponde a la raiz del arbol
	* @return el nodo menor
	*/
	private NodoAvl<AnyType> encontrarMin( NodoAvl<AnyType> r )
	{ 
		if( r != null )
			while( r.left != null )
				r = r.left;
		return r; 
	}
	
	/**
	* Encuentra el elemento mas grande de un arbol, es privado
	* @param r corresponde a la raiz del arbol
	* @return el nodo mayor
	*/
	private NodoAvl<AnyType> encontrarMax( NodoAvl<AnyType> r )
	{
		if( r != null )
			while( r.right != null )
				r = r.right;
		return r;
	}
	
	/**
	* Inserta un nodo en el lugar especifico 
	* @param r corresponde a la raiz del arbol
	* @return el nodo a insertar
	*/
	private NodoAvl<AnyType> insertarNodo( AnyType x, NodoAvl<AnyType> r )
	{ 
		if( r == null )
			return new NodoAvl<AnyType>( x, null, null );

		int compareResult = x.compareTo( r.dato );
		
		if( compareResult < 0 )
			r.left = insertarNodo( x, r.left );
		else if( compareResult > 0 )
			r.right = insertarNodo( x, r.right );
		else
			; 
		return balancear( r );
		

	}
	
	/**Se encarga de eliminar un nodo especifico
	 * @param x nodo a eliminar
	 * @param r nodo raiz del arbol
	 * @return el arbol sin el nodo
	 * */
	private NodoAvl<AnyType> removerNodo( AnyType x, NodoAvl<AnyType> r )
	{ 

		if( r == null )
			return r; // Item not found; do nothing

		int compareResult = x.compareTo( r.dato );

		if( compareResult < 0 )
			
			r.left = removerNodo( x, r.left );
		else if( compareResult > 0 )
			r.right = removerNodo( x, r.right );
		else if( r.left != null && r.right != null ) // Two children
		{
			r.dato = encontrarMin( r.right ).dato;
			r.right = removerNodo( r.dato, r.right );
		}
		else
			r = ( r.left != null ) ? r.left : r.right;
		return balancear( r );

	}

	/**
	 * Imprime el arbol, es privado
	 * @param nodo corresponde a la raiz
	 * @return la lista de string de nodos acomodados por orden creciente
	 */
		
	private void ayudanteInorden(NodoAvl nodo)
	{
		if (nodo == null)
			return;

		ayudanteInorden(nodo.left);
		System.out.print(nodo.dato + " ");
		ayudanteInorden(nodo.right);
	}

	/**
	 * Se encarga de devolver el arbol en string
	 * @param nodo corresponde al nodo raiz
	 * @return el string de cada dato del arbol en desoren*/
	private void ayudantePosorden(NodoAvl nodo)

	{
		if (nodo == null)
			return;

		ayudantePosorden(nodo.left);
		ayudantePosorden(nodo.right);
		System.out.print(nodo.dato + " ");

	}

	/**
	 * Busca longitud en un arbol
	 * @return el largo del arbol en entero o -1 si esta vacio
	 */
	private int height(NodoAvl<AnyType> r )
	{
		return r == null ? -1 : r.height;
	}
	
	private static final int ALLOWED_IMBALANCE = 1;

	/**
	 * Coloca correctamente los nodos
	 * @param r corresponde a la raiz
	 * @return arbol balanceado
	 */
	private NodoAvl<AnyType> balancear( NodoAvl<AnyType> r )
	{
		if( r == null )
			return r;

		if( height( r.left ) - height( r.right ) > ALLOWED_IMBALANCE )
			if( height( r.left.left ) >= height( r.left.right ) )
				r = rotHijIz( r );
			else
				r = doubleWitIz( r );
		else
			if( height( r.right ) - height( r.left ) > ALLOWED_IMBALANCE )
				if( height( r.right.right ) >= height( r.right.left ) )
					r = rotHijDer( r );
				else
					r = doubleWitDer( r );

		r.height = Math.max( height( r.left ), height( r.right ) ) + 1;
		return r;
	}

	/**
	 * Cambia el arbol binario con el hijo izquierdo
	 * @return actualiza la longitud y retorna el arbol combiando posiciones de hijos
	 */
	private NodoAvl<AnyType> rotHijIz( NodoAvl<AnyType> k2 )
	{
		NodoAvl<AnyType> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
		k1.height = Math.max( height( k1.left ), k2.height ) + 1;
		return k1;
	}
	
	/**
	 * Cambia el arbol binario con el hijo derecho
	 * @param el nodo para hacer los cambios de posicion
	 * @return actualiza la longitud y retorna el arbol combiando posiciones de hijos
	 */
	private NodoAvl<AnyType> rotHijDer( NodoAvl<AnyType> k2 )
	{
		NodoAvl<AnyType> k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;
		k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
		k1.height = Math.max( height( k1.right ), k2.height ) + 1;
		return k1;
	}
	
	/**
	 * Rotacion doble primero hijo izquierdo
	 * @return rota doblemente los hijos retorna el arbol con los cambios
	 */
	private NodoAvl<AnyType> doubleWitIz( NodoAvl<AnyType> k3 )
	{
		k3.left = rotHijDer( k3.left );
		return rotHijIz( k3 );
	}

	/**
	 * Rotacion doble primero hijo derecho
	 * @return rota doblemente los hijos retorna el arbol con los cambios
	 */
	private NodoAvl<AnyType> doubleWitDer( NodoAvl<AnyType> k3 )
	{
		k3.right = rotHijDer( k3.right );
		return rotHijIz( k3 );
	}
	
}
