/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.model;

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
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author carlos.fernandes
 */

@Entity
public class Conta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    
    @NotNull
    @Column
    private Long numero;
    
    @NotBlank
    @Column
    private String nome;
    
    @NotNull
    @Column
    private Long valor;
    
    @Column
    private Long valorImposto;
    
    @Column
    private boolean status;
    

    @ManyToOne
    @JoinColumn(name = "idEmpresa")
    private Empresa empresa;
    
    @ManyToOne
    @JoinColumn(name = "idTipoConta")
    private TipoConta tipoConta;
    
    @ManyToOne
    @JoinColumn(name = "idFrequenciaConta")
    private FrequenciaConta frequenciaConta;
    
    @ManyToOne
    @JoinColumn(name = "idFornecedor")
    private Fornecedor fornecedor;
    
    @ManyToOne
    @JoinColumn(name = "idGrupo")
    private Grupo grupo;
    
    @Column
    private LocalDateTime dataCriacao;
    
    @Column
    private LocalDateTime ultimaAtualizacao;

    public Conta() {
    }
    
    public Conta(Long id) {
        this.id = id;
    }

    public Conta(Long id, Long numero, String nome, Long valor, Long valorImposto, boolean status, Empresa empresa, TipoConta tipoConta, FrequenciaConta frequenciaConta, Fornecedor fornecedor, Grupo grupo) {
        this.id = id;
        this.numero = numero;
        this.nome = nome;
        this.valor = valor;
        this.valorImposto = valorImposto;
        this.status = status;
        this.empresa = empresa;
        this.tipoConta = tipoConta;
        this.frequenciaConta = frequenciaConta;
        this.fornecedor = fornecedor;
        this.grupo = grupo;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Long getValorImposto() {
        return valorImposto;
    }

    public void setValorImposto(Long valorImposto) {
        this.valorImposto = valorImposto;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public FrequenciaConta getFrequenciaConta() {
        return frequenciaConta;
    }

    public void setFrequenciaConta(FrequenciaConta frequenciaConta) {
        this.frequenciaConta = frequenciaConta;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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
    
    @PrePersist
    public void prePersist(){
        dataCriacao = LocalDateTime.now();
        status = true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Conta other = (Conta) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
