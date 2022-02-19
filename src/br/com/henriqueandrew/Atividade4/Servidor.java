package br.com.henriqueandrew.Atividade4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// @author Henrique Andrew da Silva
public class Servidor extends Thread {

    private static Socket conexao;
    private static ServerSocket server;
    private static ObjectInputStream entrada;
    private static ObjectOutputStream saida;

    public Servidor(Socket conexao) {
        this.conexao = conexao;
    }

    public void run() {
        try {
            entrada = new ObjectInputStream(conexao.getInputStream());
            while (true) {
                Pessoa p = (Pessoa) entrada.readObject();
                if (p != null) {
                    saida = new ObjectOutputStream(conexao.getOutputStream());
                    saida.writeObject(p);
                    System.out.println("Servidor retornou cadastro!");
                    conexao.close();
                }
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws IOException {

        try {
            server = new ServerSocket(20000);
            while (true) {
                conexao = server.accept();
                Servidor sThread = new Servidor(conexao);
                sThread.start();
                }
            } catch (Exception e) {

        }
    }

}
