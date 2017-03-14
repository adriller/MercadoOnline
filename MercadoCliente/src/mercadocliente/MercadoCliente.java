/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadocliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Adriller
 */
public class MercadoCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mercado mercado = new Mercado();
        String escolha = "0";
        Scanner sc = new Scanner(System.in);
        int porta = 12345;
        String host = "192.168.0.104";
        String inInfo;
        boolean logado = false;
        try {  //tenta se conectar ao servidor
            Socket s = new Socket(host, porta);
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("Esperando resposta do servidor...");
            while ((inInfo = in.readLine()) != null) { //1b
                System.out.println(inInfo);
                break;
            }
            while (Integer.parseInt(escolha) != 5) {
                System.out.println("------------Menu:-----------");
                if (!logado) {
                    System.out.println("Digite 1 para fazer login!");
                    System.out.println("Digite 2 para cadastrar-se!");
                }
                System.out.println("Digite 3 para visualizar produtos");
                System.out.println("Digite 4 para cadastrar produtos");
                System.out.println("Digite 5 para sair");
                if (logado) {
                    System.out.println("Digite 6 para deslogar");
                }
                escolha = sc.nextLine();
                if (Integer.parseInt(escolha) == 2) {
                    if (mercado.CadastrarUsuario(s, out, in)) {
                        System.out.println("Cadastrado no servidor com sucesso!");
                    } else {
                        System.out.println("Falha ao cadastrar no servidor!");
                    }
                } else if (Integer.parseInt(escolha) == 1) {
                    if (mercado.Login(s, out, in)) {
                        System.out.println("Logado no servidor com sucesso!");
                        logado = true;
                    } else {
                        System.out.println("Erro ao logar no servidor");
                    }
                } else if (Integer.parseInt(escolha) == 3) {
                    if (mercado.VisualizarProdutos(s, out, in)) {
                        System.out.println("Produtos listados com sucesso!");
                    } else {
                        System.out.println("Produto nao disponivel");
                    }
                } else if (Integer.parseInt(escolha) == 4) {
                    if (logado) {
                        if (mercado.CadastrarProdutos(s, out, in)) {
                            System.out.println("Produto cadastrado com sucesso!");
                        } else {
                            System.out.println("Problema ao conectar-se ao servidor");
                        }
                    }
                    else{
                        System.out.println("É necessario logar antes!");
                    }
                } else if (Integer.parseInt(escolha) == 6) {
                    logado = false;
                    mercado.deslogar(s, out, in);
                }
                System.out.println("Digite enter para continuar");
                sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Erro, desconectado do servidor");
        }

    }

}
