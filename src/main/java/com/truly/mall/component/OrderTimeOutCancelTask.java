package com.truly.mall.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author truly
 * @date 2022/10/26 23:55
 * 订单超时取消并解锁库存的定时任务
 */
@Component
public class OrderTimeOutCancelTask {
    private Logger LOGGER= LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10分钟扫描一次，扫描设定超时时间之前下的订单，如果没支付则取消该订单
     */
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void cancelTimeOutOrder(){
        //取消订单
        LOGGER.debug("取消订单，并根据sku编写释放锁定库存；id:{}");
    }
}
