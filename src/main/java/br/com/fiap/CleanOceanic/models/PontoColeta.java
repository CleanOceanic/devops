package br.com.fiap.CleanOceanic.models;

import br.com.fiap.CleanOceanic.controllers.dtos.pontoColeta.PontoColetaRegisterDTO;
import br.com.fiap.CleanOceanic.controllers.dtos.pontoColeta.PontoColetaUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_PONTO_COLETA")
@SequenceGenerator(name = "SEQ_T_PONTO_COLETA", sequenceName = "SEQ_T_PONTO_COLETA", allocationSize = 1)
public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_PONTO_COLETA")
    @Column(name = "id_ponto_coleta")
    private Long idPontoColeta;

    @Column(name = "ds_nome")
    private String nome;

    @Column(name = "ds_telefone")
    private String telefone;

    @Column(name = "ds_email")
    private String email;

    @Column(name = "dt_horario_funcionamento")
    private String horarioFuncionamento;

    @Column(name = "vl_capacidade_maxima")
    private double capacidadeMaxima;

    @Column(name = "dt_created_at")
    private LocalDateTime createdAt;

    public PontoColeta(PontoColetaRegisterDTO pontoColetaRegisterDTO) {
        this.nome = pontoColetaRegisterDTO.nome();
        this.telefone = pontoColetaRegisterDTO.telefone();
        this.email = pontoColetaRegisterDTO.email();
        this.horarioFuncionamento = pontoColetaRegisterDTO.horarioFuncionamento();
        this.capacidadeMaxima = pontoColetaRegisterDTO.capacidadeMaxima();
        this.createdAt = LocalDateTime.now();
    }

    public void updateInformation(PontoColetaUpdateDTO pontoColetaUpdateDTO) {
        if (pontoColetaUpdateDTO.nome() != null) {
            this.nome = pontoColetaUpdateDTO.nome();
        }

        if (pontoColetaUpdateDTO.telefone() != null) {
            this.telefone = pontoColetaUpdateDTO.telefone();
        }

        if (pontoColetaUpdateDTO.email() != null) {
            this.email = pontoColetaUpdateDTO.email();
        }

        if (pontoColetaUpdateDTO.horarioFuncionamento() != null) {
            this.horarioFuncionamento = pontoColetaUpdateDTO.horarioFuncionamento();
        }

        if (pontoColetaUpdateDTO.capacidadeMaxima() != 0) {
            this.capacidadeMaxima = pontoColetaUpdateDTO.capacidadeMaxima();
        }


    }

}
