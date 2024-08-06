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
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import org.hibernate.validator.constraints.br.CNPJ;

/**
 *
 * @author carlos.fernandes
 */

@Entity
public class Fornecedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    
    @NotNull
    @NotBlank
    @Column
    private String nome;
    
    @NotNull
    @NotBlank
    @Column
    private String fantasia;
    
    @NotNull
    @NotBlank
    @Column(unique = true)
    @CNPJ
    private String CNPJ;
    
    @NotNull
    @Column
    private Long codDatavale;
    
    @Column
    private boolean status;
    
    @Column
    private LocalDateTime dataCriacao;
    
    @Column
    private LocalDateTime ultimaAtualizacao;

    public Fornecedor() {
    }

    public Fornecedor(Long id) {
        this.id = id;
    }

    public Fornecedor(Long id, String nome, String fantasia, String CNPJ, Long codDatavale, boolean status) {
        this.id = id;
        this.nome = nome;
        this.fantasia = fantasia;
        this.CNPJ = CNPJ;
        this.codDatavale = codDatavale;
        this.status = status;
        this.dataCriacao = LocalDateTime.now();
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public Long getCodDatavale() {
        return codDatavale;
    }

    public void setCodDatavale(Long codDatavale) {
        this.codDatavale = codDatavale;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
        int hash = 7;
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
        final Fornecedor other = (Fornecedor) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
