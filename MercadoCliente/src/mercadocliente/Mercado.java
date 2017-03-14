/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadocliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Adriller
 */
public class Mercado {

    Scanner sc = new Scanner(System.in);
    String outInfo;

    public boolean CadastrarUsuario(Socket s, PrintWriter out, BufferedReader in) throws UnknownHostException, IOException {
        String nome, end, tel, email, senha, ID;
        out.println("CadastrarUsuario"); //2
        System.out.println("Digite o nome");
        nome = (sc.nextLine().toUpperCase());
        System.out.println("Digite o endereço");
        end = (sc.nextLine().toUpperCase());
        System.out.println("Digite o telfone");
        tel = (sc.nextLine().toUpperCase());
        System.out.println("Digite o email");
        email = (sc.nextLine().toUpperCase());
        System.out.println("Digite o ID ");
        ID = (sc.nextLine());
        System.out.println("Digite a senha (sensitive case)");
        senha = (sc.nextLine());
        //3-8
        out.println(nome);
        out.println(end);
        out.println(tel);
        out.println(email);
        out.println(ID);
        out.println(senha);

        System.out.println("Aguardando resposta");
        while ((outInfo = in.readLine()) != null) {
            break;
        }
        return outInfo.equals("ok");
    }

    public boolean Login(Socket s, PrintWriter out, BufferedReader in) throws UnknownHostException, IOException {
        String senha, ID;
        out.println("Login"); //2
        System.out.println("Digite o ID");
        ID = sc.nextLine();
        System.out.println("Digite a senha (sensitive case)");
        senha = sc.nextLine();
        //3 4
        out.println(ID);
        out.println(senha);
        System.out.println("Aguardando resposta...");
        while ((outInfo = in.readLine()) != null) {
            System.out.println(outInfo);
            break;
        }
        return outInfo.equals("Credenciais validas!");
    }

    public boolean VisualizarProdutos(Socket s, PrintWriter out, BufferedReader in) throws UnknownHostException, IOException {
        String nome, fornecedor, aux, endereco, email, telefone, tamLista, escolha, quantidade, preco;
        out.println("VisualizarProdutos"); //2
        System.out.println("pronto para receber tamanho..");
        while ((tamLista = in.readLine()) != null) { //3
            break;
        }
        for (int i = 0; i < Integer.parseInt(tamLista); i++) { //4-7
            System.out.println("          --          ");
            System.out.println("Produto " + (i + 1) + ": ");
            while ((nome = in.readLine()) != null) {
                System.out.println("Nome: " + nome);
                break;
            }
            while ((fornecedor = in.readLine()) != null) {
                System.out.println("Fornecedor: " + fornecedor);
                break;
            }
            while ((preco = in.readLine()) != null) {
                System.out.println("Preço: R$" + preco);
                break;
            }
            while ((quantidade = in.readLine()) != null) {
                System.out.println("Quantidade: " + quantidade);
                break;
            }
            System.out.println("Digite " + (i + 1) + " para comprar esse produto! \n");
        }
        System.out.println("Digite 0 para voltar ao menu principal");
        escolha = sc.nextLine();
        out.println(escolha);
        if (Integer.parseInt(escolha) != 0) {
            System.out.println("\n\nO seguinte produto:");
            while ((nome = in.readLine()) != null) { // 8-11
                System.out.println("Nome: " + nome);
                break;
            }
            while ((fornecedor = in.readLine()) != null) {
                System.out.println("Fornecedor: " + fornecedor);
                break;
            }
            while ((preco = in.readLine()) != null) {
                System.out.println("Preço: " + preco);
                break;
            }
            while ((quantidade = in.readLine()) != null) {
                System.out.println("Quantidade: " + quantidade);
                break;
            }

            if ((Integer.parseInt(quantidade) > 0)) {
                while ((aux = in.readLine()) != null) { //12
                    break;
                }
                if (aux.equals("oK")) { //13 - 16
                    System.out.println("Foi comprado com sucesso! Será enviado em breve para o comprador:");
                    while ((nome = in.readLine()) != null) {
                        System.out.println("Nome: " + nome);
                        break;
                    }
                    while ((endereco = in.readLine()) != null) {
                        System.out.println("Endereço: " + endereco);
                        break;
                    }
                    while ((email = in.readLine()) != null) {
                        System.out.println("E-mail: " + email);
                        break;
                    }
                    while ((telefone = in.readLine()) != null) {
                        System.out.println("Telefone: " + telefone);
                        break;
                    }
                } else {
                    System.out.println("Voce não pode comprar sem fazer login antes!");
                }

            } else {
                System.out.println("\nNao está disponivel para compra!\n");
            }
        }
        while ((aux = in.readLine()) != null) { //17
            break;
        }
        return aux.equals("ok");
    }

    public boolean CadastrarProdutos(Socket s, PrintWriter out, BufferedReader in) throws UnknownHostException, IOException {
        String nome, quantidade, preco;
        out.println("CadastrarProdutos"); //2
        System.out.println("Digite o nome do produto");
        nome = (sc.nextLine().toUpperCase());
        System.out.println("Digite o preço (utilizando . para separar centavos)");
        preco = (sc.nextLine());
        System.out.println("Digite a quantidade de itens disponiveis");
        quantidade = (sc.nextLine());
        //3-6
        out.println(nome);
        out.println(preco);
        out.println(quantidade);

        return true;
    }

    public void deslogar(Socket s, PrintWriter out, BufferedReader in) throws UnknownHostException, IOException {
        out.println("Deslogar");
    }
}
