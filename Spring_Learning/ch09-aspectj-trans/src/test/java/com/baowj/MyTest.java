package com.baowj;

import com.baowj.dao.GoodsDao;
import com.baowj.domain.Goods;
import com.baowj.domain.Sale;
import com.baowj.service.BuyGoodsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {


    @Test
    public void test01(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        BuyGoodsService buyGoodsService = (BuyGoodsService) ctx.getBean("buyService");
        buyGoodsService.buy(1001, 20);
    }
}
