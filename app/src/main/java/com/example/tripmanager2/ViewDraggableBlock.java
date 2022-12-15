package com.example.tripmanager2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ViewDraggableBlock extends ViewBlock {

    public ViewDraggableBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void SetData(BlockBase _block) {
        super.SetData(_block);

        if(_block.type== BlockBase.BlockType.trans){
            BlockTrans transData = (BlockTrans) _block;
            setText(String.format("%s:%s %s -> %s", transData.name, transData.transportType, transData.from, transData.to));
        }

        setOnTouchListener(new OnTouchListener() {
            float y=0, dy=0;
            int initBegin;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        //System.out.println("ACTION_DOWN");
                        y = event.getY();
                        initBegin = (int)view.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //System.out.println("ACTION_MOVE");
                        dy = event.getY() - y;
                        view.setY(view.getY() + dy);
                        break;
                    case MotionEvent.ACTION_UP:
                        block.ChangeBeginTime(block.beginTime + (int)((view.getY()-initBegin)/fragment.ratio));
                        mainActivity.blockManager.UpdateBlockList((int)view.getY()-initBegin);
                        fragment.ShowBlock();
                        break;
                    default:break;
                }
                return true;
            }
        });
    }
}
