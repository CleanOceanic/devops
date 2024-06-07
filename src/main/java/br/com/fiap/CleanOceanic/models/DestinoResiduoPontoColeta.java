package br.com.fiap.CleanOceanic.models;

import br.com.fiap.CleanOceanic.controllers.dtos.destinoResiduoPontoColeta.DestinoResiduoPontoColetaRegisterDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_DESTINO_RESIDUO_PONTO_COLETA")
@SequenceGenerator(name = "SEQ_T_DESTINO_RESIDUO_PONTO_COLETA", sequenceName = "SEQ_T_DESTINO_RESIDUO_PONTO_COLETA", allocationSize = 1)
public class DestinoResiduoPontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_DESTINO_RESIDUO_PONTO_COLETA")
    @Column(name = "id_destino_residuo_ponto_coleta")
    private Long idDestinoResiduoPontoColeta;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destino_residuo")
    private DestinoResiduo destinoResiduo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_coleta")
    private PontoColeta pontoColeta;

    @Column(name = "dt_created_at")
    private LocalDateTime createdAt;

    public DestinoResiduoPontoColeta(DestinoResiduoPontoColetaRegisterDTO destinoResiduoPontoColetaRegisterDTO) {
        this.createdAt = LocalDateTime.now();
    }

}
