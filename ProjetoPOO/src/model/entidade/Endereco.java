package model.entidade;

public record Endereco(
		String cep,
		String logradouro, 
		String bairro, 
		String cidade, 
		String uf, 
		int numero, 
		String pais
		) {
	
	public String fullEndereco() {
		return logradouro + " " + numero + " - " + bairro + " - " + cidade + " " + uf + " - " + pais;
	}
}
