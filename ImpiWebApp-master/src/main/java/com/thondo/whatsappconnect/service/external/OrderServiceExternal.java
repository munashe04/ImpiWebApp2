package com.thondo.whatsappconnect.service.external;

import com.thondo.whatsappconnect.entity.Orders;
import com.thondo.whatsappconnect.model.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderServiceExternal {

    public List<OrderDTO> ordersByCustomer(String customerName);
    public List<OrderDTO> getPendingOrders();
    public List<OrderDTO> getPaymentPendingOrders();
    public List<OrderDTO> getDispatchedOrders();
    public List<OrderDTO> getOrdersByAgent(String agentNumber);
    public List<OrderDTO> getOrders();
    public String dispatchStatus(String id);
    public Orders paymentReceived(String id);
    public String declineOrder(String id);

}
