package br.com.henriqueandrew.Atividade4;

import java.awt.FlowLayout;
import java.awt.TextArea;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JButton;

// @author Henrique Andrew da Silva
public class JanelaCliente {

    private JFrame jan;
    int larg = 500, alt = 500;

    private JLabel rot1;
    private JLabel rot2;
    private JLabel rot3;
    private JTextField ent1;
    private JTextField ent2;
    private TextArea retorno;
    private JButton bt1;

    private static Socket conexao;
    private static ObjectOutputStream saida;
    private static ObjectInputStream entrada;

    public JanelaCliente(){

        iniComponentes();
        addListeners();    
        
    }

    public void iniComponentes() {

        jan = new JFrame();
        jan.setSize(alt, larg);
        jan.setTitle("Cadastro de Pessoas");
        jan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rot1 = new JLabel();
        rot1.setText("Nome: ");
        jan.add(rot1);
        ent1 = new JTextField(30);
        jan.add(ent1);

        rot2 = new JLabel();
        rot2.setText("Idade: ");
        jan.add(rot2);
        ent2 = new JTextField(3);
        jan.add(ent2);

        rot3 = new JLabel();
        rot3.setText("Retorno do Servidor");
        jan.add(rot3);
        retorno = new TextArea();
        jan.add(retorno);
        retorno.setEditable(false);

        bt1 = new JButton("Enviar");
        jan.add(bt1);

        jan.setLayout(new FlowLayout());
        jan.setVisible(true);
    }

    public void addListeners() {

        bt1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Enviar");
                bt1EnviarActionPerformed(e);
            }
        });
    }

    public void bt1EnviarActionPerformed(ActionEvent evt) {
        enviarCadastro();
    }

    public void enviarCadastro() {
        try {
            //envio
            conexao = new Socket("127.0.0.1", 20000);
            saida = new ObjectOutputStream(conexao.getOutputStream());

            Pessoa p = new Pessoa(ent1.getText(), Integer.valueOf(ent2.getText()));
            saida.writeObject(p);

            //retorno
            retorno.append("\n\nReposta do servidor...\n\n");
            entrada = new ObjectInputStream(conexao.getInputStream());

            Pessoa p2 = (Pessoa) entrada.readObject();
            retorno.append("Nome: " + p2.getNome() + "\n" + "Idade: " + p2.getIdade());
            
            ent1.setText("");
            ent2.setText("");
            ent1.requestFocus();

            conexao.close();
        } catch (Exception e) {
            
        }
    }  
    

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaCliente();
            }
        });
    }
}
