package DB.BDO;

public class Musica implements Cloneable, Comparable<Musica> {
	
	private int id,duracao;
	private String titulo,artista,estilo;
	private double preco;
	
	public static boolean valida(int id, String titulo, String artista, String estilo, int duracao, double preco) {
		if(id < 0)
			return false;
		if(titulo == null || titulo.length() > 80)
			return false;
		if(artista == null || artista.length() > 60)
			return false;
		if(estilo == null || estilo.length() > 30)
			return false;
		if(duracao < 0 || duracao > 500)
			return false;
		if(preco < 0 || preco > 99.99)
			return false;
		
		return true;
	}
	
	public Musica(int id, String titulo, String artista, String estilo, int duracao, double preco)throws Exception {
		if(!Musica.valida(id, titulo, artista, estilo, duracao, preco))
			throw new Exception ("Musica Invalida!");
		
		this.id = id;
		this.duracao = duracao;
		this.titulo = titulo;
		this.artista = artista;
		this.estilo = estilo;
		this.preco = preco;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id)throws Exception {
		if(!Musica.valida(id, titulo, artista, estilo, duracao, preco))
			throw new Exception ("ID Invalido!");
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo)throws Exception {
		if(!Musica.valida(id, titulo, artista, estilo, duracao, preco))
			throw new Exception("Titulo Invalido!");
		this.titulo = titulo;
	}
	
	public String getArtista() {
		return artista;
	}
	
	public void setArtista(String artista)throws Exception {
		if(!Musica.valida(id, titulo, artista, estilo, duracao, preco))
			throw new Exception("Artista Invalido!");
		this.artista = artista;
	}
	
	public String getEstilo() {
		return estilo;
	}
	
	public void setEstilo(String estilo)throws Exception {
		if(!Musica.valida(id, titulo, artista, estilo, duracao, preco))
			throw new Exception("Estilo Invalido");
		this.estilo = estilo;
	}
	
	public int getDuracao() {
		return duracao;
	}
	
	public void setDuracao(int duracao)throws Exception{
		if(!Musica.valida(id, titulo, artista, estilo, duracao, preco))
			throw new Exception("Duracao Invalida");
		this.duracao = duracao;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco)throws Exception {
		if(!Musica.valida(id, titulo, artista, estilo, duracao, preco))
			throw new Exception("Preco Invalido!");
		this.preco = preco;
	}
	
	public String toString() {
		int duracao = this.duracao/60;
		int minutos = 0, segundos = 0;
		if(duracao > 1){
			segundos = this.duracao%60;
		}else{
			segundos = duracao;
		}
		
		return  "ID: " + this.id +
				"Musica: " + this.titulo +
				"\nArtista: " + this.artista +
				"\nEstilo: " + this.estilo +
				"\nDura��o: " + minutos + ":" + segundos +
				"\nPre�o R$: " + this.preco;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Musica musica = (Musica) obj;
		
		if (this.id != musica.id)
			return false;
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
		
		ret = ret * 5 + new Integer(this.id).hashCode();
		ret = ret * 5 + new Integer(this.titulo).hashCode();
		ret = ret * 5 + new Integer(this.artista).hashCode();
		ret = ret * 5 + new Integer(this.estilo).hashCode();
		ret = ret * 5 + new Integer(this.duracao).hashCode();
		ret = ret * 5 + new Integer((int)this.preco).hashCode();
		
		return ret;
	}
	
	public Musica(Musica modelo)throws Exception {
		if(modelo == null)
			throw new Exception("Modelo Invalido!");
		
		this.id = modelo.id;
		this.titulo = modelo.titulo;
		this.artista = modelo.artista;
		this.estilo = modelo.estilo;
		this.duracao = modelo.duracao;
		this.preco = modelo.preco;
	}
	
	public Object clone () {
		Musica ret = null;
		try {
			ret = new Musica(this);
		}catch (Exception ex) {
		}
		return ret;
	}
	
	public int compareTo(Musica musica) {
		if(this.id < musica.id)
			return -999;
		if(this.id > musica.id)
			return 999;
		if(this.titulo.length() < musica.titulo.length())
			return -999;
		if(this.titulo.length() > musica.titulo.length())
			return 999;
		if(this.artista.length() < musica.artista.length())
			return -999;
		if(this.artista.length() > musica.artista.length())
			return 999;
		if(this.estilo.length() < musica.estilo.length())
			return -999;
		if(this.estilo.length() > musica.estilo.length())
			return 999;
		if(this.duracao < musica.duracao)
			return -999;
		if(this.duracao > musica.duracao)
			return 999;
		if(this.preco < musica.preco)
			return -999;
		if(this.preco > musica.preco)
			return 999;
		
		return 0;
	}
}