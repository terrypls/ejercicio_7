
import java.net.*;
import java.io.*;
import java.util.*;

public class ClienteChat implements Runnable {

    static Socket s;
    static BufferedReader n;
    static PrintWriter o;
    static boolean vivo = true;


    public void run() {
        try {
            while (vivo) {
                String l = n.readLine();
                String cmd = l.substring(0, 3);
                System.out.println("comado: " + cmd);
                String msg = l.substring(4);
                if (cmd.equals("msg"))
                    System.out.println("Mensaje: " + msg);
                else if (cmd.equals("ref"))
                    System.out.println("Nueva Lista: " + msg);
                else if (cmd.equals("bye")) {
                    System.out.println("Saliendo " + msg);
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Problems " + e);
        }
    }


    static public void main(String args[]) throws Exception {
        s = new Socket(args[0], 4444);
        String myname = args[1];
        o = new PrintWriter(s.getOutputStream(), true);
        n = new BufferedReader(new InputStreamReader(s.getInputStream()));
        o.println("reg-" + myname);
        Thread t = new Thread(new ClienteChat());
        t.start();
        Scanner inkbd = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String l = inkbd.nextLine();
            s = new Socket(args[0], 4444);
            o = new PrintWriter(s.getOutputStream(), true);
            if (!l.equals("fin"))
                o.println("msg " + myname + ": " + l);
            else {
                o.println("dis " + myname);
                vivo = false;
                break;
            }
        }
    }

}