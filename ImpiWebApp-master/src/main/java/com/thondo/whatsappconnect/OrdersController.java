package com.thondo.whatsappconnect;

import com.thondo.whatsappconnect.entity.Orders;
import com.thondo.whatsappconnect.model.OrderDTO;
import com.thondo.whatsappconnect.service.MessageProcessorService;
import com.thondo.whatsappconnect.service.external.OrderServiceExternal;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins ="http://localhost:4200" )
@RequestMapping("/orders")

public class OrdersController {

    @Qualifier("orderServiceImplExternal")
    @Autowired
    OrderServiceExternal orderServiceExt;
    @Autowired
    private MessageProcessorService messageProcessorService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins ="http://localhost:4200" )
    public List<OrderDTO> getOrder() {
        System.out.println(" getting orders >>>>>>>>>>>>>>>>>");
        return orderServiceExt.getOrders();
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/byAgent/{refMobileNumber}")
    @CrossOrigin(origins ="http://localhost:4200" )
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getOrderByAgent(@PathVariable ("refMobileNumber") String refMobileNumber) {
        System.out.println(" getting orders >>>>>>>>>>>>>>>>>");

        return orderServiceExt.getOrdersByAgent(refMobileNumber);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/pending")
    @CrossOrigin(origins ="http://localhost:4200" )
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getPendingOrder() {
        System.out.println(" getting pending orders >>>>>>>>>>>>>>>>>");
        return orderServiceExt.getPendingOrders();
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/byCustomer/{customerName}")
    @CrossOrigin(origins ="http://localhost:4200" )
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> ordersByCustomer(@PathVariable ("customerName") String customerName) {
        System.out.println(" getting orders by customer>>>>>>>>>>>>>>>>>");
        return orderServiceExt.ordersByCustomer(customerName);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/paymentPending")
    @CrossOrigin(origins ="http://localhost:4200" )
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getPaymentPendingOrder() {
        System.out.println(" getting payment pending orders >>>>>>>>>>>>>>>>>");
        return orderServiceExt.getPaymentPendingOrders();
    }
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/dispatchOrder/{id}")
    @CrossOrigin(origins ="http://localhost:4200" )
    @ResponseStatus(HttpStatus.OK)
    public String dispatchOrder(@PathVariable("id") String id, Orders orders) {
        System.out.println(" dispatching order >>>>>>>>>>>>>>>>>");
         orderServiceExt.dispatchStatus(id);
         return "Dispatched order for " + orders.getCustomerName();
    }
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/paymentReceived/{id}")
    @CrossOrigin(origins ="http://localhost:4200" )
    @ResponseStatus(HttpStatus.OK)
    public Orders paymentReceived(@PathVariable("id") String id) {
        System.out.println(" payment received for order >>>>>>>>>>>>>>>>>");
        return orderServiceExt.paymentReceived(id);
    }
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/declineOrder/{id}")
    @CrossOrigin(origins ="http://localhost:4200" )
    @ResponseStatus(HttpStatus.OK)
    public String declineOrder(@PathVariable("id") String id) {
        System.out.println(" Deleting order >>>>>>>>>>>>>>>>>");
         orderServiceExt.declineOrder(id);
         return "Declined order";
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/dispatched")
    @CrossOrigin(origins ="http://localhost:4200" )
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getDispatchedOrders(){
        System.out.println(" getting payment pending orders >>>>>>>>>>>>>>>>>");
        return orderServiceExt.getDispatchedOrders();

    }



}
