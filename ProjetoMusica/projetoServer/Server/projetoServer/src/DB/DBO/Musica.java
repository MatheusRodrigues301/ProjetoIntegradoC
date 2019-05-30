package DB.DBO;

/**
 * DBO Musica � a classe respons�vel por manipular as informa��es das m�sicas 
 * contidas no banco de dados.
 *  
 * @author Eduardo Oliveira e Matheus Andrey
 */
public class Musica implements Cloneable, Comparable<Musica> {
	private int duracao;
	private String titulo, artista, estilo;
	private double preco;

	/**
	 * M�todo que valida se as informa��es da m�sica est�o dentro do padr�o definido.
	 *
	 * @param titulo Titulo da m�sica.
	 * @param artista Banda/Cantor da m�sica.
	 * @param estilo Estilo musical.
	 * @param duracao Dura��o total da m�sica em segundos.
	 * @param preco Pre�o da m�sica em Reais.
	 * @return true Caso as informa��es estejam corretas retorna Verdadeiro;
	 */
	public static boolean valida(String titulo, String artista, String estilo, int duracao, double preco) {
		if (titulo == null || titulo.length() > 80)
			return false;
		if (artista == null || artista.length() > 60)
			return false;
		if (estilo == null || estilo.length() > 30)
			return false;
		if (duracao < 0 || duracao > 500)
			return false;
		if (preco < 0 || preco > 9.99)
			return false;

		return true;
	}

	/**
	 * M�todo que instancia uma nova m�sica caso a mesma possua dados validos.
	 *
	 * @param titulo Titulo da m�sica.
	 * @param artista Banda/Cantor(a) da m�sica.
	 * @param estilo Estilo da m�sica.
	 * @param duracao Dura��o total da m�sica em segundos.
	 * @param preco Pre�o da musica em Reais.
	 * @throws Exception Em caso de erro na valida��o dos dados lan�a uma exce��o.
	 */
	public Musica(String titulo, String artista, String estilo, int duracao, double preco) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Musica Invalida!");

		this.duracao = duracao;
		this.titulo = titulo;
		this.artista = artista;
		this.estilo = estilo;
		this.preco = preco;
	}

	/**
	 * M�todo que busca o titulo de determinada m�sica.
	 *
	 * @return Titulo da musica.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * M�todo que define um novo titulo para determinada m�sica.
	 *
	 * @param titulo Titulo da m�sica que ser� definido.
	 * @throws Exception Caso o novo titulo da m�sica seja inv�lido lan�a uma exce��o.
	 */
	public void setTitulo(String titulo) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Titulo inv�lido!");
		this.titulo = titulo;
	}

	/**
	 * M�todo que busca o nome da banda/cantor(a) de determinada m�sica.
	 *
	 * @return Nome da Banda/Cantor da m�sica.
	 */
	public String getArtista() {
		return artista;
	}

	/**
	 * M�todo que define um novo cantor(a)/banda para determinada m�sica.
	 *
	 * @param artista Nome da banda/cantor(a) que ser� definida.
	 * @throws Exception Caso o nome da nova banda/cantor da m�sica seja inv�lido lan�a uma exce��o.
	 */
	public void setArtista(String artista) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Artista inv�lido!");
		this.artista = artista;
	}

	/**
	 * M�todo que busca o estilo musical de determinada m�sica.
	 *
	 * @return Estilo musical.
	 */
	public String getEstilo() {
		return estilo;
	}

	/**
	 * M�todo que define um novo estilo musical para determinada m�sica.
	 *
	 * @param estilo Estilo musical que ser� definido.
	 * @throws Exception Caso o novo estilo musical n�o seja v�lido lan�a uma exce��o.
	 */
	public void setEstilo(String estilo) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Estilo inv�lido");
		this.estilo = estilo;
	}

	/**
	 * M�todo que busca o tempo de dura��o de determinada m�sica.
	 *
	 * @return o tempo de dura��o da m�sica.
	 */
	public int getDuracao() {
		return duracao;
	}

	/**
	 * M�todo que define uma nova dura��o para determinada m�sica.
	 *
	 * @param duracao Tempo de dura��o da musica que ser� definido.
	 * @throws Exception Caso o novo tempo de dura��o da m�sica esteja errado lan�a uma exce��o.
	 */
	public void setDuracao(int duracao) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Duracao Inv�lida");
		this.duracao = duracao;
	}

	/**
	 * M�todo que busca o pre�o de determinada m�sica.
	 *
	 * @return Pre�o da m�sica.
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * M�todo que define um novo pre�o para determinada m�sica.
	 *
	 * @param preco Pre�o da m�sica a ser definido.
	 * @throws Exception Caso o novo pre�o da m�sica seja inv�lido lan�a uma excess�o.
	 */
	public void setPreco(double preco) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Preco Inv�lido!");
		this.preco = preco;
	}
	/**
	 * M�todo que passa as informa��es da m�sica para formato de texto.
	 * 
	 * @return Retorna as informa��es da m�sica em formato de texto.
	 */
	public String toString() {
		return (this.duracao / 60 < 10 ? "0" : "") + this.duracao / 60 + ":" + (this.duracao % 60 < 10 ? "0" : "")
				+ this.duracao % 60 + " " + this.titulo + " - " + this.artista + " - " + this.estilo + "   R$: "
				+ this.preco;
	}
	/**
	 * M�todo que compara a igualdade da m�sica atual com outra m�sica.
	 * 
	 * @return true Caso ambos Objetos sejam iguais retorna Verdadeiro
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Musica musica = (Musica) obj;

		if (this.titulo != musica.titulo)
			return false;
		if (this.artista != musica.artista)
			return false;
		if (this.estilo != musica.estilo)
			return false;
		if (this.duracao != musica.duracao)
			return false;
		if (this.preco != musica.preco)
			return false;

		return true;
	}
	/**
	 * M�todo que gera um valor hash code para determinada m�sica.
	 * 
	 * @return Retorna um valor hash code de determinada m�sica.
	 */
	public int hashCode() {
		int ret = 999;

		ret = ret * 5 + new Integer(this.titulo).hashCode();
		ret = ret * 5 + new Integer(this.artista).hashCode();
		ret = ret * 5 + new Integer(this.estilo).hashCode();
		ret = ret * 5 + new Integer(this.duracao).hashCode();
		ret = ret * 5 + new Integer((int) this.preco).hashCode();

		return ret;
	}

	/**
	 * M�todo que clona uma m�sica ja existente.
	 *
	 * @param modelo Molde com parametros de uma musica ja existente.
	 * @throws Exception caso os dados da musica molde estejam incorretos lan�a uma exce��o. 
	 */
	public Musica(Musica modelo) throws Exception {
		if (modelo == null)
			throw new Exception("Modelo Inv�lido!");

		this.titulo = modelo.titulo;
		this.artista = modelo.artista;
		this.estilo = modelo.estilo;
		this.duracao = modelo.duracao;
		this.preco = modelo.preco;
	}
	/**
	 * M�todo que copia e gera uma m�sica igual a outra j� existente.
	 * 
	 * @return Retorna uma m�sica igual a outra j� existente.
	 */
	public Object clone() {
		Musica ret = null;
		try {
			ret = new Musica(this);
		} catch (Exception ex) {
		}
		return ret;
	}
	/**
	 * M�todo que compara a m�sica atual com outra para ver se ambas coincidem.
	 * 
	 * @return Retorna 0 caso as m�sicas sejam iguais.
	 */
	public int compareTo(Musica musica) {
		if (this.titulo.length() < musica.titulo.length())
			return -999;
		if (this.titulo.length() > musica.titulo.length())
			return 999;
		if (this.artista.length() < musica.artista.length())
			return -999;
		if (this.artista.length() > musica.artista.length())
			return 999;
		if (this.estilo.length() < musica.estilo.length())
			return -999;
		if (this.estilo.length() > musica.estilo.length())
			return 999;
		if (this.duracao < musica.duracao)
			return -999;
		if (this.duracao > musica.duracao)
			return 999;
		if (this.preco < musica.preco)
			return -999;
		if (this.preco > musica.preco)
			return 999;

		return 0;
	}
}