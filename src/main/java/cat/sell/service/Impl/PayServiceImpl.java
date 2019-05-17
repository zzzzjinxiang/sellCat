package cat.sell.service.Impl;

import cat.sell.dto.OrderDTO;
import cat.sell.service.PayService;
import cat.sell.utils.JsonUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String s = "abc";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Override
    public void create(OrderDTO orderDTO) {

        PayRequest request = new PayRequest();
        request.setOpenid(orderDTO.getBuyerOpenid());
        request.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        request.setOrderId(orderDTO.getOrderId());
        request.setOrderName(s);
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】request={}", JsonUtil.toJson(request));
        PayResponse payResponse = bestPayService.pay(request);
        log.info("【微信支付】request={}", JsonUtil.toJson(payResponse));
    }
}
