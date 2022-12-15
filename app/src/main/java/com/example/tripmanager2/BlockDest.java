package com.example.tripmanager2;

import java.util.HashMap;

public class BlockDest extends  BlockBase {
    public BlockDest(){};
    public BlockDest(BlockType _type, String _name, int _id, int _begin, int _end, HashMap<String, Float> _coord){
        super(_type, _name, _id, _begin, _end);
        coordinate = _coord;
    }

    public HashMap<String, Float> coordinate;

}
