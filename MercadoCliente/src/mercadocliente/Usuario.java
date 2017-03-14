/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadocliente;

import java.io.Serializable;

/**
 *
 * @author Adriller
 */
public class Usuario implements Serializable {

    private String nome, endereco, telefone, email, senha, ID;

    void setNome(String nome) {
        this.nome = nome;
    }

    String getNome() {
        return this.nome;
    }

    void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    String getEndereco() {
        return this.endereco;
    }

    void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    String getTelefone() {
        return this.telefone;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getEmail() {
        return this.email;
    }

    void setSenha(String senha) {
        this.senha = senha;
    }

    String getSenha() {
        return this.senha;
    }

    void setID(String ID) {
        this.ID = ID;
    }

    String getID() {
        return this.ID;
    }

}
