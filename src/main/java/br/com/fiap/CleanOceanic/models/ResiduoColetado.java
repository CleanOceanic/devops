package br.com.fiap.CleanOceanic.models;

import br.com.fiap.CleanOceanic.controllers.dtos.residuoColetado.ResiduoColetadoRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.residuoColetado.ResiduoColetadoUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "SEQ_T_RESIDUO_COLETADO", sequenceName = "SEQ_T_RESIDUO_COLETADO", allocationSize = 1)
@Table(name = "T_RESIDUO_COLETADO")
public class ResiduoColetado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_RESIDUO_COLETADO")
    @Column(name = "id_residuo_coletado")
    private Long idResiduoColetado;

    @Column(name = "ds_tipo")
    private String tipo;

    @Column(name = "vl_peso")
    private double peso;

    @Column(name = "dt_data_coleta")
    private String dataColeta;

    @Column(name = "ds_local_coleta")
    private String localColeta;

    @Column(name = "ds_origem_residuo")
    private String origemResiduo;

    @Column(name = "ds_observacao_coleta")
    private String observacaoColeta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "dt_created_at")
    private LocalDateTime createdAt;

    public ResiduoColetado(ResiduoColetadoRegisterDTO residuoColetadoRegisterDTO) {
        this.tipo = residuoColetadoRegisterDTO.tipo();
        this.peso = residuoColetadoRegisterDTO.peso();
        this.dataColeta = residuoColetadoRegisterDTO.dataColeta();
        this.localColeta = residuoColetadoRegisterDTO.localColeta();
        this.origemResiduo = residuoColetadoRegisterDTO.origemResiduo();
        this.observacaoColeta = residuoColetadoRegisterDTO.observacaoColeta();
        this.createdAt = LocalDateTime.now();
    }

    public void updateInformation(ResiduoColetadoUpdateDTO residuoColetadoUpdateDTO) {
        if (residuoColetadoUpdateDTO.tipo() != null) {
            this.tipo = residuoColetadoUpdateDTO.tipo();
        }

        if (residuoColetadoUpdateDTO.peso() != 0) {
            this.peso = residuoColetadoUpdateDTO.peso();
        }

        if (residuoColetadoUpdateDTO.dataColeta() != null) {
            this.dataColeta = residuoColetadoUpdateDTO.dataColeta();
        }

        if (residuoColetadoUpdateDTO.localColeta() != null) {
            this.localColeta = residuoColetadoUpdateDTO.localColeta();
        }

        if (residuoColetadoUpdateDTO.origemResiduo() != null) {
            this.origemResiduo = residuoColetadoUpdateDTO.origemResiduo();
        }

        if (residuoColetadoUpdateDTO.observacaoColeta() != null) {
            this.observacaoColeta = residuoColetadoUpdateDTO.observacaoColeta();
        }
    }
}
