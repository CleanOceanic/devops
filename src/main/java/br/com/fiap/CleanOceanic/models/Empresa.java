package br.com.fiap.CleanOceanic.models;

import br.com.fiap.CleanOceanic.controllers.dtos.empresa.EmpresaRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.empresa.EmpresaUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_EMPRESA")
@SequenceGenerator(name = "SEQ_T_EMPRESA", sequenceName = "SEQ_T_EMPRESA", allocationSize = 1)
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_EMPRESA")
    @Column(name = "id_empresa")
    private Long idEmpresa;

    @Column(name = "ds_razao_social")
    private String razaoSocial;

    @Column(name = "ds_cnpj")
    private String cnpj;

    @Column(name = "ds_telefone")
    private String telefone;

    @Column(name = "ds_email")
    private String email;

    @Column(name = "vl_quantidade_funcionario")
    private int quantidadeFuncionario;

    @Column(name = "ds_setor")
    private String setor;

    @Column(name = "dt_created_at")
    private LocalDateTime createdAt;


    public Empresa(EmpresaRegisterDTO empresaRegisterDTO) {
        this.razaoSocial = empresaRegisterDTO.razaoSocial();
        this.cnpj = empresaRegisterDTO.cnpj();
        this.telefone = empresaRegisterDTO.telefone();
        this.email = empresaRegisterDTO.email();
        this.quantidadeFuncionario = empresaRegisterDTO.quantidadeFuncionario();
        this.setor = empresaRegisterDTO.setor();
        this.createdAt = LocalDateTime.now();
    }


    public void updateInformation(EmpresaUpdateDTO empresaUpdateDTO) {
        if (empresaUpdateDTO.razaoSocial() != null){
            this.razaoSocial = empresaUpdateDTO.razaoSocial();
        }

        if (empresaUpdateDTO.cnpj() != null) {
            this.cnpj = empresaUpdateDTO.cnpj();
        }

        if (empresaUpdateDTO.telefone() != null) {
            this.telefone = empresaUpdateDTO.telefone();
        }

        if (empresaUpdateDTO.email() != null) {
            this.email = empresaUpdateDTO.email();
        }

        if (empresaUpdateDTO.quantidadeFuncionario() != 0) {
            this.quantidadeFuncionario = empresaUpdateDTO.quantidadeFuncionario();
        }

        if (empresaUpdateDTO.setor() != null) {
            this.setor = empresaUpdateDTO.setor();
        }
    }

}
