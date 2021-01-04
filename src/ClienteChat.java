
import java.lang.ref.Cleaner;
import java.net.*;
import java.io.*;
import java.util.*;

public class ClienteChat implements Runnable, Observer {

    static Socket socket;
    static BufferedReader in;
    static PrintWriter out;
    static boolean vivo = true;
    boolean updated;
    String mensaje;
    InterfazVista vista;
    String name;


    ClienteChat(String nombre) {
        this.vista = new InterfazVista();
        this.vista.addObserver(this);
        this.updated = false;
        this.mensaje = "";
        this.name = nombre;

    }

    public void run() {
        try {

            while (vivo) {

                String l = in.readLine();
                String cmd = l.substring(0, 3);
                System.out.println("comado: " + cmd);
                String msg = l.substring(4);
                String[] lista;

                if (cmd.equals("msg")) {
                    System.out.println("Mensaje: " + msg);
                    this.vista.mensajesNuevo(msg);
                } else if (cmd.equals("ref")) {
                    System.out.println("Nueva Lista: " + msg);
                    lista = msg.split(" ");
                    this.vista.usuariosConectados(lista);
                } else if (cmd.equals("bye")) {
                    System.out.println("Saliendo " + msg);
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Problems " + e);
        }
    }

    static public void main(String[] args) throws Exception {
        socket = new Socket(args[0], 4444);
        String myname = args[1];
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println("reg-" + myname);
        ClienteChat cliente = new ClienteChat(myname);
        System.out.println(cliente.updated);
        Thread t = new Thread(cliente);
        t.start();
    }


    @Override
    public void update(Observable o, Object arg) {
        try {
            socket = new Socket("localhost", 4444);
            out = new PrintWriter(socket.getOutputStream(), true);
            if (arg.equals("logout")) {
                out.println("dis " + this.name);
                vivo = false;
                InterfazVista.close();


            } else {
                this.mensaje = (String) arg;
                out.println("msg " + this.name + ": " + this.mensaje);
            }


        } catch (Exception e) {
            System.out.println("Problems " + e);
        }

        System.out.println(this.updated);
        this.updated = true;

        System.out.println(this.mensaje);
        System.out.println(this.updated);

    }
}