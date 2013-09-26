/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luis Palacios
 * @author Tom Phillips
 */
public class Artifacts extends Objects {
    
    private int artID;
    
    public Artifacts(int artsID, int placesID, int money, int move, String objName, String desc)
    {
        artID = artsID;
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
  
    public void printArtItems()
    {
        System.out.println(name);
    }
    
}
