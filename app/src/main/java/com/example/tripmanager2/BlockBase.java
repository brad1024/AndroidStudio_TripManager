package com.example.tripmanager2;

import java.sql.Struct;
import java.util.HashMap;

public abstract class BlockBase {
    public BlockBase(){};
    public BlockBase(BlockType _type, String _name, int _id, int _begin, int _end){
        type = _type;
        name = _name;
        id = _id;
        beginTime = _begin;
        endTime = _end;
        stayTime = endTime - beginTime;
    };
    public BlockType type;
    public String name;
    public int id;
    public int beginTime;
    public int endTime;
    public int stayTime;

    public enum BlockType{
        dest, trans, transRequest
    }

    public void ChangeBeginTime(int _begin){
        beginTime = _begin;
        endTime = beginTime + stayTime;
    }
    public void ChangeEndTime(int _end){
        endTime = _end;
        beginTime = endTime - stayTime;
    }
    public void ChangeStayTime(int _stay){
        stayTime = _stay;
        endTime = beginTime + stayTime;
    }


}
