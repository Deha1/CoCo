package com.coco.service;

import com.coco.commom.ServerResponse;
import com.coco.commom.ServerResponse;
import com.coco.pojo.Goods;

public interface FootService {

    public ServerResponse findFoot(int user_id);

    public void addFoot(Goods goods, int user_id);

    public void updateFoot(Goods goods);

    public ServerResponse deleteAllFoot(int user_id);

}
