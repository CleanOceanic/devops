package br.com.fiap.CleanOceanic.models;

import br.com.fiap.CleanOceanic.controllers.dtos.empresaEndereco.EmpresaEnderecoRegisterDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_EMPRESA_ENDERECO")
@SequenceGenerator(name = "SEQ_T_EMPRESA_ENDERECO", sequenceName = "SEQ_T_EMPRESA_ENDERECO", allocationSize = 1)
public class EmpresaEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_EMPRESA_ENDERECO")
    @Column(name = "id_empresa_endereco")
    private Long idEmpresaEndereco;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @Column(name = "dt_created_at")
    private LocalDateTime createdAt;

    public EmpresaEndereco(EmpresaEnderecoRegisterDTO empresaEnderecoRegisterDTO) {
        this.createdAt = LocalDateTime.now();
    }

}
