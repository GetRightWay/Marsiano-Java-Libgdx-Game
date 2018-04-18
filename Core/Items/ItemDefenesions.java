package com.mygdx.game.Items;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by interstaller on 3/22/2018.
 */

public class ItemDefenesions  {
    public Vector2 position;
    public Class<?> Type_Of_Item;
    public  ItemDefenesions(Class<?> Type_Of_Item,Vector2 position){
        this.Type_Of_Item=Type_Of_Item;
        this.position=position;
    }
}
