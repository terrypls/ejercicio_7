import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;


public class InterfazVista extends Observable implements ActionListener {

    JTextArea input = new JTextArea(2, 10);
    JTextArea mensaje = new JTextArea(40, 20);
    JScrollPane scrollPane = new JScrollPane(mensaje);
    JTextArea lista = new JTextArea(40, 20);
    JButton logout = new JButton("Log out");
    JButton enviar = new JButton("Enviar");


    InterfazVista() {

        JFrame frame = new JFrame("Chat");
        frame.setSize(500, 560);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(null);
        JLabel participantes = new JLabel("Conectados");
        participantes.setBounds(315, 0, 80, 50);
        panel.add(participantes);

        JLabel conversacion = new JLabel("Conversacion");
        conversacion.setBounds(10, 0, 80, 50);
        panel.add(conversacion);

        //Logout
        this.logout.setBounds(400, 10, 80, 25);
        panel.add(this.logout);


        this.mensaje.setBounds(10, 50, 300, 400);
        this.mensaje.setBackground(new Color(200, 200, 200));
        this.mensaje.setLineWrap(true);
        this.mensaje.setEditable(false);

        this.lista.setBounds(320, 50, 150, 400);
        this.lista.setLineWrap(true);
        this.lista.setEditable(false);
        this.lista.setBackground(new Color(200, 200, 200));
        panel.add(this.lista);

        this.scrollPane.setBounds(10, 50, 300, 400);
        this.scrollPane.setBackground(new Color(200, 200, 200));


        this.input.setBounds(10, 460, 380, 50);
        this.input.setBorder(BorderFactory.createLineBorder(Color.black));
        this.input.setLineWrap(true);
        panel.add(this.input);

        this.enviar.setBounds(400, 460, 80, 50);
        panel.add(this.enviar);
        panel.add(this.scrollPane);
        frame.setVisible(true);
        this.logout.addActionListener(this);
        this.enviar.addActionListener(this);

    }

    public void usuariosConectados(String[] lista) {
        StringBuilder texto = new StringBuilder();
        for (String nombre : lista
        ) {

            texto.append(nombre).append("\n");
        }
        this.lista.setText(String.valueOf(texto));
    }

    public void mensajesNuevo(String mensaje) {
        this.mensaje.append(mensaje + "\n");
    }

    public static void close() {
        System.out.println("Cerrando Programa");
        System.exit(0);
    }

    public String inputText() {
        return input.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String observer = "";
        if (e.getSource() == this.logout) {
            observer = "logout";

        }
        if (e.getSource() == this.enviar) {
            observer = this.inputText();
            this.input.setText("");
        }
        setChanged();
        notifyObservers(observer);
    }
}
