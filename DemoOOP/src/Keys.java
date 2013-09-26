/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luis Palacios
 * @author Tom Phillips
 */
public class Keys extends Objects {
    
    private int keyID;
    private int keyPattern;
    private int masterCode;
    
    public Keys(int keysID, int placesID, int keysPattern, int mastersCode, int money, int move, String objName, String desc)
    {
        keyID = keysID;
        keyPattern = keysPattern;
        masterCode = mastersCode;
        placeID = placesID;
        value = money;
        movability = move;
        name = objName;
        description = desc;
    }
    
    public void updatePlace(int newPlace)
    {
        placeID = newPlace;
    }
    
    public void useKey()
    {
        
    }
    
    public void printKeyItems()
    {
        System.out.println(name);
    }
}
