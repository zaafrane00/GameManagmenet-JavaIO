/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamesmanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author zaafr
 */
public class GamesManagement {

     static ArrayList<Game> gameStore=new ArrayList<Game>();
        
         public static ArrayList getElementsByTagName(String chaine,String tagname){
          ArrayList<String> elements = new ArrayList<>();
             String vol=null;
           Pattern p=Pattern.compile("<("+tagname+"+?)>(.+?)</("+tagname+"+?>)", Pattern.DOTALL);
           Matcher m=p.matcher(chaine);
           while(m.find()){
               vol=m.group(2);
               elements.add(vol);
               //System.out.println(m.group(1)+" : \n"+vol);
           }
           if(elements.isEmpty()){
               String err="not found";
               elements.add(err);
           }
               
           return elements;
      }
    
        public static void Read(){
            String line="";
            String xml="";
            ArrayList<String> games;
           
            BufferedReader reader = null;
            try{
                reader=new BufferedReader(new FileReader("C:/Users/zaafr/Documents/EPI 1er/JavaApplication1/file3.txt"));
                while((line=reader.readLine())!=null){
                    xml+=line;
                }
               // System.out.println(xml);
                 games=getElementsByTagName(xml,"game");
              //  System.out.println(  Arrays.deepToString(games.toArray()));
                 for (int i = 0; i < games.size(); i++) {
                    String game=games.get(i);
                    Game newGame=new Game();
                    newGame.setId(getElementsByTagName(game, "id").get(0).toString());
                    newGame.setTitle(getElementsByTagName(game, "title").get(0).toString());
                    newGame.setGenre(getElementsByTagName(game, "genre").get(0).toString());
                    gameStore.add(newGame);
                    }
               //  System.out.println(Arrays.deepToString(gameStore.toArray()));
                 }catch(FileNotFoundException e){
                e.getMessage();
                  }catch(Exception e){
                e.getMessage();
                 }finally{
                     try{
                         reader.close();
                     }catch(Exception e){
                          e.getMessage();
                      }
                 }
    }
        
        static void insert(Object ob)throws IOException{
             try{
            BufferedWriter out = new BufferedWriter(new FileWriter("C:/Users/zaafr/Documents/EPI 1er/JavaApplication1/file3.txt"));
            String chaine="";
            if(ob instanceof Game){
             gameStore.add((Game)ob);
            }
            write();
        }catch(Exception e){
           e.getMessage();
            }
            
        }
        
        static void write()throws IOException{
            BufferedWriter out = new BufferedWriter(new FileWriter("C:/Users/zaafr/Documents/EPI 1er/JavaApplication1/file3.txt"));
            String chaine="";
            for(int i=0; i<gameStore.size(); i++){
             Game std=gameStore.get(i);
             chaine+="<game>\n"
                        + "\t <id>"+std.getId()+"</id>\n"
                        + "\t <title>"+std.getTitle()+"</title>\n"
                        + "\t <genre>"+std.getGenre()+"</genre>\n"
                        +"</game>\n";
             }
              out.newLine();
              out.write(chaine);
              out.newLine();
              out.close();  
        }
        
        static void showStore(){
            System.out.println(Arrays.deepToString(gameStore.toArray()));
        }
        
        
        public static boolean isNumeric(String str) { 
            try {  
              Double.parseDouble(str);  
              return true;
            } catch(NumberFormatException e){  
              return false;  
            }  
          }
        
        public static Game exist(String id){
            if(isNumeric(id) ){
                for(int i=0; i<gameStore.size(); i++){
                    if(gameStore.get(i).getId().equals(id) ){
                        return gameStore.get(i);
                    }
                }
            }
            return null;
        }
        
         public static int gameIndex(String id){
            if(isNumeric(id) ){
                for(int i=0; i<gameStore.size(); i++){
                    if(gameStore.get(i).getId().equals(id) ){
                        return i;
                    }
                }
            }
            return 0;
        }
        
        static void edit(String id,String ...args)throws IOException{
            if(id.isEmpty()){
                System.out.println("not found 1");
                return;
            }
            else if(isNumeric(id)&& args.length>0 ){
                 if(exist(id)!=null){
                      exist(id).setTitle(args[0]);
                         exist(id).setGenre(args[1]);
                         System.out.println("Edit done successfully");
                         write();
                }
                 else{
                        System.out.println("not found");
                    }
            }
            else{
                System.out.println("id must be numerical");
                return;
            }
        }
        
        static void delete(String id)throws IOException{
               if(id.isEmpty()){
                System.out.println("not found 1");
                return;
            }
            else if(isNumeric(id)){
                 if(exist(id)!=null){
                    gameStore.remove(gameIndex(id));
                    System.out.println("delete done successfully");
                    write();
                }
                 else{
                        System.out.println("not found");
                    }
            }
            else{
                System.out.println("id must be numerical");
                return;
            }
        }
            
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Read();
      //  Game x=new Game("7","spiderssman","action"); 
      //  insert(x);
       // edit("7","marsssssssioqa","hello");
        delete("7");
    }
    
}
