package br.csi.gg_store.model.usuario;

import java.util.Date;
public record DadosUsuario(Long id, String login, String permissao, String cpf, Date dataNascimento) {
    public DadosUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getLogin(), usuario.getPermissao(), usuario.getCpf(), usuario.getDataNascimento());
    }
}
