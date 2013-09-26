/**
 * The Paths class represents a hallway or path between rooms.
 * @author Luis Palacios
 */
public class Paths {
        
        /**
         * Places object which is attached to the entrance to this path.
         */
        private Places source;
        /**
         * Places object which is attached to the exit of this path.
         */
        private Places destination;
        
        /**
         * String object representing the direction of this path from source to destination.
         */
        private String direction;
        
        /**
         * Shows whether this path is locked or not.
         */
        private boolean locked; // whether or not the path is locked or not
        private int lockCombo; // lockPattern = 0 indicates there is no key that can change this lock status
        
        /**
         * Public constructor
         * @param src is the source room
         * @param dst is the destination room
         * @param d is the direction the path points
         */
        public Paths(Places src, Places dst, String d, boolean lock, int lockNum){
                if(src == null || dst == null || d == null){
                        if(src == null)
                                System.out.println("source is null");
                                                
                        if(dst == null)
                                System.out.println("dest is null");
                        
                        if(d == null)
                                System.out.println("d is null");
                }
                source = src;
                destination = dst;
                direction = d;
                locked = lock;
                lockCombo = lockNum;
                
        }
        
        /**
         * Public getter for source.
         * @return source
         */
        public Places getSource(){
                return source;
        }
        
        /**
         * Public getter for destination
         * @return destination
         */
        public Places getDestination(){
                return destination;
                
        }
        
        /**
         * Public getter for direction
         * @return direction
         */
        public String getDirection(){
                return direction;
        }
        
        public boolean getLock()
        {
            return locked;
        }

}
