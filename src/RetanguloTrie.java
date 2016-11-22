import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class RetanguloTrie {

    List<Set<String>> palavrasPorTamanho;
    int maxTamanho;
    List<Trie> tries;
    
    public RetanguloTrie(List<String> palavras) {
        preProcessarPalavras(palavras);
    }

    private void preProcessarPalavras(List<String> palavras) {
        palavrasPorTamanho = new ArrayList<>();
        tries = new ArrayList<Trie>();
        maxTamanho = 0;
        for (String palavra : palavras) {
            int tamanho = palavra.length();
            this.maxTamanho = Math.max(tamanho, maxTamanho);
            while (palavrasPorTamanho.size() <= tamanho) {
                palavrasPorTamanho.add(null);
                tries.add(null);
            }
            Set<String> conjuntoPalavras = palavrasPorTamanho.get(tamanho);
            if (conjuntoPalavras == null) {
                conjuntoPalavras = new HashSet<>();
                palavrasPorTamanho.set(tamanho, conjuntoPalavras);
            }
            conjuntoPalavras.add(palavra);
        }
    }

    public List<String> encontrarMaiorMatriz() {
        int area = maxTamanho * maxTamanho;
        while (area > 0) {
            for (int nLinhas = maxTamanho; nLinhas > 0; nLinhas--) {
               int nColunas = area / nLinhas;
               if (nColunas > nLinhas) {
                   break;  // queremos linhas >= colunas
               }
               if (nLinhas * nColunas != area) {
                   continue;
               }
               List<String> resultado = encontrarMatriz(
                       nLinhas, nColunas);
               if (resultado != null) {
                   return resultado;
               }
            }
            area--;
        }
        return null;
    }

    private List<String> encontrarMatriz(int p, int q) {
        // preciso de p palavras de tamanho q
        List<String> retangulo = new ArrayList<>(p);
//        return expandirRetangulo(retangulo, p, q);
        return expandirRetanguloComPoda(retangulo, p, q);
    }

    private List<String> expandirRetanguloComPoda
            (List<String> retangulo, int p, int q) {
        // verifica se eh um estado valido! (fail early)
        if (!verificarRetangulo(retangulo, p)) {
            return null;
        }

        // verifica se eh um retangulo completo (estado final)
        if (retangulo.size() == p) {
            return retangulo;
        }

        // nao eh um retangulo completo ainda; vou expandir!
        Set<String> palavrasQ = palavrasPorTamanho.get(q);
        if (palavrasQ == null) {
            return null;
        }
        for (String palavraQ : palavrasQ) {
            // expande
            retangulo.add(palavraQ);

            // chama recursivamente
            List<String> resultado = expandirRetanguloComPoda(retangulo, p, q);
            if (resultado != null) {
                return resultado;
            }

            // limpa
            retangulo.remove(retangulo.size() - 1);
        }
        return null;
    }

    /**
     * Valida o retangulo informado.
     *
     * @param retangulo Lista com p palavras de tamanho q
     * @param tamanhoAlvo o tamanho desejado
     * @return true, se o retangulo contem q palavras, cada qual
     *               sendo o p-prefixo de alguma palavra validao
     *               com tamanhoAlvo caracteres;
     *         false, caso contrario
     */
    private boolean verificarRetangulo(List<String> retangulo,
                                       int tamanhoAlvo) {
        if (retangulo.size() == 0) {
            return true;
        }
        Set<String> palavrasP = palavrasPorTamanho.get(tamanhoAlvo);
        if (palavrasP == null) {
            return false;
        }
        StringBuffer sb = new StringBuffer();
        for (int coluna = 0; coluna < retangulo.get(0).length(); coluna++) {
            sb.setLength(0);
            for (int linha = 0; linha < retangulo.size(); linha++) {
                sb.append(retangulo.get(linha).charAt(coluna));
            }
            String palavra = sb.toString();
            if (tamanhoAlvo == retangulo.size()) {
                // nao estamos interessados em prefixos,
                // mas na palavra inteira!!
                if (!palavrasP.contains(palavra)) {
                    return false;
                }
            } else {
                // queremos na verdade saber se a palavra
                // referente a essa coluna eh prefixo de alguma palavra valida
                if (!verificarPrefixoUsandoTrie(palavra, tamanhoAlvo)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * cria nova Trie com palavras de tamanhoAlvo da lista palavrasPorTamanho
     * e adiciona na lista de tries.
     * @param tamanhoAlvo
     */
    private void inicializaTrie(int tamanhoAlvo) {
        if (tries.get(tamanhoAlvo) == null) {
        	tries.set(tamanhoAlvo, new Trie());
            for (String palavra : palavrasPorTamanho.get(tamanhoAlvo)) {
            	tries.get(tamanhoAlvo).adicionarPalavra(palavra);
            }
        }
    }
    
    /**
     * Verifica se a Trie de tamanhoAlvo possui filhos com valores do prefixo
     * @param prefixo
     * @param tamanhoAlvo
     * @return
     */
    private boolean verificarPrefixoUsandoTrie(String prefixo, int tamanhoAlvo) {
        inicializaTrie(tamanhoAlvo);
        return tries.get(tamanhoAlvo).verificarPrefixo(prefixo);
    }
    
    public static void imprimirRetangulo(List<String> retangulo) {
        if (retangulo == null) {
            System.out.println("Nao encontrou nada!");
        } else {
            for (String palavra : retangulo) {
                System.out.println(palavra);
            }
        }
    }
    
    public List<Trie> getTries(){
    	return this.tries;
    }

    public static void main(String args[]) {
    	PalavrasTeste teste = new PalavrasTeste(); 
        List<String> palavras = teste.getPalavras(4600);
        
        RetanguloTrie instancia = new RetanguloTrie(palavras);
        
        long tempoInicio = System.currentTimeMillis();
        
        List<String> resultado = instancia.encontrarMaiorMatriz();
        
        long tempoTotal = (System.currentTimeMillis()-tempoInicio);
        long tempoTotalSegundos = TimeUnit.MILLISECONDS.toSeconds(tempoTotal);
        System.out.println("Tempo Total: "+tempoTotalSegundos+"s");
        
        imprimirRetangulo(resultado);
    }

}