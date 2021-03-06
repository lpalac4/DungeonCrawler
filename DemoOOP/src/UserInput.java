import java.io.Console;
import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * UserInput class responsible for taking input commands from the user.
 * @author Luis Palacios
 * @author Tom Phillips
 *
 */
public class UserInput {
        
        private Player player;
        private Map map;
        private Parser parser;
        private boolean running;
        private String[] directionsAllowed;
        
        /**
         * Public constructor method
         * @param textFile is the string representing the file location
         */
        public UserInput(File textFile){
                
                map = new Map();
                
                //initialize exit(id = 1) and locked room(id = 0);
                map.addRoom(new Places("Locked Room", 0, "*The door is locked you're doomed!",100));
                map.addRoom(new Places("Exit", 1, "*You're now outside of the dungeon!! Freedom!!!",100));
                
                parser = new Parser(textFile, map);
                
                initializePlayer();
                initGameWorld();
        }
        
        /**
         * Private method that contains the main game loop that accepts input commands.
         */
        private void initGameWorld() {
                running = true;
                System.out.println("The world has been created, and is open for exploration.");
                outputPlayerLocation();
                String[] directionsAllowed1 = {"N", "NORTH", "S", "SOUTH", "E", "EAST", "W", "WEST", "U", "UP", "D", "DOWN", 
                                "NE", "NW", "SE", "SW", "NNE", "NNW", "SSE", "SSW", "ENE", "ESE", "WNW", "WSW"};                
                
                directionsAllowed = directionsAllowed1;
                
                while(running){
                        System.out.println("\n");
                        System.out.println("What would you like to do? \n" +
                                        "For input help enter 'HELP'. ");
                        
                        Scanner sc = new Scanner(System.in);
                        //sc.useDelimiter("\\s");
                        //String input = sc.next().toUpperCase();
                        String input = sc.nextLine().toUpperCase();
                        String dir = "";
                        if (input.indexOf("HELP") != -1)
                        {
                            userInputHelp();
                        }
                        else if (input.indexOf("QUIT") != -1)
                        {
                            userInputQuit();
                        }
                        else if (input.indexOf("EXIT") != -1)
                        {
                            userInputExit();
                        }
                        else if (input.indexOf("GO") != -1)
                        {
                            dir = input.substring(2);
                            dir = dir.trim();
                            if (dir.isEmpty())
                            {
                                System.out.println("Please specify a direction in the format \"GO <direction>\"");
                            }
                            else
                            {
                                userInputGo(dir);
                            }
                        }
                        else if (input.indexOf("LOOK") != -1)
                        {
                            dir = input.substring(4);
                            dir = dir.trim();
                            if (dir.isEmpty())
                            {
                                userInputLook();
                            }
                            else if (isDir(dir) == true)
                            {
                                userInputLookDir(dir);
                            }
                            else if (isPlaceItem(dir) == true || isPlayerItem(dir) == true)
                            {
                                player.getCurrentLocation().getItemDesc(dir);
                                player.getItemDesc(dir);
                            }
                            else
                            {
                                System.out.println("Sorry, that does not appear to be a direction nor an item in this room");
                            }
                        }
                        else if (input.indexOf("INVENTORY") != -1)
                        {
                            player.listItems();
                        }
                        else if (input.indexOf("INVE") != -1)
                        {
                            player.listItems();
                        }
                        else if (input.indexOf("GET") != -1)
                        {
                            dir = input.substring(3);
                            dir = dir.trim();
                            if (dir.isEmpty())
                            {
                                System.out.println("Please place in the format of \"GET <item>\"");
                                player.getCurrentLocation().listItems();
                            }
                            else if (isPlaceItem(dir) == true)
                            {
                                if (player.getCurrentLocation().getItemMove(dir) == true)
                                {
                                    if (player.getCurrentLocation().isArt(dir) == true)
                                    {
                                        if (player.getCurrentLocation().getArt(dir) != null)
                                        {
                                            player.addAnArt(player.getCurrentLocation().getArt(dir));
                                            player.getCurrentLocation().removeArt(dir);
                                            System.out.println("Picked up: " + dir);
                                        }
                                        else {
                                            System.out.println("There was an error with grabbing this artifact");
                                        }
                                    }
                                    else if (player.getCurrentLocation().isKey(dir) == true)
                                    {
                                        if (player.getCurrentLocation().getKey(dir) != null)
                                        {
                                            player.addAKey(player.getCurrentLocation().getKey(dir));
                                            player.getCurrentLocation().removeKey(dir);
                                            System.out.println("Picked up: " + dir);
                                        }
                                        else {
                                            System.out.println("There was an error with grabbing this Key");
                                        }
                                    }
                                    else if (player.getCurrentLocation().isLight(dir) == true)
                                    {
                                        if (player.getCurrentLocation().getLight(dir) != null)
                                        {
                                            player.addALight(player.getCurrentLocation().getLight(dir));
                                            player.getCurrentLocation().removeLight(dir);
                                            System.out.println("Picked up: " + dir);
                                        }
                                        else {
                                            System.out.println("There was an error with grabbing this Light");
                                        }
                                    }
                                }
                                else
                                {
                                    System.out.println("That is too heavy to pick up!");
                                }
                            }
                            else
                            {
                                System.out.println("That is not an item in this room!");
                            }
                        }
                        else if (input.indexOf("DROP") != -1)
                        {
                            dir = input.substring(4);
                            dir = dir.trim();
                            if (dir.isEmpty())
                            {
                                System.out.println("Please place in the format of \"DROP <item>\"");
                                player.listItems();
                            }
                            else if (isPlayerItem(dir) == true)
                            {
                                if (player.isArt(dir) == true)
                                {
                                    if (player.getArt(dir) != null)
                                    {
                                        player.getCurrentLocation().addAnArt(player.getArt(dir));
                                        player.removeArt(dir);
                                        System.out.println("Dropped: " + dir);
                                    }
                                    else {
                                        System.out.println("There was an error with dropping this artifact");
                                    }
                                }
                                else if (player.isKey(dir) == true)
                                {
                                    if (player.getKey(dir) != null)
                                    {
                                        player.getCurrentLocation().addAKey(player.getKey(dir));
                                        player.removeKey(dir);
                                        System.out.println("Dropped: " + dir);
                                    }
                                    else {
                                        System.out.println("There was an error with dropping this Key");
                                    }
                                }
                                else if (player.isLight(dir) == true)
                                {
                                    if (player.getLight(dir) != null)
                                    {
                                        player.getCurrentLocation().addALight(player.getLight(dir));
                                        player.removeLight(dir);
                                        System.out.println("Dropped: " + dir);
                                    }
                                    else {
                                        System.out.println("There was an error with dropping this Light");
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("That is not an item you are holding!");
                            }
                        }
                        else if (input.indexOf("USE") != -1)
                        {
                            
                        }
                        else 
                        {
                            System.out.println("Input was not recognized please try again or type 'HELP' for assistance.");
                        }
                        
                }
                
        }
        
        public boolean isPlaceItem(String str)
        {
            if (player.getCurrentLocation().getItems(str) == true)
            {
                return true;
            }
            return false;
        }
        
        public boolean isPlayerItem(String str)
        {
            if (player.getItems(str) == true)
            {
                return true;
            }
            return false;
        }
        
        public boolean isDir(String str)
        {
            for(String d : directionsAllowed){
                        if(d.equals(str)){
                            return true;
                        }
                }
            return false;
        }
        
        /**
         * Private method responsible for printing the description of the room the player is currently in.
         */
        private void userInputLook() { 
                outputPlayerLocation();
 
                for(Paths paths : player.getCurrentLocation().paths){
                        Places p = paths.getDestination();
                        if(p.exit){
                                System.out.println("There appears to be a gust of wind coming from somewhere...");
                        }
                }   
        }
        
        /**
         * Private method that outputs the description of a room in a specified direction.
         * @param di 
         */
        private void userInputLookDir(String di)
        {
            Places currentRoom = player.getCurrentLocation();
                
            di = reassignDir(di);
            
                for(Paths p : currentRoom.paths){
                        if(p.getDirection().equals(di)){
                            if (p.getLock() == false) {
                                System.out.println("Looking " + di);
                                if (p.getDestination().getLights() > 0)
                                {
                                    System.out.println(p.getDestination().description);
                                    p.getDestination().listItems();
                                }
                                else {
                                    System.out.println("It is too dark to see in that direction!");
                                }
                                return;
                            }
                            else 
                            {
                                System.out.println(di + " is locked!");
                                return;
                            }
                        }
                }
                
                System.out.println("There is no path in that direction..");
        } // end userInputLookDir(String di)
        
        /**
         * Private method responsible for handling the GO command from the user.
         * @param dir is the direction in which the player wants to move from their current location.
         */
        private void userInputGo(String dir) {
                Places currentRoom = player.getCurrentLocation();
                boolean validDirection = false;
                for(String d : directionsAllowed){
                        if(d.equals(dir)){
                                validDirection = true;
                                dir = reassignDir(d);
                                break;
                        }
                }
                
                if(!validDirection){
                        System.out.println("Not a valid direction, please use the format GO <direction>");
                        return;
                }
                
                for(Paths p : currentRoom.paths){
                        if(p.getDirection().equals(dir)){
                            if (p.getLock() == false) {
                                System.out.println("Moving " + dir);
                                player.updateCurrentLocation(p.getDestination());
                                return;
                            }
                            else 
                            {
                                System.out.println(dir + " is locked!");
                                return;
                            }
                        }
                }
                System.out.println("There is no path in that direction..");
                
        }

        /**
         * Private method that will stop the game loop and currently exits the application.
         */
        private void userInputExit() {
                System.out.println("Your time in this world is over, visit us again!");
                running = false;
                System.exit(1);
                
        }

        /**
         * Private method that will stop the game loop and also currently exits the application.
         */
        private void userInputQuit() {
                System.out.println("Your time here has come to an end friend.");
                running = false;
                System.exit(1);
                
        }

        /**
         * Private method responsible of handling the HELP command
         */
        private void userInputHelp() {
                System.out.println("You can type in the following commands: \n" +
                                "QUIT : Will exit the game application. \n" +
                                "EXIT : Will exit the game application. \n" +
                                "HELP : Opens the help menu. \n" +
                                "LOOK : Describes the room in the player is in. \n" +
                                "LOOK <direction> : Describes the room in the direction the player is looking toward. \n" +
                                "LOOK <item> : Describes the item in greater detail. \n" +
                                "GO <direction> : Moves the player in any of the allowed directions {N, NORTH, S, SOUTH, E, EAST, W, WEST, U, UP, D, DOWN, NE," +
                                "NW, SE, SW, NNE, NNW, SSE, SSW, ENE, ESE, WNW, WSW} \n" +
                                "INVE or INVENTORY : List the objects currently in the player's possession. \n" +
                                "GET <item> : Picks the item up from the room and places in the player's inventory. \n" +
                                "DROP <item> : Places an item from the players inventory into the current room. \n" + 
                                "USE <item> : Certain items have uses when conditions are met! Give it a try! \n"); 
        }

        /**
         * Private method that prints out the description of the current location of the player.
         */
        private void outputPlayerLocation(){
            if (player.getCurrentLocation().getLights() > 0)
            {
                System.out.println(player.getCurrentLocation().description);
                player.getCurrentLocation().listItems();
            }
            else {
                System.out.println("It is too dark to see!");
            }
        }

        /**
         * Initializer method for the player class called once the map is initialized.
         */
        private void initializePlayer() {
                
                player = new Player(map.getList().get(2));
                
                
        }

        /**
         * Takes direction from user input (already checked to be a valid input)
         * and reassigns it to the correct direction (ie. 'NORTH' will reassign to 'N')
         * @param di 
         */
        private String reassignDir(String di)
        {
            if (di == "NORTH")
            {
                return "N";
            }
            if (di == "SOUTH")
            {
                return "S";
            }
            if (di == "EAST")
            {
                return "E";
            }
            if (di == "WEST")
            {
                return "W";
            }
            if (di == "UP")
            {
                return "U";
            }
            if (di == "DOWN")
            {
                return "D";
            }
            
            
            return di;
        } // end reassignDir(String di)
        
        /**
         * Public main method that takes in a string representation of the location of the file to be opened.
         * @param args String for file location
         */
        public static void main(String[] args){
                
                System.out.println("Welcome to the World of Magic");
                String fileName = args[0];
                File textFile = null;
                int attempts = 0;
                System.out.println("Running on file " + fileName);
                while(textFile == null){
                        if(attempts >= 3){
                                System.err.println("Too many attempts closing program.");
                                System.exit(1);
                        }
                        if(attempts > 0){
                                System.out.println("File not found. Please ensure the file is in the same directory.");
                        }
                        
                        try {
                                textFile = new File(fileName);
                        } catch (Exception e) {
                                System.out.println("Woah Exceptions");
                        }
                        attempts++;
                }
                
                System.out.println("Initializing the world...");
                UserInput newGame = new UserInput(textFile);
                
                
                
                
                
        }


}
