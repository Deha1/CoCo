package com.coco.service.imp;


import com.coco.mapper.ShopCarMapper;
import com.coco.pojo.ShopCar;
import com.coco.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCarServiceImp implements ShopCarService {
    @Autowired
    private ShopCarMapper shopCarMapper;

    @Override
    public List<ShopCar> findShopCar() {
        List<ShopCar> list=shopCarMapper.findShopCarGoods();
        return list;
    }
    @Override
    public void addShopCarService(ShopCar shopCar) {

        // 判断逻辑 判断用户id ，和商品名字是否重复添加
        List<ShopCar> list=shopCarMapper.findShopCarGoods();

        for (ShopCar shopCar1:list) {
            //

            Integer Sid=shopCar1.getShopId();
            Integer Gid=shopCar1.getGoodsId();
            Integer userId=shopCar1.getUserId();
            //

            Integer Sid1=shopCar.getShopId();
            Integer Gid1=shopCar.getGoodsId();
            Integer userId1=shopCar.getUserId();
            System.out.println(Sid+"-----"+Sid1);
            System.out.println(Gid+"-----"+Gid1);
            System.out.println(userId+"-------"+userId1);
            if(userId==userId1&&Gid==Gid1&&Sid==Sid1){
                System.out.println("只能增加一次");
                return ;
            }

        }

        //迭代器无法从中取出两个数据，最好用foreach！！！
        /*  Iterator<ShopCar> iterator=list.iterator();

             while (iterator.hasNext()){

            String s=iterator.next().getGoodsName();
            int i=iterator.next().getUserId();
            System.out.println(i);

            String s1=shopCar.getGoodsName();
            int id=shopCar.getUserId();
            System.out.println(id);
            System.out.println(s+"-----"+s1);
            System.out.println(id+"-------"+i);
            if(s.equals(s1))
            {

                System.out.println("只能添加一次");
                return ;
            }
        }*/
        shopCarMapper.addShopCar(shopCar);

    }

    @Override
    public void updateShopCar(ShopCar shopCar) {
        shopCarMapper.updataShopCar(shopCar);
    }

    @Override
    public void deleteShopCarById(int id) {
        shopCarMapper.deleteShopCarById(id);
    }

    @Override
    public List<ShopCar> personShopCar(int useId) {
        List<ShopCar> shopCars=shopCarMapper.personShopCar(useId);
        return shopCars;
    }

    @Override

    public void deleteAllShopCarByUserId(int userId) {
        shopCarMapper.deleteAllShopCarByUserId(userId);
    }

    @Override
    public ShopCar findaShopCar(int goodsId, int shopId, int userId) {
        ShopCar shopCar=shopCarMapper.findaShopCar(goodsId,shopId,userId);
        return shopCar;
    }
}
