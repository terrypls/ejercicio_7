import java.net.*; 
import java.io.*; 
import java.util.*;  
public class CiCServer {
  static Vector names = new Vector();
  static Vector v = new Vector();

  public static void main(String[] arg){
    try {
         ServerSocket ss =new ServerSocket(4444);
         System.out.println("waiting for messages started...");
         while(true) {
			Socket s = ss.accept();
			BufferedReader in = new BufferedReader(
			new InputStreamReader(s.getInputStream()));
			String msg = in.readLine();
			System.out.println(msg.substring(0,3));
			if (msg.substring(0,3).equals("msg")) {
				System.out.println("got message :"+msg);
				for(int i=0; i<v.size(); i++){
					PrintWriter o=(PrintWriter)v.elementAt(i);
					o.println(msg);
				}
			} else if (msg.substring(0,3).equals("reg")) {
				names.addElement(msg.substring(4));
				PrintWriter out=new PrintWriter(s.getOutputStream(),true);
				v.addElement(out);
				System.out.println(msg.substring(4)+" registerado");
				refreshList();
			} else if (msg.substring(0,3).equals("dis")) {
				for (int i=0; i < names.size(); i++) {
					String n = (String)names.elementAt(i);
					if (n.equals(msg.substring(4))) {
						System.out.println("Sacando a "+names.elementAt(i));
                                                PrintWriter p = (PrintWriter)v.elementAt(i);
                                                p.println("bye "+n);
						names.removeElementAt(i);
						v.removeElementAt(i);
						refreshList();
						break;
					}
				}
			}
		}
    }  catch (Exception e) { System.out.println("Problems"); }        } 

  public static void refreshList() throws Exception {
     String n = "";
     for (int i = 0; i < names.size(); i++) 
		n = n+(String)names.elementAt(i)+" ";
     for(int i = 0; i < v.size(); i++) {
        PrintWriter o=(PrintWriter)v.elementAt(i);
        o.println("ref "+n);
      }
   }
	 
} 