package Client.Util;

public class Musica implements Cloneable, Comparable<Musica> {

	private int duracao;
	private String titulo, artista, estilo;
	private double preco;

	public static boolean valida(String titulo, String artista, String estilo, int duracao, double preco) {
		if (titulo == null || titulo.length() > 80)
			return false;
		if (artista == null || artista.length() > 60)
			return false;
		if (estilo == null || estilo.length() > 30)
			return false;
		if (duracao < 0 || duracao > 500)
			return false;
		if (preco < 0 || preco > 99.99)
			return false;

		return true;
	}

	public Musica(String titulo, String artista, String estilo, int duracao, double preco) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Musica Invalida!");

		this.duracao = duracao;
		this.titulo = titulo;
		this.artista = artista;
		this.estilo = estilo;
		this.preco = preco;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Titulo Invalido!");
		this.titulo = titulo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Artista Invalido!");
		this.artista = artista;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Estilo Invalido");
		this.estilo = estilo;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Duracao Invalida");
		this.duracao = duracao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Preco Invalido!");
		this.preco = preco;
	}

	public String toString() {
		return (this.duracao / 60 < 10 ? "0" : "") + this.duracao / 60 + ":" + (this.duracao % 60 < 10 ? "0" : "")
				+ this.duracao % 60 + " " + this.titulo + " - " + this.artista + " - " + this.estilo + "   R$: "
				+ this.preco;
	}

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

	public int hashCode() {
		int ret = 999;

		ret = ret * 5 + new Integer(this.titulo).hashCode();
		ret = ret * 5 + new Integer(this.artista).hashCode();
		ret = ret * 5 + new Integer(this.estilo).hashCode();
		ret = ret * 5 + new Integer(this.duracao).hashCode();
		ret = ret * 5 + new Integer((int) this.preco).hashCode();

		return ret;
	}

	public Musica(Musica modelo) throws Exception {
		if (modelo == null)
			throw new Exception("Modelo Invalido!");

		this.titulo = modelo.titulo;
		this.artista = modelo.artista;
		this.estilo = modelo.estilo;
		this.duracao = modelo.duracao;
		this.preco = modelo.preco;
	}

	public Object clone() {
		Musica ret = null;
		try {
			ret = new Musica(this);
		} catch (Exception ex) {
		}
		return ret;
	}

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