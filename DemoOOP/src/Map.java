import java.util.ArrayList;

/**
 * Map class will hold information about the entire game world including all Places and Paths.
 * @author Luis Palacios
 * @author Tom Phillips
 *
 */
public class Map {
        /**
         * Array List containing all of the rooms in the game world.
         */
        private ArrayList<Places> map;
        
        /**
         * Public constructor
         */
        public Map(){
                map = new ArrayList<Places>();
                
        }
        
        /**
         * Public method to add additional rooms.
         * @param room : Places object to append to the Array List.
         */
        public void addRoom(Places room){
                map.add(room);
        }
        
        /**
         * Public getter for the ArrayList representing the entire map.
         * @return map : ArrayList.
         */
        public ArrayList<Places> getList(){
                return map;
        }
        
        /**
         * Public method that deletes a room/Places from the ArrayList map. 
         * @param roomToDelete is a pointer to the room to delete.
         */
        public void deleteRoomFromMap(Places roomToDelete){
                if(map.contains(roomToDelete))
                        map.remove(roomToDelete);
                        
        }
        
        /**
         * Public method that returns the room represented by the integer id, if there exists one within
         * the map.
         * @param id is the unique integer value attached to the room.
         * @return pointer to the room within the map or null if no map exists with such id.
         */
        public Places findRoomById(int id){
                for(Places x : map){
                        if(x.id == id)
                                return x;
                }
                
                return null;
        }
        
        
}
