package com.rjkj.cj.bbibm.kjds.order;

import com.rjkj.cf.common.security.annotation.EnableRjkjFeignClients;
import com.rjkj.cf.common.security.annotation.EnableRjkjResourceServer;
import com.rjkj.cf.common.swgger.annotation.EnableRJSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @描述：商户管理模块
 * @项目：bbibm-kjds
 * @公司：软江科技
 * @作者：YiHao
 * @时间：2019/10/8 15:52
 **/
@EnableRJSwagger2
@EnableRjkjFeignClients
@EnableRjkjResourceServer
@SpringCloudApplication
//@EnableDistributedTransaction
@EnableAsync
public class KjdsOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(KjdsOrderApplication.class);
    }

}
