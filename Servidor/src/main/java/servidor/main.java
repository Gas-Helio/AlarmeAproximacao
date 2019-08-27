/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author junior
 */
public class main {
     public static void main(String[] args) throws IOException {
            Inicio in = new Inicio();
            in.setVisible(true);
            in.setSize(675,550);
            
            /*
                TratamentoClass novo = new TratamentoClass(cliente,in,i+1);
                Thread c1 = new Thread(novo);
                c1.start();
            */
            try {
		  ServerSocket servidor = new ServerSocket(8000);
		  System.out.println("Servidor ouvindo a porta 8000");
                  ArrayList<Thread> slotes = new ArrayList<Thread>();
                  while(true) {
                        Socket cliente = servidor.accept();
                        if(slotes.isEmpty()){
                            TratamentoClass novo = new TratamentoClass(cliente,in,1);
                            Thread c1 = new Thread(novo);
                            c1.start();
                            slotes.add(c1);
                        }
                        else{
                            if(slotes.size()<4){
                                int adicionado = 0;
                                for(int i =0; i<slotes.size(); i++){
                                    if(!slotes.get(i).isAlive() && adicionado == 0){
                                        TratamentoClass novo = new TratamentoClass(cliente,in,i+1);
                                        Thread c1 = new Thread(novo);
                                        c1.start();
                                        slotes.set(i, c1);
                                        adicionado = 1;
                                    }
                                }
                                if(adicionado == 0){
                                    TratamentoClass novo = new TratamentoClass(cliente,in,slotes.size()+1);
                                    Thread c1 = new Thread(novo);
                                    c1.start();
                                    slotes.add(c1);
                                }

                            }
                        }
                    }
	    	}   
	    catch(Exception e) {
	       System.out.println("Erro: " + e.getMessage());
	    }
    }
}
