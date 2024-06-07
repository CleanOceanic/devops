package br.com.fiap.CleanOceanic.models;

import br.com.fiap.CleanOceanic.controllers.dtos.endereco.EnderecoRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.endereco.EnderecoUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_ENDERECO")
@SequenceGenerator(name = "SEQ_T_ENDERECO", sequenceName = "SEQ_T_ENDERECO", allocationSize = 1)
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_ENDERECO")
    @Column(name = "id_endereco")
    private Long idEndereco;

    @Column(name = "ds_logradouro")
    private String logradouro;

    @Column(name = "ds_numero")
    private String numero;

    @Column(name = "ds_bairro")
    private String bairro;

    @Column(name = "ds_cidade")
    private String cidade;

    @Column(name = "ds_sigla")
    private String sigla;

    @Column(name = "ds_cep")
    private String cep;

    @Column(name = "dt_created_at")
    private LocalDateTime createdAt;

    public Endereco(EnderecoRegisterDTO enderecoRegisterDTO) {
        this.logradouro = enderecoRegisterDTO.logradouro();
        this.numero = enderecoRegisterDTO.numero();
        this.bairro = enderecoRegisterDTO.bairro();
        this.cidade = enderecoRegisterDTO.cidade();
        this.sigla = enderecoRegisterDTO.sigla();
        this.cep = enderecoRegisterDTO.cep();
        this.createdAt = LocalDateTime.now();
    }

    public void updateInformation(EnderecoUpdateDTO enderecoUpdateDTO) {
        if (enderecoUpdateDTO.logradouro() != null){
            this.logradouro = enderecoUpdateDTO.logradouro();
        }

        if (enderecoUpdateDTO.numero() != null) {
            this.numero = enderecoUpdateDTO.numero();
        }

        if (enderecoUpdateDTO.cep() != null) {
            this.cep = enderecoUpdateDTO.cep();
        }

        if (enderecoUpdateDTO.bairro() != null) {
            this.bairro = enderecoUpdateDTO.bairro();
        }

        if (enderecoUpdateDTO.cidade() != null) {
            this.cidade = enderecoUpdateDTO.cidade();
        }

        if (enderecoUpdateDTO.sigla() != null) {
            this.sigla = enderecoUpdateDTO.sigla();
        }
    }

}
