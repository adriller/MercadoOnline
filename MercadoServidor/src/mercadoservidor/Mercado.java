/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadoservidor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Adriller
 */
public class Mercado {

    ArrayList<Usuario> users = new ArrayList<>();
    ArrayList<Produto> produtos = new ArrayList<>();
    Usuario onlineUser;

    public boolean Login(Socket s, PrintWriter out, BufferedReader in) throws UnknownHostException, IOException {
        String aux, senha, ID;
        //3
        while ((ID = in.readLine()) != null) {
            break;
        }
        System.out.println("ID recebido " + ID);
        //4
        while ((senha = in.readLine()) != null) {
            break;
        }
        System.out.println("Senha recebida " + senha);
        for (Usuario u : users) {
            if (u.getID() == null ? ID == null : u.getID().equals(ID)) {
                if (u.getSenha().equals(senha)) {
                    this.onlineUser = u;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean CadastrarUsuario(Socket s, PrintWriter out, BufferedReader in) throws UnknownHostException, IOException, ClassNotFoundException {
        String nome, end, tel, email, senha, aux, ID;
        Usuario usuario = new Usuario();
        System.out.println("Recebendo dados");
        //3-8
        while ((nome = in.readLine()) != null) {
            break;
        }
        while ((end = in.readLine()) != null) {
            break;
        }
        while ((tel = in.readLine()) != null) {
            break;
        }
        while ((email = in.readLine()) != null) {
            break;
        }
        while ((ID = in.readLine()) != null) {
            break;
        }
        while ((senha = in.readLine()) != null) {
            break;
        }
        System.out.println("setando usuario");
        usuario.setEmail(email);
        usuario.setEndereco(end);
        usuario.setID(ID);
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setTelefone(tel);
        users.add(usuario);
        System.out.println("Usuario adicionado!");
        return true;
    }

    public boolean VisualizarProdutos(Socket s, PrintWriter out, BufferedReader in) throws UnknownHostException, IOException {
        int auxtam = produtos.size();
        String aux, escolha, tamLista = Integer.toString(auxtam);

        out.println(tamLista); //3
        for (Produto p : produtos) { //4-7
            out.println(p.getNome());
            out.println(p.getFornecedor());
            out.println(p.getPreco());
            out.println(p.getQuantidade());
        }
        System.out.println("Produtos listados");
        while ((escolha = in.readLine()) != null) {
            break;
        }
        if (Integer.parseInt(escolha) != 0) { //8-11
            Produto p2 = produtos.get(Integer.parseInt(escolha) - 1);
            out.println(p2.getNome());
            out.println(p2.getFornecedor());
            out.println(p2.getPreco());
            out.println(p2.getQuantidade());
            if (Integer.parseInt(p2.getQuantidade()) > 0) {
                Usuario u = this.getUsuarioOnline();
                if (u == null) {
                    out.println("UsuarioDeslogado"); //12
                } else {
                    out.println("oK"); //12 - 16
                    out.println(u.getNome());
                    out.println(u.getEndereco());
                    out.println(u.getEmail());
                    out.println(u.getTelefone());
                    p2.Vender();
                }
            }
        }
        return true;
    }

    public boolean CadastrarProdutos(Socket s, PrintWriter out, BufferedReader in) throws UnknownHostException, IOException {
        String nome, aux, quantidade, preco;
        System.out.println("Recebendo dados");
        boolean novo = true;
        while ((nome = in.readLine()) != null) {
            break;
        }
        while ((preco = in.readLine()) != null) {
            break;
        }
        while ((quantidade = in.readLine()) != null) {
            break;
        }
        Usuario u = this.onlineUser;
        for (Produto p2 : produtos) {
            if (p2.getNome().equals(nome) && p2.getPreco().equals(preco) && p2.getFornecedor().equals(u.getNome())) {
                p2.Reabastecer(quantidade);
                novo = false;
            }
        }
        if (novo) {
            Produto p = new Produto();
            p.setFornecedor(u.getNome());
            p.setNome(nome);
            p.setPreco(preco);
            p.setQuantidade(quantidade);
            produtos.add(p);
        }
        System.out.println("Produto adicionado!");
        return true;
    }

    public Usuario getUsuarioOnline() {
        return this.onlineUser;
    }

    public void Deslogar(Socket s, PrintWriter out, BufferedReader in) throws UnknownHostException, IOException {
        this.onlineUser = null;
    }

    public void salvarDados() {
        try {
            File file = new File("data.csv");
            file.delete();
            try (FileWriter writer = new FileWriter("data.csv")) {

                /*Escreve os usuarios*/
                for (Usuario p : users) {
                    writer.append(p.getNome());
                    writer.append(',');
                    writer.append(p.getEndereco());
                    writer.append(',');
                    writer.append(p.getTelefone());
                    writer.append(',');
                    writer.append(p.getEmail());
                    writer.append(',');
                    writer.append(p.getID());
                    writer.append(',');
                    writer.append(p.getSenha());
                    writer.append(',');
                }
                writer.append("fim");
                writer.append('\n');

                /*escreve os livros*/
                for (Produto l : produtos) {
                    writer.append(l.getNome());
                    writer.append(',');
                    writer.append(l.getPreco());
                    writer.append(',');
                    writer.append(l.getQuantidade());
                    writer.append(',');
                    writer.append(l.getFornecedor());
                    writer.append(',');
                }
                writer.append("fim");
                writer.append('\n');
                writer.close();
            }

        } catch (Exception e) {
            System.out.println("Erro ao ler dados: " + e);
        }
    }

    public void recuperarDados() {
        File file = new File("data.csv");
        int i = 0;
        int index;
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String line = inputStream.nextLine();
                System.out.println("Linha: " + line);
                String[] campo = line.split(",");
                while (!campo[i].equals("fim")) {
                    this.CadastrarUsuarioArq(campo[i], campo[i + 1], campo[i + 2], campo[i + 3], campo[i + 4], campo[i + 5]);
                    i += 6;
                }
                i = 0;
                line = inputStream.nextLine();
                System.out.println("Linha: " + line);
                campo = line.split(",");
                while (!campo[i].equals("fim")) {
                    this.CadastrarProdutoArq(campo[i], campo[i + 1], campo[i + 2], campo[i + 3]);
                    i += 4;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void CadastrarUsuarioArq(String nome, String end, String tel, String email, String ID, String senha) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setEndereco(end);
        usuario.setID(ID);
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setTelefone(tel);
        users.add(usuario);
    }

    private void CadastrarProdutoArq(String nome, String preco, String quantidade, String fornecedor) {
        Produto p = new Produto();
        p.setFornecedor(fornecedor);
        p.setNome(nome);
        p.setPreco(preco);
        p.setQuantidade(quantidade);
        produtos.add(p);
    }
}
