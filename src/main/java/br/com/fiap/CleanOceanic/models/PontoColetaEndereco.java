package br.com.fiap.CleanOceanic.models;

import br.com.fiap.CleanOceanic.controllers.dtos.pontoColetaEndereco.PontoColetaEnderecoRegisterDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_PONTO_COLETA_ENDERECO")
@SequenceGenerator(name = "SEQ_T_PONTO_COLETA_ENDERECO", sequenceName = "SEQ_T_PONTO_COLETA_ENDERECO", allocationSize = 1)
public class PontoColetaEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_PONTO_COLETA_ENDERECO")
    @Column(name = "id_ponto_coleta_endereco")
    private Long idPontoColetaEndereco;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_coleta")
    private PontoColeta pontoColeta;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @Column(name = "dt_created_at")
    private LocalDateTime createdAt;

    public PontoColetaEndereco(PontoColetaEnderecoRegisterDTO pontoColetaEnderecoRegisterDTO) {
        this.createdAt = LocalDateTime.now();
    }

}
