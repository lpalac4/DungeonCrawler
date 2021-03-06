import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 * Parser class is responsible for scanning and disecting gdf files according to the format given by Professor Bell.
 * @author Luis Palacios
 * @author Tom Phillips
 * @author Aman Mahajan
 * @author Sunil Tilokani
 *
 */

public class Parser {
        
        private Map map;
        private float version;
        private String env;
        private String magicword;
        
        Pattern p = Pattern.compile("//.*");
        Pattern q = Pattern.compile("\\*.*");
        
        public Parser(File file, Map mapCreator){
                
                map = mapCreator;
                
                try{
                        
        Scanner s = new Scanner(file);
        //s.useDelimiter("\\s");
                        
                while(s.hasNextLine()){
                        
                        magicword = null;
                        if(s.hasNext())
                                magicword = s.next();
                
                        
                        if(magicword != null && magicword.equals("GDF")){

                                version = s.nextFloat();
                                env  = new String();
                                boolean finished = false;
                                
                                while(s.hasNext()){
                                        if(s.hasNext(p)){ //Comments reached skip to next line.
                                                s.nextLine();
                                                finished = true;
                                        }
                                        if(finished)
                                                break;
                                        env = env + " " + s.next();
                                }
                                
                                System.out.println("Running on " + magicword+" "+version + " " +env);
                        }        
                        else if(magicword != null && magicword.equals("PLACES")){               
                                
                                int n = s.nextInt();
                                int count = 0;
                                int id;
                                String roomName = "";
                                String roomDesc = "";
                                
                                while(count < n){
                                        while(s.hasNext(p)){ //handle any comments before the room layouts 
                                                s.nextLine();
                                        }
                                        
                                        //Handle an addition of a room
                                        if(s.hasNextInt()){             //start with ID
                                                id = s.nextInt();
                                                if(s.hasNext()){        //now add the room name
                                                        roomName = s.next();
                                                        roomDesc = "";
                                        
                                                        while(!s.hasNext(p)){                //handle names of more than 1 word
                                                                roomName = roomName + " " + s.next();
                                                                
                                                        }
                                                        s.nextLine(); //at this point a // has been found and scanner skips to next line
                                                        while(s.hasNextLine() && !s.hasNextInt() && !s.hasNext(p)){ //now handle the description will keep handling till no more comment asterisks appear
                                                                if(s.hasNext("PATHS"))
                                                                        break;
                                                                   roomDesc = roomDesc + "\n" + s.nextLine();  //add this line of the description and advance scanner.
                                                        }
                                                        //System.out.println(roomName);
                                                        //System.out.println(roomDesc);
                                                        count++;
                                                        Places room = new Places(roomName,id,roomDesc,50);
                                                        map.addRoom(room);
                                                }
                                        }
                                }
                        }
                        else if(magicword != null && magicword.equals("PATHS")){
                                int index = 0;
                                int source = 0;
                                int dest = 0;
                                boolean locked = false;
                                String direction = "";
                                int lockNum = 0;
                                
                                int n = s.nextInt();
                                int count = 0;
                                
                                while(count < n){
                                        direction = "";
                                        locked = false;
                                        lockNum = 0;
                                        
                                        while(s.hasNext(p))
                                                s.nextLine();

                                        if(s.hasNextInt()){     //Prepare for a new path
                                                index = s.nextInt();    //add the index number

                                                if(s.hasNextInt())      //now add the source id
                                                        source = s.nextInt();

                                                if(s.hasNext()) //now add the direction
                                                        direction = direction + s.next();

                                                if(s.hasNextInt()){     //now the destination id
                                                        dest = s.nextInt();
                                                        if(dest < 0 || dest == 0) {
                                                                locked = true;
                                                                dest *= -1;
                                                        }
                                                }
                                                
                                                if(s.hasNextInt())
                                                {
                                                    lockNum = s.nextInt(); // stores the lock value
                                                }
                                                
                                                //now we expect a comment // so skip to next line
                                                while(s.hasNext(p))
                                                        s.nextLine();

                                                //now we can say that this path is complete add it to map and assign it to rooms, increment count
                                                count++;
                                                Paths hallway = new Paths(map.findRoomById(source), map.findRoomById(dest), direction, locked, lockNum);
                                                map.findRoomById(source).addPath(hallway);

                                        }

                                }               
                        }
                        else if(magicword != null && magicword.equals("LIGHTING")){
                            int index = 0;
                            int source = 0;
                            int lightVal = 0;

                            int n = s.nextInt();
                            int count = 0;
                            
                            while (count < n)
                            {
                                while(s.hasNext(p)) { 
                                    s.nextLine();
                                }
                                
                                if(s.hasNextInt()){
                                    index = s.nextInt();
                                    
                                    if (s.hasNextInt()) {
                                        source = s.nextInt();
                                    }
                                    
                                    if (s.hasNextInt()) {
                                        lightVal = s.nextInt();
                                    }
                                    
                                    //now we expect a comment // so skip to next line
                                    while(s.hasNext(p))
                                        s.nextLine();

                                    count++;
                                    map.findRoomById(source).setLight(lightVal);
                                }
                            }
                        }
                        else if(magicword != null && magicword.equals("ARTIFACTS")){
                            int index = 0;
                            int source = 0;
                            int moneyVal = 0;
                            int moves = 0;
                            String item = "";
                            String itemDesc = "";

                            int n = s.nextInt();
                            int count = 0;
                            
                            while (count < n)
                            {
                                while(s.hasNext(p)) { 
                                    s.nextLine();
                                }
                                
                                if(s.hasNextInt()){
                                    index = s.nextInt(); // item ID
                                    //System.out.println(index);
                                    if (s.hasNextInt()) { // source of item
                                        source = s.nextInt();
                                        //System.out.println(source);
                                    }
                                    
                                    if (s.hasNextInt()) { // values of item
                                        moneyVal = s.nextInt();
                                        //System.out.println(moneyVal);
                                    }
                                    
                                    if (s.hasNextInt()) { // is the item movable?
                                        moves = s.nextInt();
                                        //System.out.println(moves);
                                    }
                                    
                                    if(s.hasNext()) { //now add the item name
                                        item = s.next();
                                        while(!s.hasNext(p) && !s.hasNext(q)){ //handle names of more than 1 word
                                                item = item + " " + s.next();
                                        }
                                        s.nextLine(); //at this point a // has been found and scanner skips to next line
                                        itemDesc = "";
                                        while(s.hasNextLine() && !s.hasNextInt() && !s.hasNext(p)){ //now handle the description will keep handling till no more comment asterisks appear
                                                if(s.hasNext("KEYS"))
                                                        break;
                                                itemDesc = itemDesc + "\n" + s.nextLine();  //add this line of the description and advance scanner.
                                        }
                                        
                                        //now we expect a comment // so skip to next line
                                        while(s.hasNext(p))
                                            s.nextLine();

                                        count++;
                                        map.findRoomById(source).addArt(index,source,moneyVal,moves,item,itemDesc);
                                    }
                                }
                            }
                        }
                        else if(magicword != null && magicword.equals("KEYS")){
                            int index = 0;
                            int source = 0;
                            int keyVal = 0;
                            int masterVal = 0;
                            int moneyVal = 0;
                            int moves = 0;
                            String item = "";
                            String itemDesc = "";

                            int n = s.nextInt();
                            int count = 0;
                            
                            while (count < n)
                            {
                                while(s.hasNext(p)) { 
                                    s.nextLine();
                                }
                                
                                if(s.hasNextInt()){
                                    index = s.nextInt(); // item ID
                                    //System.out.println(index);
                                    if (s.hasNextInt()) { // source of item
                                        source = s.nextInt();
                                        //System.out.println(source);
                                    }
                                    
                                    if (s.hasNextInt()) { // value of key
                                        keyVal = s.nextInt();
                                        //System.out.println(keyVal);
                                    }
                                    
                                    if (s.hasNextInt()) { // master value of key
                                        masterVal = s.nextInt();
                                        //System.out.println(masterVal);
                                    }
                                    if (s.hasNextInt()) { // values of item
                                        moneyVal = s.nextInt();
                                        //System.out.println(moneyVal);
                                    }
                                    
                                    if (s.hasNextInt()) { // is the item movable?
                                        moves = s.nextInt();
                                        //System.out.println(moves);
                                    }
                                    
                                    if(s.hasNext()) { //now add the item name
                                        item = s.next();
                                        while(!s.hasNext(p) && !s.hasNext(q)){ //handle names of more than 1 word
                                                item = item + " " + s.next();
                                                //System.out.println(item);
                                        }
                                        s.nextLine(); //at this point a // has been found and scanner skips to next line
                                        itemDesc = "";
                                        while(s.hasNextLine() && !s.hasNextInt() && !s.hasNext(p)){ //now handle the description will keep handling till no more comment asterisks appear
                                                if(s.hasNext("LIGHTS"))
                                                        break;
                                                itemDesc = itemDesc + "\n" + s.nextLine();  //add this line of the description and advance scanner.
                                        }
                                        //System.out.println(itemDesc);
                                        //now we expect a comment // so skip to next line
                                        while(s.hasNext(p))
                                            s.nextLine();

                                        count++;
                                        map.findRoomById(source).addKey(index,source,keyVal,masterVal,moneyVal,moves,item,itemDesc);
                                    }
                                }
                            }
                        }
                        else if(magicword != null && magicword.equals("LIGHTS")){
                            //lightID placeID lightLevel value movability name
                            int index = 0;
                            int source = 0;
                            int lightVal = 0;
                            int moneyVal = 0;
                            int moves = 0;
                            String item = "";
                            String itemDesc = "";

                            int n = s.nextInt();
                            int count = 0;
                            
                            while (count < n)
                            {
                                while(s.hasNext(p)) { 
                                    s.nextLine();
                                }
                                
                                if(s.hasNextInt()){
                                    index = s.nextInt(); // item ID
                                    //System.out.println(index);
                                    if (s.hasNextInt()) { // source of item
                                        source = s.nextInt();
                                        //System.out.println(source);
                                    }
                                    
                                    if (s.hasNextInt()) { // value of light
                                        lightVal = s.nextInt();
                                        //System.out.println(lightVal);
                                    }
                                    
                                    if (s.hasNextInt()) { // values of item
                                        moneyVal = s.nextInt();
                                        //System.out.println(moneyVal);
                                    }
                                    
                                    if (s.hasNextInt()) { // is the item movable?
                                        moves = s.nextInt();
                                        //System.out.println(moves);
                                    }
                                    
                                    if(s.hasNext()) { //now add the item name
                                        item = s.next();
                                        while(!s.hasNext(p) && !s.hasNext(q)){ //handle names of more than 1 word
                                                item = item + " " + s.next();
                                                //System.out.println(item);
                                        }
                                        s.nextLine(); //at this point a // has been found and scanner skips to next line
                                        itemDesc = "";
                                        while(s.hasNextLine() && !s.hasNextInt() && !s.hasNext(p)){ //now handle the description will keep handling till no more comment asterisks appear
                                                if(s.hasNext("LIGHTS"))
                                                        break;
                                                itemDesc = itemDesc + "\n" + s.nextLine();  //add this line of the description and advance scanner.
                                        }
                                        //System.out.println(itemDesc);
                                        //now we expect a comment // so skip to next line
                                        while(s.hasNext(p))
                                            s.nextLine();

                                        count++;
                                        map.findRoomById(source).addLight(index,source,lightVal,moneyVal,moves,item,itemDesc);
                                    }
                                }
                            }
                        }
                        else{
                                System.out.println("The file does not conform with the given format specifications.  Shutting down application.");
                                System.exit(1);
                        }
                        
                        if(!s.hasNext())
                                break;
                
                }
                
                }

                catch(Exception e){
                        e.printStackTrace();
                
                } 

        }
        }
