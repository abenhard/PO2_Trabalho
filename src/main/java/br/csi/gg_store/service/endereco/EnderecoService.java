package br.csi.gg_store.service.endereco;

import br.csi.gg_store.model.endereco.*;
import br.csi.gg_store.model.usuario.Usuario;
import br.csi.gg_store.model.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {
    private final EnderecoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final CidadeRepository cidadeRepository;
    private final UFRepository ufRepository;

    public EnderecoService(EnderecoRepository repository, CidadeRepository cidadeRepository, UFRepository ufRepository, UsuarioRepository usuarioRepository){
        this.repository = repository;
        this.cidadeRepository = cidadeRepository;
        this.ufRepository = ufRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void cadastrar(List<EnderecoDTO> enderecoDTOs)
    {
        for(EnderecoDTO  enderecoDTO:  enderecoDTOs)
        {
            Endereco endereco = new Endereco();
            endereco.setRua(enderecoDTO.getRua());
            endereco.setBairro(enderecoDTO.getBairro());
            endereco.setComplemento(enderecoDTO.getComplemento());
            endereco.setCep(enderecoDTO.getCep());
            endereco.setNumero(enderecoDTO.getNumero());


            UF uf =  ufRepository.getUFByNome(enderecoDTO.getUf()).get(0);
            endereco.setCidade(cidadeRepository.getCidadeByNomeOrUf(enderecoDTO.getCidade(), uf).get(0));

            Usuario usuario = usuarioRepository.findByLogin(enderecoDTO.getLogin());
            endereco.setUsuario(usuario);

            this.repository.save(endereco);
        }
    }

    public List<EnderecoDTO> listar(){
        List<EnderecoDTO> enderecoDTOS = new ArrayList<>();
        List<Endereco> enderecos = this.repository.findAll();

        for(Endereco endereco: enderecos)
        {
            EnderecoDTO enderecoDTO = new EnderecoDTO(
                    endereco.getRua(), endereco.getBairro(), endereco.getComplemento(),
                    endereco.getCep(), endereco.getNumero(), endereco.getCidade().getNome(),
                    endereco.getUsuario().getLogin(), endereco.getCidade().getUf().getNome()
            );
            enderecoDTO.setId(endereco.getId().toString());

            enderecoDTOS.add(enderecoDTO);
        }

        return enderecoDTOS;
    }

    public Endereco findById(Long id){
        return this.repository.findById(id).get();
    }
    public Endereco findByCep(String cep){ return this.repository.findByCep(cep).get();}
    public void atualizar(Endereco endereco){
       Endereco enderecoSalvar = this.repository.getReferenceById(endereco.getId());
        enderecoSalvar.setRua(endereco.getRua());
        enderecoSalvar.setBairro(endereco.getBairro());
        enderecoSalvar.setCep(endereco.getCep());
        enderecoSalvar.setComplemento(endereco.getComplemento());
        enderecoSalvar.setCidade(endereco.getCidade());
    }
    public void excluir(Long id){
        this.repository.deleteById(id);
    }
}
