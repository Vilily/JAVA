package com.baowj.service.impl;

import com.baowj.dao.GoodsDao;
import com.baowj.dao.SaleDao;
import com.baowj.domain.Goods;
import com.baowj.domain.Sale;
import com.baowj.excep.NotEnoughException;
import com.baowj.service.BuyGoodsService;

public class BuyGoodsServiceImpl implements BuyGoodsService {
    private SaleDao saleDao;
    private GoodsDao goodsDao;


    @Override
    public void buy(Integer id, Integer nums) {
        System.out.println("===== buy 方法开始=====");
        // 记录销售信息
        Sale sale = new Sale();
        sale.setGid(id);
        sale.setNums(nums);
        saleDao.insertSale(sale);
        // 更新库存
        Goods goods = goodsDao.selectGoods(id);
        if(goods == null){
            throw new NullPointerException("编号是：" + id + ",商品不存在");
        }else if(goods.getAmount() < nums){
            throw new NotEnoughException("编号是：" + id + "，商品库存不足");
        }
        Goods buyGoods = new Goods();
        buyGoods.setId(id);
        buyGoods.setAmount(nums);
        goodsDao.updateGoods(buyGoods);
        System.out.println("===== buy 方法完成=====");
    }

    public void setSaleDao(SaleDao saleDao) {
        this.saleDao = saleDao;
    }

    public void setGoodsDao(GoodsDao goodsDao){
        this.goodsDao = goodsDao;
    }
}
