package br.csi.gg_store.model.usuario;

import java.util.Date;
public record DadosUsuario(Long id, String login, String permissao,String nome, String cpf, Date dataNascimento) {
    public DadosUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getLogin(), usuario.getPermissao(), usuario.getNome(), usuario.getCpf(), usuario.getDataNascimento());
    }
}
