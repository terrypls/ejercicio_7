import java.util.ArrayList;

public class InterfazModelo {

    ArrayList<String> usuarios;
    ArrayList<String> chat;

    InterfazModelo(){
        this.usuarios = new ArrayList<String>();
        this.chat = new ArrayList<String>();
    }

    public void nuevoMensaje(String mensaje){
        this.chat.add(mensaje);
    }

    public void nuevoUsuario(String usuario){
        this.usuarios.add(usuario);
    }

    public void borrarUsuario(String usuario){
        this.usuarios.removeIf(n -> (n.equals(usuario)));
    }
}
