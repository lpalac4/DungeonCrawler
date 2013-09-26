/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luis Palacios
 * @author Tom Phillips
 */
public class Lights extends Objects {
    
    private int lightID;
    private int lightLevel;
    
    public Lights(int lightsID,int placesID, int lightsLevel, int money, int move, String objName, String desc)
    {
        lightID = lightsID;
        lightLevel = lightsLevel;
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
    
    public void useLight()
    {
        
    }
    
    public void printLightItems()
    {
        System.out.println(name);
    }
}
