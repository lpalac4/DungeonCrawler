import java.util.ArrayList;


/**
 * Places class is the abstract representation of a room.
 * @author Luis Palacios
 * @author Tom Phillips
 *
 */
public class Places {
        
        /**
         * Name of the room.
         */
        private String name;
        /**
         * Identification number of the room.
         */
        int id;
        /**
         * Description of the room.
         */
        String description; 
        /**
         * Array containing all paths attached to this room.
         */
        ArrayList<Paths> paths;
        /**
         * Boolean set to true if this room is attached to an Exit path.
         */
        boolean exit;
        
        private Inventory placeInv;
        int lights = 100; //light level for the room, if it is 0 then you cannot see
        
        /**
         * Public constructor
         * @param n name of the place/room.
         * @param ID identification number.
         * @param desc description of the room.
         */
        public Places(String n, int ID, String desc, int lighting){
                if(ID == 1)
                        exit = true;
                else 
                        exit = false;
                
                paths = new ArrayList<Paths>();
                name = n;
                id = ID;
                description = desc;
                lights = lighting;
                placeInv = new Inventory();
                
                
                
        }
        
        /**
         * Public method to add new paths to this room.
         */
        public void addPath(Paths p){
                paths.add(p);
        }
        
        /**
         * Sets the light level for a room.
         * @param lig 
         */
        public void setLight(int lig)
        {
            lights = lig;
        }
        
        /**
         * Returns the light level for a given room.
         * A value of 0 means the user cannot see.
         * @return 
         */
        public int getLights()
        {
            return lights;
        }
        
        /**
         * Checks to see if the item the user is searching for exists in this room
         * @param str
         * @return 
         */
        public boolean getItems(String str)
        {
            return placeInv.compItems(str);
        }
        
        public void getItemDesc(String str)
        {
            placeInv.getItemDesc(str);
        }
        
        public boolean getItemMove(String str)
        {
            return placeInv.isMoveable(str);
        }
        
        /**
         * adds an artifact to the place
         * @param a 
         */
        public void addArt(int index, int source, int moneyVal, int moves, String item, String itemDesc){
                placeInv.addArt(index,source, moneyVal,moves,item,itemDesc);
        }
        
        public void addKey(int index, int source, int keyVal, int masterVal, int moneyVal, int moves, String item, String itemDesc)
        {
            placeInv.addKey(index,source,keyVal,masterVal,moneyVal,moves,item,itemDesc);
        }
        
        public void addLight(int index, int source, int lightVal, int moneyVal, int moves, String item, String itemDesc)
        {
            placeInv.addLight(index,source,lightVal,moneyVal,moves,item,itemDesc);
        }
        
        public void addAnArt(Artifacts a)
        {
            placeInv.addAnArt(a);
        }
    
        public void addAKey(Keys k)
        {
            placeInv.addAKey(k);
        }

        public void addALight(Lights l)
        {
            placeInv.addALight(l);

        }
        
        public void listItems()
        {
            System.out.println("\nYou can see the following items: ");
            
            placeInv.getArtList();
            placeInv.getKeyList();
            placeInv.getLightList();
        }
        
        public boolean isArt(String str)
        {
            return placeInv.isArt(str);
        }
        
        public boolean isKey(String str)
        {
            return placeInv.isKey(str);
        }
        
        public boolean isLight(String str)
        {
            return placeInv.isLight(str);
        }
        
        public Artifacts getArt(String str)
        {
            return placeInv.getArt(str);
        }
        
        public Keys getKey(String str)
        {
            return placeInv.getKey(str);
        }
        
        public Lights getLight(String str)
        {
            return placeInv.getLight(str);
        }
        
        public void removeArt(String str)
        {
            placeInv.removeArt(str);
        }
        
        public void removeKey(String str)
        {
            placeInv.removeKey(str);
        }
        
        public void removeLight(String str)
        {
            placeInv.removeLight(str);
        }
}
