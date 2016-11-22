public class Trie{

    private NoTrie raiz;

    public Trie(){
        raiz = new NoTrie();
    }

    /**
     * Adiciona filhos a raiz, onde cada um deles tera o valor de um caracter da palavra.
     * @param palavra
     */
    public void adicionarPalavra(String palavra){
        raiz.adicionarPalavra(palavra);
    }

    /**
     * A partir da raiz percorre todos os caracteres da arvore, verificando
     * se todos os do prefixo passado se encontram na arvore.
     * @param prefixo string a ser procurada nas palavras da trie
     * @return true se prefixo estiver em alguma palavra da trie e false
     * caso contrario
     */
    public boolean verificarPrefixo(String prefixo){
    	NoTrie noAtual = this.raiz;
        for(int i = 0; i < prefixo.length(); i++) {
            char proximoCaracter = prefixo.charAt(i);
          	noAtual = noAtual.getFilho(proximoCaracter);
            if(noAtual == null) {
                return false;
            }
        }
        return true;
    }
    
    public void imprime(){
    	for(NoTrie filho: raiz.getFilhos()){
    		filho.imprime();
    	}
    }
}