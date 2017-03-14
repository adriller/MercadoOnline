/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadoservidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Adriller
 */
public class MercadoServidor {

    final static int porta = 12345;

    public static void main(String[] args) {
        Mercado mercado = new Mercado();
        mercado.recuperarDados();
        ServerSocket ss;
        try {
            ss = new ServerSocket(porta);
            while (true) {

                try {
                    System.out.println("Esperando por conexoes..");
                    Socket s = ss.accept();
                    System.out.println("Um cliente foi conectado ao servidor!!");
                    Thread t = new Thread(() -> {
                        System.out.println("Criando thread..");
                        try {
                            String opcao;
                            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                            System.out.println("Mandando resposta ao cliente");
                            out.println("Voce está conectado ao servidor! Esperando jobs.."); //1b
                            while (true) {
                                System.out.println("Esperando comando");
                                while ((opcao = in.readLine()) != null) { //2
                                    System.out.println("comando lido: " + opcao);
                                    if ("Login".equals(opcao)) {
                                        System.out.println("Fazendo login no servidor..");
                                        if (mercado.Login(s, out, in)) {
                                            out.println("Credenciais validas!"); //3
                                        } else {
                                            out.println("Credenciais invalidas!");
                                        }
                                    } else if ("CadastrarUsuario".equals(opcao)) {
                                        System.out.println("Cadastrando no servidor..");
                                        if (mercado.CadastrarUsuario(s, out, in)) {
                                            out.println("ok");
                                        } else {
                                            out.println("erro");
                                        }
                                    } else if ("VisualizarProdutos".equals(opcao)) {
                                        System.out.println("Preparando produtos...");
                                        if (mercado.VisualizarProdutos(s, out, in)) {
                                            out.println("ok"); //17
                                        } else {
                                            out.println("erro");
                                        }
                                    } else if ("CadastrarProdutos".equals(opcao)) {
                                        System.out.println("Preparando cadastro...");
                                        mercado.CadastrarProdutos(s, out, in);
                                    } else if ("Deslogar".equals(opcao)) {
                                        System.out.println("Deslogando...");
                                        mercado.Deslogar(s, out, in);
                                    }
                                    break;
                                }
                                mercado.salvarDados();
                            }
                        } catch (Exception e) {
                            System.out.println("Ops! Parece que o programa foi finalizado");
                        }
                    });
                    t.start();
                } catch (Exception e) {
                    System.out.println("Erro ao conectar cliente: " + e);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro no servidor: " + e);
        }

    }

}
