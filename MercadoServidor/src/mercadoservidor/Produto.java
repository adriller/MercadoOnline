/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadoservidor;

/**
 *
 * @author Adriller
 */
public class Produto {
    private String nome, fornecedor, quantidade, preco;
    void setNome(String nome){
        this.nome = nome;
    }
    String getNome(){
        return this.nome;
    }
    void setPreco(String preco){
        this.preco = preco;
    }
    String getPreco(){
        return this.preco;
    }
    void setFornecedor(String fornecedor){
        this.fornecedor = fornecedor;
    }
    String getFornecedor(){
        return this.fornecedor;
    }
    public void setQuantidade(String q){
        this.quantidade = q;
    }
    public String getQuantidade(){
        return this.quantidade;
    }
    public void Vender(){
        this.quantidade = Integer.toString(Integer.parseInt(this.quantidade) - 1);
    }
    public void Reabastecer(String qtd){
        this.quantidade = Integer.toString(Integer.parseInt(qtd) + Integer.parseInt(this.quantidade));
    }
}
