package com.thondo.whatsappconnect.service;

import com.thondo.whatsappconnect.entity.Item;
import com.thondo.whatsappconnect.entity.MainProfile;
import com.thondo.whatsappconnect.entity.OrderItem;
import com.thondo.whatsappconnect.entity.Orders;
import com.thondo.whatsappconnect.enums.ApplicationStatus;
import com.thondo.whatsappconnect.enums.MenuEnum;
import com.thondo.whatsappconnect.repository.ItemRepository;
import com.thondo.whatsappconnect.repository.MainProfileRepository;
import com.thondo.whatsappconnect.repository.OrderItemRepository;
import com.thondo.whatsappconnect.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageProcessorService {
    @Autowired
    private MainProfileRepository mainProfileRepository;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;


    public String processMessage(MultiValueMap<String, String> paramMap) {
        String formattedBody = null;

        //always check we need the whatsapp ID here
        if (paramMap != null && paramMap.get("WaId") != null) {
            String mobileNumber = paramMap.get("WaId").get(0);
            String formattedMobileNumber = mobileNumber.replace('[', ' ').replace(']', ' ').trim();
            if (paramMap != null && paramMap.get("Body") != null) {
                String body = paramMap.get("Body").get(0);
                formattedBody = body.replace('[', ' ').replace(']', ' ').trim();
            }
            //we have both the whatsapp ID and the BODY- lets configure how to process

            Optional<MainProfile> optionalMainProfile = mainProfileRepository.findByMobileNumber(formattedMobileNumber);

            //we have this profile set up before , let decide processing based on menu selected.
            if (optionalMainProfile.isPresent()) {
                //go to registration service
                if (optionalMainProfile.get().getMenuSelected().equalsIgnoreCase(MenuEnum.REGISTRATION.getValue())) {
                    String imageUrl =null;
                    if (paramMap.get("MediaUrl0")!=null){
                        imageUrl=paramMap.get("MediaUrl0").get(0);
                    }
                    return registrationService.register(optionalMainProfile.get(), formattedBody,imageUrl);
                } //go to registration service
                else if (optionalMainProfile.get().getMenuSelected().equalsIgnoreCase(MenuEnum.ORDER.getValue())) {
                   // Item item = new Item();
                    String imageUrl =null;
                    if (paramMap.get("MediaUrl0")!=null){
                        imageUrl=paramMap.get("MediaUrl0").get(0);
                    }
                    Optional<Orders> pendingOrder = ordersRepository.findByRefMobileNumberAndStatus(optionalMainProfile.get().getMobileNumber(), "Pending");
                 //  Optional<Item> pendingItem = itemRepository.findByRefAndAndStatus(optionalMainProfile.get().getRefMobileNumber(),"Pending");
                  //  Item item = itemRepository.findByStatus("Pending");

                    if (!pendingOrder.isPresent()){
                       if (optionalMainProfile.get().getMenuStage()==0){
                           Orders order = new Orders();
                           Item item = new Item();
                           item.setStatus("Pending");
                           order.setRefMobileNumber(optionalMainProfile.get().getMobileNumber());
                           order.setMenuStage(1);
                           order.setMenuSelected(MenuEnum.ORDER.getValue());
                           order.setStatus("Pending");
                           order.setOrderStatus("Pending");
                           optionalMainProfile.get().setMenuSelected(MenuEnum.ORDER.getValue());
                           optionalMainProfile.get().setMenuStage(1);
                           mainProfileRepository.save(optionalMainProfile.get());
                           ordersRepository.save(order);
                           itemRepository.save(item);
                           //order main menu
                           return "[1] Solar Home Systems\n"+
                           "[2] Solar Pumps\n";
                       }else{
                        //  return orderService.ordersFinalisation(optionalMainProfile.get(), formattedBody,imageUrl);
                       }


                    }

                    //  Optional<MainProfile>mainProfile1 = mainProfileRepository.findByMobileNumber()

                    else{
                       // OrderItem orderItem = new OrderItem();
                       // Item item = new Item();

                            return orderService.orderPlacement(pendingOrder.get(),optionalMainProfile.get(),
                                    formattedBody,imageUrl);

                    }


                }
            }
            //no main profile so let give initial menu
            else {
                //main menu
                MainProfile mainProfile = new MainProfile();
                mainProfile.setApprovalStatus(ApplicationStatus.PENDING.getValue());
                mainProfile.setMobileNumber(formattedMobileNumber);
                mainProfile.setDateCreated(LocalDateTime.now());
                mainProfile.setMenuStage(0);
                mainProfile.setMenuSelected(MenuEnum.REGISTRATION.getValue());
                mainProfileRepository.save(mainProfile);
                return "Hi, welcome to ImpiPower!\n select 1 to register ";
            }
        }
        //if no whatsapp ID for now we returning " "
        return " xxx";
    }


}

