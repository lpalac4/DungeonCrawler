import java.util.ArrayList;

//import java.util.ArrayList

/**
 *
 * @author Luis Palacios
 * @author Tom Phillips
 */
public class Inventory {
    
    private ArrayList<Artifacts> artList = new ArrayList<Artifacts>();
    private ArrayList<Keys> keyList = new ArrayList<Keys>();
    private ArrayList<Lights> lightList = new ArrayList<Lights>();
    
    public void addArt(int index, int source, int moneyVal, int moves, String item, String itemDesc)
    {
        Artifacts a = new Artifacts(index, source, moneyVal, moves, item, itemDesc);
        artList.add(a);
    }
    
    public void addKey(int index,int source,int keyVal,int masterVal,int moneyVal,int moves,String item,String itemDesc)
    {
        Keys k = new Keys(index,source,keyVal,masterVal,moneyVal,moves,item,itemDesc);
        keyList.add(k);
    }
    
    public void addLight(int index,int source,int lightVal,int moneyVal,int moves,String item,String itemDesc)
    {
        Lights l = new Lights(index,source,lightVal,moneyVal,moves,item,itemDesc);
        lightList.add(l);
    }
    
    public void addAnArt(Artifacts a)
    {
        artList.add(a);
    }
    
    public void addAKey(Keys k)
    {
        keyList.add(k);
    }
    
    public void addALight(Lights l)
    {
        lightList.add(l);
    }
    
    public void removeArt(String str)
    {
        for (int i = 0; i < artList.size(); i++)
        {
            if (artList.get(i).name.toUpperCase().equals(str))
            {
                artList.remove(i);
            }
        }
    }
    
    public void removeKey(String str)
    {
        for (int i = 0; i < keyList.size(); i++)
        {
            if (keyList.get(i).name.toUpperCase().equals(str))
            {
                keyList.remove(i);
            }
        }
    }
    
    public void removeLight(String str)
    {
        for (int i = 0; i < lightList.size(); i++)
        {
            if (lightList.get(i).name.toUpperCase().equals(str))
            {
                lightList.remove(i);
            }
        }
    }
    
    public Artifacts getArt(String str)
    {
        for (Artifacts a : artList)
        {
            if (a.name.toUpperCase().equals(str))
            {
                return a;
            }
        }
        return null;
    }
    
    public Lights getLight(String str)
    {
        for (Lights l : lightList)
        {
            if (l.name.toUpperCase().equals(str))
            {
                return l;
            }
        }
        return null;
    }
    
    public Keys getKey(String str)
    {
        for (Keys k : keyList)
        {
            if (k.name.toUpperCase().equals(str))
            {
                return k;
            }
        }
        return null;
    }
    
    /**
     * Prints a list of items in the room
     */
    public void getArtList()
    {
        for (Artifacts a : artList)
        {
            a.printArtItems();
        }
    }
    
    /**
     * Prints a list of items in the room
     */
    public void getKeyList()
    {
        for (Keys k : keyList)
        {
            k.printKeyItems();
        }
    }
    
    /**
     * Prints a list of items in the room
     */
    public void getLightList()
    {
        for (Lights l : lightList)
        {
            l.printLightItems();
        }
    }
    
    public boolean isMoveable(String str)
    {
        for (Artifacts a : artList)
        {
            if(a.name.toUpperCase().equals(str)){
                if (a.movability == 1)
                {
                    return true;
                }
                return false;
            }
        }
        for (Keys k : keyList)
        {
            if (k.name.toUpperCase().equals(str)){
                if (k.movability == 1)
                {
                    return true;
                }
                return false;
            }
        }
        for (Lights l : lightList)
        {
            if (l.name.toUpperCase().equals(str)){
                if (l.movability == 1)
                {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    
    /**
     * If the artifact a user is looking at exists in this room it will return the description.
     * @param str
     * @return 
     */
    public boolean compItems(String str)
    {
        for (Artifacts a : artList)
        {
            if(a.name.toUpperCase().equals(str)){
                return true;
            }
        }
        for (Keys k : keyList)
        {
            if (k.name.toUpperCase().equals(str)){
                return true;
            }
        }
        for (Lights l : lightList)
        {
            if (l.name.toUpperCase().equals(str)){
                return true;
            }
        }
        return false;
    }
    
    public void getItemDesc(String str)
    {
        for (Artifacts a : artList)
        {
            if(a.name.toUpperCase().equals(str)){
                System.out.println(a.description);
            }
        }
        for (Keys k : keyList)
        {
            if (k.name.toUpperCase().equals(str)){
                System.out.println(k.description);
            }
        }
        for (Lights l : lightList)
        {
            if (l.name.toUpperCase().equals(str)){
                System.out.println(l.description);
            }
        }
    }

    public boolean isArt(String str)
    {
        for (Artifacts a : artList)
        {
            if(a.name.toUpperCase().equals(str)){
                return true;
            }
        }
        return false;
    }
    
    public boolean isKey(String str)
    {
        for (Keys k : keyList)
        {
            if(k.name.toUpperCase().equals(str)){
                return true;
            }
        }
        return false;
    }
    
    public boolean isLight(String str)
    {
        for (Lights l : lightList)
        {
            if(l.name.toUpperCase().equals(str)){
                return true;
            }
        }
        return false;
    }
}
