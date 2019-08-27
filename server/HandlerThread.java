import java.net.Socket;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class HandlerThread extends Thread{

    private Socket cliente;

    public HandlerThread(Socket cliente){
        this.cliente = cliente;
    }

    public void run(){
        System.out.println("IP: " + cliente.getInetAddress().getHostAddress() + " Conectado");
        InputStream is;
        try {
            is = this.cliente.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            while(true){
                ArrayList<String> al = (ArrayList)ois.readObject();
                System.out.println(al.get(0));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        
        // try {
        //     is = this.cliente.getInputStream();
        //     try {
        //         ObjectInputStream ois = new ObjectInputStream(is);
        //         while(true){
        //             ArrayList<String> al = (ArrayList)ois.readObject();
        //             System.out.println(al.get(0));
        //         }
        //     } catch (Exception e) {
        //         e.getStackTrace();
        //     }
        // } catch (Exception e) {
        //     e.getStackTrace();
        // }
    }
}