/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author carlos.fernandes
 */

@Entity
public class Solicitacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    
    @NotNull
    @Column
    private Long numero;
    
    @NotNull
    @Column
    private Long valor;
    
    @NotNull
    @Column
    private LocalDate dataEmissao;
    
    @NotNull
    @Column
    private LocalDate dataVencimento;
    
    @NotNull
    @Column
    private LocalDate dataEntrega;
    
    @ManyToOne
    @JoinColumn(name = "idEmpresa")
    private Empresa empresa;
    
    @ManyToOne
    @JoinColumn(name = "idTipoSolicitacao")
    private TipoSolicitacao tipoSolicitacao;
    
    @ManyToOne
    @JoinColumn(name = "idFornecedor")
    private Fornecedor fornecedor;
    
    @Column
    private LocalDateTime dataCriacao;
    
    @Column
    private LocalDateTime ultimaAtualizacao;

    public Solicitacao() {
    }

    public Solicitacao(Long id) {
        this.id = id;
    }

    public Solicitacao(Long id, Long numero, Long valor, LocalDate dataEmissao, LocalDate dataVencimento, LocalDate dataEntrega, Empresa empresa, TipoSolicitacao tipoSolicitacao, Fornecedor fornecedor) {
        this.id = id;
        this.numero = numero;
        this.valor = valor;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.dataEntrega = dataEntrega;
        this.empresa = empresa;
        this.tipoSolicitacao = tipoSolicitacao;
        this.fornecedor = fornecedor;
        this.dataCriacao = LocalDateTime.now();
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public TipoSolicitacao getTipoSolicitacao() {
        return tipoSolicitacao;
    }

    public void setTipoSolicitacao(TipoSolicitacao tipoSolicitacao) {
        this.tipoSolicitacao = tipoSolicitacao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Solicitacao other = (Solicitacao) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
    
}
