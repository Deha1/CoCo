package com.coco.service.imp;

import com.coco.commom.ServerResponse;
import com.coco.mapper.FootMapper;
import com.coco.pojo.Foot;
import com.coco.pojo.Goods;
import com.coco.service.FootService;
import com.coco.service.SelectService;
import com.coco.commom.vo.GoodsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class FootServiceImp implements FootService {

    @Autowired
    FootMapper footMapper;
    @Autowired
    SelectService selectService;


    @Override
    public void updateFoot(Goods goods) {

        Foot foot = new Foot();
        foot.setGoodsId(goods.getId());
        footMapper.updateFoot(foot);
    }

    @Override
    @Transactional
    public void addFoot(Goods goods, int user_id) {

            //如果存在此足迹，则更新此足迹
//            List<Foot> listAll = footMapper.findAllFoot();
//            for (int i = 0; i < listAll.size(); i++) {
//                if (listAll.get(i).getGoods_id() == goods.getId()) {
//                    this.updateFoot(goods);
//                    return;
//                }
//            }
             Foot foot = footMapper.findFootById(goods.getId(),user_id);
             if (foot !=null){
                 this.updateFoot(goods);
                 return;
             }

            //没有此足迹，增加一条足迹
            Foot co_footnew = new Foot();
            co_footnew.setUserId(user_id);
            co_footnew.setGoodsId(goods.getId());
            footMapper.insertFoot(co_footnew);

    }

    @Override
    public ServerResponse findFoot(int user_id) {

        //查询出改用户所有的足迹
        List<Foot> listAll=footMapper.findAllFootByUserId(user_id);
        List<Foot> list = new ArrayList<>();
        //选出30天内的足迹
        for (int cont=0;cont<listAll.size();cont++){

            Date update_time = listAll.get(cont).getUpdateTime();
            Date now_date= new Date();
            Date overdue_time = new Date(update_time.getTime()+ 30 * 24 * 60 * 60 * 1000L);
            if(now_date.compareTo(overdue_time)<=0){
                list.add(listAll.get(cont));
            }
        }
        //将足迹按照更新顺序排序
        Collections.sort(list);

        List<GoodsPage> lgpv = new ArrayList();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {

            //根据商品Id查出商品
            Foot foot =(Foot) iterator.next();
            Goods goods = new Goods();
            goods.setId(foot.getGoodsId());

           //封装成goodsPageVo
            GoodsPage gpv = new GoodsPage();
            gpv = selectService.goodsToGoodsPageVo(selectService.getGoods(goods));

            //将goodsPageVo放入集合中
            lgpv.add(gpv);
        }

        return ServerResponse.createBySuccess(lgpv);
    }

    @Override
    public ServerResponse deleteAllFoot(int user_id) {

        footMapper.deleteAllFoot(user_id);

        return ServerResponse.createBySuccessMessage("删除所有足迹");
    }
}
