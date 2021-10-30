package com.thondo.whatsappconnect.service;

import com.thondo.whatsappconnect.entity.Item;
import com.thondo.whatsappconnect.entity.MainProfile;
import com.thondo.whatsappconnect.entity.OrderItem;
import com.thondo.whatsappconnect.entity.Orders;
import com.thondo.whatsappconnect.enums.MenuEnum;
import com.thondo.whatsappconnect.repository.ItemRepository;
import com.thondo.whatsappconnect.repository.MainProfileRepository;
import com.thondo.whatsappconnect.repository.OrderItemRepository;
import com.thondo.whatsappconnect.repository.OrdersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
        @Transactional
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private MainProfileRepository mainProfileRepository;
    @Autowired
    private ItemRepository itemsRepository;
    @Autowired
    private OrderItemRepository orderItemsRepository;
    @Autowired
    MessageProcessorService messageProcessorService;


    public String orderPlacement(Orders order, MainProfile mainProfile, String body, String imageUrl) {


        if (order.getMenuStage() == 1 && order.getCurrentItem() == 0) {



            // Solar
            if (body.equalsIgnoreCase("1")) {
                Item item = new Item();
                order.setMenuStage(2);
                item.setType("Home Systems");
                order.setCurrentItem(1);
                itemsRepository.save(item);
                ordersRepository.save(order);
                mainProfile.setMenuStage(2);
                return "Solar Home Systems\n" +
                        "\t•[1]\tPICO\n" +
                        "\t•[2]\tPICO plus\n" +
                        "\t•[3]\tPro 200\n" +
                        "\t•[4]\tPro 300\n" +
                        "\t•[5]\tPro 400\n" +
                        "\t•[6]\tBoom\n" +
                        "\t•[7]\tHome 40z\n" +
                        "\t•[8]\tHome 60\n" +
                        "\t•[9]\tHome 120\n" +
                        "\t•[10]\tHome 400\n" +
                        "\t•[11]\tLights\n";

            } else if (body.equalsIgnoreCase("2")) {
                Item item = new Item();
                item.setType("Pumps");
                order.setMenuStage(2);
                order.setCurrentItem(2);
                itemsRepository.save(item);
                ordersRepository.save(order);
                return "Solar Pumps\n" +
                        "\t•[1]\tLorenz PS2-100\n" +
                        "\t•[2]\tFuture Pumps SF1\n" +
                        "\t•[3]\tFuture Pumps SF2";
            } else if (order.getCurrentItem() == 0) {
                return "[1]Solar Home Systems\n" +
                        "[2] Solar Pumps\n";
            }
        }


        if (order.getCurrentItem() == 1 && body.equalsIgnoreCase("1")) {
           // order.setOrderItems(new ArrayList<>());
         // Optional<OrderItem> orderItems = orderItemsRepository.findByName("PICO");
            OrderItem orderItem = new OrderItem();
            Item item = new Item();
            System.out.println("orderitem id : " + orderItem.getId());
            List<OrderItem> orderItems = new ArrayList<>();
            System.out.println("orderitem id : " + orderItem.getId());
            orderItem.setName("PICO");
           // orderItems.add(orderItem);
            item.setName("PICO");
            orderItem.setItem(item);
            orderItem.setStatus("Pending Quantity");
            System.out.println("orderitem id : " + orderItem.getId());
           // order.setOrderItems(orderItem);
            order.getOrderItems().add(orderItem);
            System.out.println("orderitem id : " + orderItem.getId());
            order.setOrderName(orderItem.getName());

            order.setCurrentItem(5);
            orderItem.setOrders(order);
            order.setMenuStage(3);
            itemsRepository.save(item);
            orderItemsRepository.save(orderItem);
            ordersRepository.save(order);
            return "Quantity";

        } else if (order.getCurrentItem() == 1 && body.equalsIgnoreCase("2")) {
            order.setOrderItems(new ArrayList<>());
            OrderItem orderItem = new OrderItem();
            Item item = new Item();
            orderItem.setName("PICO plus");
            item.setName("PICO plus");
            order.setOrderName(orderItem.getName());
            order.setMenuStage(3);
            orderItem.setOrders(order);
            order.setCurrentItem(5);
            orderItem.setItem(item);
            orderItem.setStatus("Pending Quantity");
            order.getOrderItems().add(orderItem);
            itemsRepository.save(item);
            orderItemsRepository.save(orderItem);
            ordersRepository.save(order);
            return "Quantity";
        } else if (order.getCurrentItem() == 1 && body.equalsIgnoreCase("3")) {
            order.setOrderItems(new ArrayList<>());
            OrderItem orderItem = new OrderItem();
            Item item = new Item();
            orderItem.setName("Pro 200");
            orderItem.setStatus("Pending Quantity");
            item.setName("Pro 200");
            order.setOrderName(orderItem.getName());
            order.setMenuStage(3);
            order.setCurrentItem(5);
            orderItem.setItem(item);
            orderItem.setOrders(order);
            itemsRepository.save(item);
            orderItemsRepository.save(orderItem);
            ordersRepository.save(order);
            return "Quantity";
        } else if (order.getCurrentItem() == 1 && body.equalsIgnoreCase("4")) {
            order.setOrderItems(new ArrayList<>());
            OrderItem orderItem = new OrderItem();
            Item item = new Item();
            orderItem.setName("Pro 300");
            item.setName("Pro 300");
            order.setOrderName(orderItem.getName());
            order.setMenuStage(3);
            orderItem.setItem(item);
            order.setCurrentItem(5);
            orderItem.setOrders(order);
            orderItem.setStatus("Pending Quantity");
            itemsRepository.save(item);
            orderItemsRepository.save(orderItem);
            ordersRepository.save(order);
            return "Quantity";
        } else if (order.getCurrentItem() == 1 && body.equalsIgnoreCase("5")) {
            order.setOrderItems(new ArrayList<>());
            OrderItem orderItem = new OrderItem();
            Item item = new Item();
            orderItem.setName("Pro 400");
            item.setName("Pro 400");
            order.getOrderItems().add(orderItem);
            order.setOrderName(orderItem.getName());
            orderItem.setOrders(order);
            order.setMenuStage(3);
            order.setCurrentItem(5);
            orderItem.setItem(item);
            orderItem.setStatus("Pending Quantity");
            itemsRepository.save(item);
            orderItemsRepository.save(orderItem);
            ordersRepository.save(order);
            return "Quantity";
        } else if (order.getCurrentItem() == 1 && body.equalsIgnoreCase("6")) {
            order.setOrderItems(new ArrayList<>());
            OrderItem orderItem = new OrderItem();
            Item item = new Item();
            orderItem.setName("Boom");
            orderItem.setStatus("Pending Quantity");
            order.setOrderName(orderItem.getName());
            order.setMenuStage(3);
            order.setCurrentItem(5);
            orderItem.setOrders(order);
            orderItem.setItem(item);
            orderItemsRepository.save(orderItem);
            ordersRepository.save(order);
            return "Quantity";
        } else if (order.getCurrentItem() == 1 && body.equalsIgnoreCase("7")) {
            order.setOrderItems(new ArrayList<>());
            OrderItem orderItem = new OrderItem();
            Item item = new Item();
            orderItem.setName("Home 40z");
            item.setName("Home 40z");
            orderItem.setStatus("Pending Quantity");
            order.setOrderName(orderItem.getName());
            order.setMenuStage(3);
            order.setCurrentItem(5);
            orderItem.setOrders(order);
            orderItem.setItem(item);
            itemsRepository.save(item);

            orderItemsRepository.save(orderItem);
            ordersRepository.save(order);
            return "Quantity";
        } else if (order.getCurrentItem() == 1 && body.equalsIgnoreCase("8")) {
            order.setOrderItems(new ArrayList<>());
            OrderItem orderItem = new OrderItem();
            Item item = new Item();
            orderItem.setName("Home 60");
            orderItem.setStatus("Pending Quantity");
            item.setName("Home 60");
            order.setOrderName(orderItem.getName());
            order.setMenuStage(3);
            order.setCurrentItem(5);
            orderItem.setItem(item);
            itemsRepository.save(item);
            orderItemsRepository.save(orderItem);
            ordersRepository.save(order);
            return "Quantity";
        } else if (order.getCurrentItem() == 1 && body.equalsIgnoreCase("9")) {
            order.setOrderItems(new ArrayList<>());
            OrderItem orderItem = new OrderItem();
            Item item = new Item();
            orderItem.setName("Home 120");
            orderItem.setStatus("Pending Quantity");
            item.setName("Home 120");
         //   order.setOrderItems(ArrayList<OrderItem>);
            order.setOrderName(orderItem.getName());
            order.setMenuStage(3);
            order.setCurrentItem(5);
            orderItem.setOrders(order);
            orderItem.setItem(item);
            itemsRepository.save(item);
            orderItemsRepository.save(orderItem);
            ordersRepository.save(order);
            return "Quantity";
        } else if (order.getCurrentItem() == 1 && body.equalsIgnoreCase("10")) {
            order.setOrderItems(new ArrayList<>());
            OrderItem orderItem = new OrderItem();
            Item item = new Item();
            orderItem.setName("Home 400");
            orderItem.setStatus("Pending Quantity");
            item.setName("Home 400");
            order.setOrderName(orderItem.getName());
            order.setMenuStage(3);
            order.setCurrentItem(5);
            orderItem.setOrders(order);
            orderItem.setItem(item);
            orderItem.setStatus("Pending");
            itemsRepository.save(item);
            orderItemsRepository.save(orderItem);
            ordersRepository.save(order);
            return "Quantity";
        } else if (order.getCurrentItem() == 1 && body.equalsIgnoreCase("11")) {
            order.setOrderItems(new ArrayList<>());
            OrderItem orderItem = new OrderItem();
            Item item = new Item();
            orderItem.setName("Lights");
            orderItem.setStatus("Pending Quantity");
            item.setName("Lights");
            order.setOrderName(orderItem.getName());
            order.setMenuStage(3);
            order.setCurrentItem(5);
            orderItem.setOrders(order);
            orderItem.setItem(item);
            itemsRepository.save(item);
            orderItem.setStatus("Pending");
            orderItemsRepository.save(orderItem);
            ordersRepository.save(order);
            return "Quantity";
        }

        /////////////FOR SOLAR PUMPS

        else if (order.getCurrentItem() == 2 && body.equalsIgnoreCase("1")) {
            order.setOrderItems(new ArrayList<>());
            OrderItem orderItem = new OrderItem();
            Item item = new Item();
            orderItem.setName("Lorenz PS2-100");
            orderItem.setStatus("Pending Quantity");
            item.setName("Lorenz PS2-100");
            item.setStatus(item.getStatus());
            item.setType(item.getType());
            item.setId(item.getId());
            order.setOrderName(orderItem.getName());
            order.setCurrentItem(5);
            order.setMenuStage(3);
            orderItem.setOrders(order);
            orderItem.setItem(item);
            orderItem.setStatus("Pending");
            orderItemsRepository.save(orderItem);
            ordersRepository.save(order);
            return "Quantity";
        } else if (order.getCurrentItem() == 2 && body.equalsIgnoreCase("2")) {
            order.setOrderItems(new ArrayList<>());
            OrderItem orderItem = new OrderItem();
            Item item = new Item();
            item.setStatus(item.getStatus());
            item.setType(item.getType());
            item.setId(item.getId());

            orderItem.setStatus("Pending Quantity");
            item.setName("Future Pumps SF1");
            orderItem.setName("Future Pumps SF1");
           // order.setOrderName(orderItem.getName());
            order.setCurrentItem(5);
            order.setMenuStage(3);
            orderItem.setOrders(order);
            orderItem.setItem(item);
            orderItemsRepository.save(orderItem);
            ordersRepository.save(order);
            return "Quantity";
        } else if (order.getCurrentItem() == 2 && body.equalsIgnoreCase("3")) {
            order.setOrderItems(new ArrayList<>());
            OrderItem orderItem = new OrderItem();
            Item item = new Item();
            item.setName("Future Pumps SF2");
            orderItem.setName("Future Pumps SF2");
           // order.setOrderName(orderItem.getName());
            order.setCurrentItem(5);
            order.setMenuStage(3);
            orderItem.setOrders(order);
            orderItem.setItem(item);
            orderItem.setStatus("Pending Quantity");
            orderItemsRepository.save(orderItem);
            ordersRepository.save(order);
            return "Quantity";
        }

      // Optional<OrderItem> orderItem1 = orderItemsRepository.findByName(order.getOrderName().);
      //  Optional<OrderItem>orderItem2 = orderItemsRepository.findByName(order.getOrderName());


            if (order.getMenuStage() == 3) {
              Optional< OrderItem> orderItem = orderItemsRepository.findByStatus("Pending Quantity");

                int qty = Integer.parseInt(body);
                List<OrderItem> orderItems = new ArrayList<>();
                order.setOrderItems(new ArrayList<>());

                orderItem.get().setName(orderItem.get().getName());
                orderItem.get().setQuantity(qty);
                orderItem.get().setStatus("Completed");
               // orderItem1.get().setQuantity(qty);
                order.setQuantity(body);
                // orderItem.setQuantity(qty);
                order.setMenuStage(4);
                order.setCurrentItem(0);
                mainProfile.setMenuStage(0);
                //  orderItem.setOrders(order);
               // orderItems.add(orderItem1.get());
                orderItems.add(orderItem.get());
                ordersRepository.save(order);
               // orderItemsRepository.save(orderItem1.get());
                 orderItemsRepository.save(orderItem.get());
                return "1. Add Another Product \n 2. Continue";
            }



        else if (body.equalsIgnoreCase("1") && order.getMenuStage() == 4) {
            order.setCurrentItem(0);
            order.setMenuStage(1);
            order.setStatus("Pending");
            ordersRepository.save(order);
            //order main menu
            return "[1]Solar Home Systems\n" +
                    "[2] Solar Pumps\n";
        } else if (body.equalsIgnoreCase("2") && order.getMenuStage() == 4) {
            order.setMenuStage(6);
            ordersRepository.save(order);
            return "Name of Customer";
        } else if (order.getMenuStage() == 6) {
            order.setMenuStage(7);
            order.setCustomerName(body);
            ordersRepository.save(order);
            return "Customer Phone number";
        } else if (order.getMenuStage() == 7) {
            order.setMenuStage(8);
            order.setCustomerNumber(body);
            ordersRepository.save(order);
            return "Customer Address";

        } else if (order.getMenuStage() == 8) {
            order.setMenuStage(9);
            order.setCustomerAddress(body);
            ordersRepository.save(order);
            return "Payment terms \n \t [1] Cash \n \t [2] Credit";

        } else if (order.getMenuStage() == 9 && body.equalsIgnoreCase("1")) {
            order.setMenuStage(13);
            order.setPaymentTerms("Cash");
            ordersRepository.save(order);
            return "Customer ID Number";
        } else if (order.getMenuStage() == 9 && body.equalsIgnoreCase("2")) {
            order.setMenuStage(11);
            order.setPaymentTerms("Credit");
            ordersRepository.save(order);
            return "Deposit Amount";
        } else if (order.getMenuStage() == 11) {
            BigDecimal amount = new BigDecimal(body);
            order.setMenuStage(12);
            order.setAmountDeposited(amount);
            ordersRepository.save(order);
            return "Repayment Period";
        } else if (order.getMenuStage() == 12) {
            order.setMenuStage(13);
            order.setRepaymentPeriod(body);
            ordersRepository.save(order);
            return "Customer ID Number";
        } else if (order.getMenuStage() == 13) {
            order.setMenuStage(14);
            order.setCustomerIdNumber(body);
            ordersRepository.save(order);
            return "Customer ID document";
        } else if (order.getMenuStage() == 14) {
            if (imageUrl == null) {
                return "Please upload a valid Customer ID document";
            }
            order.setMenuStage(15);
            order.setIdImageUrl(body);
            ordersRepository.save(order);
            return "Customer ID picture";
        } else if (order.getMenuStage() == 15) {
            if (imageUrl == null) {
                return "Please upload a valid Customer ID picture";
            }
            order.setCustomerImageUrl(imageUrl);
            order.setMenuStage(16);
            ordersRepository.save(order);
            return "Source of Income";
        }
        else if (order.getMenuStage() == 16) {
            order.setSourceOfIncome(body);
            order.setMenuStage(17);
            ordersRepository.save(order);
            //Optional<OrderItem> allOrderItems = orderItemsRepository.findByStatus("Completed");
                List<OrderItem> orderItems = orderItemsRepository.findAll();


            String response = " ";

                List<OrderItem> allOrderItems = orderItems.stream()
                        .filter(allItems -> allItems.getStatus().equalsIgnoreCase("Completed"))
                        .collect(Collectors.toList());


            for (OrderItem orderItemSummary : allOrderItems) {
                response = response + orderItemSummary.getName() + " x "
                        + orderItemSummary.getQuantity() +"\n \t";
                orderItemSummary.setStatus("Finalized");
            }


            return "Order Summary \n" +
                    "\t Kindly confirm your order below : \n"+ "\t" +
                    response + "\n  1.Confirm \n 2.Redo Order"  ;
        }
        else if (order.getMenuStage() == 17 && body.equalsIgnoreCase("1") ){


            order.setMenuSelected(MenuEnum.ORDER.getValue());
            order.setStatus("Submitted");
            order.setMenuStage(0);
            mainProfile.setMenuStage(0);
            mainProfileRepository.save(mainProfile);
            ordersRepository.save(order);

            return "Your order has been processed.You will be notified when order is ready. \n" +
                    "\n Enter 1 to start a new Order";
        }
        else if (order.getMenuStage() == 17 && body.equalsIgnoreCase("2")){
            orderItemsRepository.deleteAll();
            ordersRepository.deleteAll();
            order.setMenuStage(1);
            order.setStatus("Pending");
            order.setCurrentItem(0);
            ordersRepository.save(order);
            return "[1]Solar Home Systems\n"+
                    "[2] Solar Pumps\n";
        }

        return "Please contact our administrator for support";


    }


}

