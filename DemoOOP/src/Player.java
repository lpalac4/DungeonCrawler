/**
 * Player class holds necessary data pertaining to the player.
 * @author Luis Palacios
 * @author Tom Phillips
 *
 */
public class Player {

        /**
         * Players current location on the map
         */
        private Places currentLocation;
        private Inventory playInv = new Inventory();
        
        /**
         * Constructor for Player
         */
        public Player(Places currentRoom){
                currentLocation = currentRoom;
        }
        
        public Places getCurrentLocation(){
                return currentLocation;
        }
        
        public void updateCurrentLocation(Places newRoom){
                currentLocation = newRoom;
        }
        
        public void listItems()
        {
            System.out.println("\nYou are holding the following items: ");

            playInv.getArtList();
            playInv.getKeyList();
            playInv.getLightList();
        }
        
        /**
         * adds an artifact to the player
         */
        public void addArt(int index, int source, int moneyVal, int moves, String item, String itemDesc){
            playInv.addArt(index,source, moneyVal,moves,item,itemDesc);
        }
        
        public void addKey(int index, int source, int keyVal, int masterVal, int moneyVal, int moves, String item, String itemDesc)
        {
            playInv.addKey(index,source,keyVal,masterVal,moneyVal,moves,item,itemDesc);
        }
        
        public void addLight(int index, int source, int lightVal, int moneyVal, int moves, String item, String itemDesc)
        {
            playInv.addLight(index,source,lightVal,moneyVal,moves,item,itemDesc);
        }
        
        public void addAnArt(Artifacts a)
        {
            playInv.addAnArt(a);
        }
    
        public void addAKey(Keys k)
        {
            playInv.addAKey(k);
        }

        public void addALight(Lights l)
        {
            playInv.addALight(l);

        }
        
        public boolean getItems(String str)
        {
            return playInv.compItems(str);
        }
        
        public void getItemDesc(String str)
        {
            playInv.getItemDesc(str);
        }
        
        public boolean isArt(String str)
        {
            return playInv.isArt(str);
        }
        
        public boolean isKey(String str)
        {
            return playInv.isKey(str);
        }
        
        public boolean isLight(String str)
        {
            return playInv.isLight(str);
        }
        
        public Artifacts getArt(String str)
        {
            return playInv.getArt(str);
        }
        
        public Keys getKey(String str)
        {
            return playInv.getKey(str);
        }
        
        public Lights getLight(String str)
        {
            return playInv.getLight(str);
        }
        
        public void removeArt(String str)
        {
            playInv.removeArt(str);
        }
        
        public void removeKey(String str)
        {
            playInv.removeKey(str);
        }
        
        public void removeLight(String str)
        {
            playInv.removeLight(str);
        }
}
