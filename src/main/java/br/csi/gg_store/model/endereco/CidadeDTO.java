package br.csi.gg_store.model.endereco;


public record CidadeDTO(String nome, Long iduf, String estado){
    public CidadeDTO(Cidade cidade)
    {
        this(cidade.getNome() , cidade.getUf().getId(), cidade.getUf().getNome());
    }
    public Long getIduf(){return iduf;}

}
