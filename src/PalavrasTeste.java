import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PalavrasTeste {
	
	private List<String> palavras;
	
	/**
	 * Construtor da classe responsavel por ler o arquivo de palavras e montar a lista das mesmas.
	 */
	public PalavrasTeste(){
		String token1 = "";

	    Scanner inFile1;
	    palavras = new ArrayList<String>();
		try {
			inFile1 = new Scanner(new File("palavras.txt")).useDelimiter("\\n");
			while (inFile1.hasNext()) {
			      token1 = inFile1.next();
			      palavras.add(token1.trim().replace("\\n", ""));
			    }
			    inFile1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que monta cenario base de teste passado em aula.
	 * @return lista basica de palavras teste
	 */
	public List<String> getPalavrasBasico(){
		List<String> palavrasBasico = new ArrayList<>();
		palavrasBasico.add("a");
		palavrasBasico.add("bb");
		palavrasBasico.add("asa");
		palavrasBasico.add("asas");
		palavrasBasico.add("sas");
        palavrasBasico.add("sal");
        palavrasBasico.add("ala");
        palavrasBasico.add("sala");
        palavrasBasico.add("apendicite");
        palavrasBasico.add("abacate");
        palavrasBasico.add("bacatea");
        palavrasBasico.add("acateab");
        palavrasBasico.add("cateaba");
        palavrasBasico.add("ateabac");
        palavrasBasico.add("teabaca");
        palavrasBasico.add("eabacat");
        palavrasBasico.add("pequeno");
        palavrasBasico.add("aborigene");
        palavrasBasico.add("inconstitucionalissimamente");
        return palavras;
	}
	
	/**
	 * Metodo responsavel por retornar a sublista de todas as palavras ja lidas do arquivo.
	 * Tal retorno sera a sublista de palavras do inicio ate o tamanho especificado no parametro.
	 * Caso o tamanho ultrapasse a quantidade de itens na lista de palavras teste, o retorno será a 
	 * prórpia lista sem alterações.
	 * @param tamanho tamanho da lista pretendida
	 * @return lista de tamanho TAMANHO com palavras testes lidas do arquivo inicial 
	 */
	public List<String> getPalavras(int tamanho){
		if(tamanho > palavras.size()) tamanho = palavras.size();
		return palavras.subList(0, tamanho-1);
	}
	
	/**
	 * 
	 * @return lista com todas as palavras teste lidas do arquivo inicial
	 */
	public List<String> getPalavras(){
		return this.palavras;
	}
}
