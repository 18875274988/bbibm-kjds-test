package com.rjkj.cj.bbibm.kjds.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rjkj.cf.common.core.util.R;
import com.rjkj.cj.bbibm.kjds.order.bo.OrderTranslateBo;
import com.rjkj.cj.bbibm.kjds.order.entity.ItemsPo;
import com.rjkj.cj.bbibm.kjds.order.entity.OrderPo;
import com.rjkj.cj.bbibm.kjds.order.entity.RecipientAddressPo;
import com.rjkj.cj.bbibm.kjds.order.mapper.ItemsMapper;
import com.rjkj.cj.bbibm.kjds.order.mapper.OrderMapper;
import com.rjkj.cj.bbibm.kjds.order.mapper.RecipientAddressMapper;
import com.rjkj.cj.bbibm.kjds.order.service.OrderTranslateService;
import com.rjkj.cj.bbibm.kjds.order.utils.DataUtil;
import com.rjkj.cj.bbibm.kjds.order.vo.QueryEnabledStoresVo;
import lombok.AllArgsConstructor;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 *
 * @author PuYinsheng
 * @date 2020/5/13
 * @copyright 2020 [图片]www.tydic.com Inc. All rights reserved.
 **/
@Service
@AllArgsConstructor
public class OrderTranslateServiceImpl implements OrderTranslateService {
    private final ItemsMapper itemsMapper;
    private final OrderMapper orderMapper;
    private final RecipientAddressMapper recipientAddressMapper;
    private final RedisTemplate redisTemplate;
    private final RestTemplate template;
    @Override
    public R OrderTranslate(OrderTranslateBo orderTranslateBo) {
        //1调用远程接口
        String url = "http://192.168.0.199:8666/goods/user/getOrder?area="+orderTranslateBo.getArea()+"&&shopId="+orderTranslateBo.getShopId();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        String result = null;
        try {
            Response response = call.execute();
             result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return R.ok(0);
        }
        JSONObject jsonObject = JSON.parseObject(result);
        JSONArray orders = jsonObject.getJSONArray("orders");
        ArrayList<String> ordersns = new ArrayList<>();
        for (Object order : orders) {
            String orderString = JSONObject.toJSONString(order);
            JSONObject jsonObject1 = JSON.parseObject(orderString);
            ordersns.add(jsonObject1.getString("ordersn"));
        }

        for (String ordersn : ordersns) {
            ValueOperations valueOperations = redisTemplate.opsForValue();
            /**
             * HashOperations hashOperations = redisTemplate.opsForHash();
             *  Boolean taobao = hashOperations.hasKey("taobao", "1231231312");
             */
            Object ordersn2 = valueOperations.get("ordersn");
            //判断数据是否存在
            if (!(ordersn.equals(String.valueOf(ordersn2)))){
               // 数据层操作
                for (Object order : orders) {
                    OrderPo orderPo = new OrderPo();
                    String orderString = JSONObject.toJSONString(order);
                    JSONObject jsonObject1 = JSON.parseObject(orderString);
                    orderPo.setCreateTime(DataUtil.LongToDate(jsonObject1.getLong("create_time")));
                    orderPo.setPayTime(DataUtil.LongToDate(jsonObject1.getLong("pay_time")));
                    orderPo.setUpdateTime(DataUtil.LongToDate(jsonObject1.getLong("update_time")));
                    orderPo.setShipByDate(DataUtil.LongToDate(jsonObject1.getLong("ship_by_date")));
                    orderPo.setEstimatedShippingFee(jsonObject1.getString("estimated_shipping_fee"));
                    orderPo.setPaymentMethod(jsonObject1.getString("payment_method"));
                    orderPo.setMessageToSeller(jsonObject1.getString("message_to_seller"));
                    orderPo.setShippingCarrier(jsonObject1.getString("shipping_carrier"));
                    orderPo.setCurrency(jsonObject1.getString("currency"));
                    orderPo.setDaysToShip(Long.valueOf(jsonObject1.getString("days_to_ship")));
                    orderPo.setTrackingNo(jsonObject1.getString("tracking_no"));
                    orderPo.setOrderStatus(jsonObject1.getString("order_status"));
                    orderPo.setEscrowAmount(jsonObject1.getString("escrow_amount"));
                    orderPo.setGoodsToDeclare(jsonObject1.getString("goods_to_declare"));
                    orderPo.setTotalAmount(jsonObject1.getString("total_amount"));
                    orderPo.setServiceCode(jsonObject1.getString("service_code"));
                    orderPo.setCountry(jsonObject1.getString("country"));
                    orderPo.setOrdersn(jsonObject1.getString("ordersn"));
                    orderPo.setBuyerUsername(jsonObject1.getString("buyer_username"));
                    orderMapper.insert(orderPo);
                    String ordersn1 = orderPo.getOrdersn();
                    //插入Redis
                    redisTemplate.opsForValue().set(ordersn1,orderPo.getOrderStatus());
                    //写入商品详情
                    JSONArray items = jsonObject1.getJSONArray("items");
                    JSONObject recipientAddress = jsonObject1.getJSONObject("recipient_address");
                    for (Object item : items) {
                        String itemString = JSONObject.toJSONString(item);
                        JSONObject itemObject = JSON.parseObject(itemString);
                        ItemsPo itemsPo = new ItemsPo();
                        itemsPo.setOrdersn(ordersn1);
                        itemsPo.setWeight(itemObject.getDouble("weight"));
                        itemsPo.setItemName(itemObject.getString("item_name"));
                        itemsPo.setItemSku(itemObject.getString("item_sku"));
                        itemsPo.setVariationDiscountedPrice(itemObject.getDouble("variation_discounted_price"));
                        itemsPo.setVariationId(itemObject.getLong("variation_id"));
                        itemsPo.setVariationName(itemObject.getString("variation_name"));
                        itemsPo.setIsAddOnDeal(itemObject.getString("is_add_on_deal"));
                        itemsPo.setItemId(itemObject.getLong("item_id"));
                        itemsPo.setVariationSku(itemObject.getString("variation_sku"));
                        itemsPo.setVariationOriginalPrice(itemObject.getDouble("variation_original_price"));
                        itemsPo.setIsMainItem(itemObject.getString("is_main_item"));
                        itemsMapper.insert(itemsPo);
                    }
                    //写入地址详情
                    RecipientAddressPo recipientAddressPo = new RecipientAddressPo();
                    recipientAddressPo.setPhone(recipientAddress.getString("phone"));
                    recipientAddressPo.setName(recipientAddress.getString("name"));
                    recipientAddressPo.setFullAddress(recipientAddress.getString("full_address"));
                    recipientAddressPo.setOrdersn(ordersn);

                    recipientAddressMapper.insert(recipientAddressPo);
                }
            }else {
                for (Object order : orders) {
                    String orderString = JSONObject.toJSONString(order);
                    JSONObject jsonObject1 = JSON.parseObject(orderString);
                    String orderStatus = jsonObject1.getString("order_status");
                    //判断是否更新
                    if(orderStatus.equals(ordersn2)){
                        //判断是否更新
                        redisTemplate.opsForValue().set(ordersn,orderStatus);
                        orderMapper.updeOrderStatus(ordersn);
                    }
                }
                }
            }

        return R.ok(1);
    }

    @Override
    public R OrderTranslates() {
        //调用goods模块
        List<LinkedHashMap<String, String>> data = (List<LinkedHashMap<String, String>>) template.getForObject("http://kjds-goods/goods/queryEnabledStores", R.class).getData();
        for (LinkedHashMap<String, String> datum : data) {
            OrderTranslateBo orderTranslateBo = new OrderTranslateBo();
            orderTranslateBo.setShopId(datum.get("sid"));
            orderTranslateBo.setArea(datum.get("area"));
            OrderTranslate(orderTranslateBo);
        }
        System.out.println();
        //调用子功能
        return null;
    }
}
