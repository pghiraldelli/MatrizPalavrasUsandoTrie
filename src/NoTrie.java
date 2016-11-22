import java.util.ArrayList;
import java.util.List;

public class NoTrie {

    private char valor;
    private List<NoTrie> filhos;

    /**
     * Inicializador de no de Trie cujo valor nao foi especificado.
     */
    public NoTrie() {
        this.filhos = new ArrayList<NoTrie>();
    }

    /**
     * Inicializador de no Trie cujo valor foi especificado.
     * @param valor
     */
    public NoTrie(char valor) {
        this();
        this.valor = valor;
    }

    public char getValor(){
    	return this.valor;
    }
    
    /**
     * Retorna lista com todos os filhos do NoTrie.
     * @return
     */
    public List<NoTrie> getFilhos(){
    	return this.filhos;
    }

    /**
     * Retorna filho cujo valor corresponde ao caracter passado como parametro.
     * Caso nao encontre filho algum eh retornado null.
     * @param caracter
     * @return
     */
    public NoTrie getFilho(char caracter){
        for(NoTrie filho : this.filhos){
        	if(filho.getValor() == caracter){
        		return filho;
        	}
        }
        return null;
    }

    /**
     * Cria filhos consecutivos para cada caracter da palavra passada como parametro.
     * Caso o filho ja exista, continua procurando seus filhos para adicionar os caracteres.
     * @param palavra
     */
    public void adicionarPalavra(String palavra) {
        if (palavra.isEmpty()) {
            return;
        }

        char caracter = palavra.charAt(0);
        NoTrie filho = getFilho(caracter);
        if (filho == null) {
            filho = new NoTrie(caracter);
            this.filhos.add(filho);
        }
        filho.adicionarPalavra(palavra.substring(1));
    }
    
    public void imprime(){
    	for(NoTrie filho: filhos){
    		System.out.println(filho.getValor());
    		filho.imprime();
    	}
    }
}