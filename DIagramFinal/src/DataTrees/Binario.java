package DataTrees;
import java.lang.Exception;

public class Binario<AnyType extends Comparable<? super AnyType>>
{
	
	private BinaryNode<AnyType> root;

	/**Crea una raiz nula cuando se instancia el arbol
	 * */
	public Binario( )
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
	
	/**Metodo contiene publico
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
		return encontrarMin( root ).element;
	}
	
	/**
	* Encuentra el elemento mas grande de un arbol
	* @exception java.lang.Exception
	* @return el nodo mayor
	*/
	public AnyType encontrarMax( ) throws Exception
	{ 
		if( esLimpio( ) ) throw new Exception( );
		return encontrarMax( root ).element;
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
	public void removerNodo( AnyType x )
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

///////////////////////METODOS INTERNOS DE LA CLASE//////////////////////////////////////////
	
	/**Clase Nodo
	 * @param Data corresponde a la informacion del nodo
	 * @return crea el nodo 
	 * */
	private static class BinaryNode<AnyType>
	{ 
		AnyType element; // La informacion en el nodo
		BinaryNode<AnyType> left; // Hijo izquierdo
		BinaryNode<AnyType> right; // Hijo derecho
		
		BinaryNode( AnyType Data )
		{ 
			this( Data, null, null ); 
		}
		
		BinaryNode( AnyType Data, BinaryNode<AnyType> lr, BinaryNode<AnyType> rr )
		{ 
			element = Data; 
			left = lr; 
			right = rr; 
		}
	}
	
	/**Metodo contiene privado
	 * @param x corresponde a lo que se desea buscar 
	 * @param r corresponde a la raiz
	 * @return true si lo encuentra y false si pasa lo contrario
	 * */
	private boolean contiene( AnyType x, BinaryNode<AnyType> r )
	{
		if( r == null )
			return false;
		int compareResult = x.compareTo( r.element );
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
	private BinaryNode<AnyType> encontrarMin( BinaryNode<AnyType> r )
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
	private BinaryNode<AnyType> encontrarMax( BinaryNode<AnyType> r )
	{
		if( r != null )
			while( r.right != null )
				r = r.right;
		return r;
	}
	
	/**
	* Inserta un nodo en el lugar especifico por su correcta posicion, metodo privado
	* @param r corresponde a la raiz del arbol
	* @param x corresponde al nodo por insertar
	* @return el arbol con el nodo insertado
	*/
	private BinaryNode<AnyType> insertarNodo( AnyType x, BinaryNode<AnyType> r )
	{ 
		if( r == null ){
			return new BinaryNode<>( x, null, null );
		}
		int compareResult = x.compareTo( r.element );

		if( compareResult < 0 ){
			r.left = insertarNodo( x, r.left );
		}
		else if( compareResult > 0 )
			r.right = insertarNodo( x, r.right );
		else
			; // Duplicate; do nothing
		return r; 
	}
	
	/**Se encarga de eliminar un nodo especifico, metodo privado
	 * @param x nodo a eliminar
	 * @param r nodo raiz del arbol
	 * @return el arbol sin el nodo
	 * */
	private BinaryNode<AnyType> removerNodo( AnyType x, BinaryNode<AnyType> r )
	{ 

		if( r == null )
			return r;

		int compareResult = x.compareTo( r.element );

		if( compareResult < 0 )
			r.left = removerNodo( x, r.left );
		else if( compareResult > 0 )
			r.right = removerNodo( x, r.right );
		else if( r.left != null && r.right != null ) // Two children
		{
			r.element = encontrarMin( r.right ).element;
			r.right = removerNodo( r.element, r.right );
		}
		else
			r = ( r.left != null ) ? r.left : r.right;
		return r;

	}
	
	/**
	 * Se encarga de imprimir los datos de los nodos en orden
	 * @param nodo corresponde a la raiz del arbol a imprimir*/
	private void ayudanteInorden(BinaryNode<AnyType> nodo)
	{
		if (nodo == null)
			return;

		ayudanteInorden(nodo.left);
		System.out.print(nodo.element + " ");
		ayudanteInorden(nodo.right);
	}
}