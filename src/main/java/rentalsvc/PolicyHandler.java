package rentalsvc;

import rentalsvc.config.kafka.KafkaProcessor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.common.metrics.stats.Total;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    
	@Autowired
	TotalViewRepository totalViewRepository;
	
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRentalRequested_WriteRentalRequest(@Payload RentalRequested rentalRequested){

        if(rentalRequested.isMe()){
            System.out.println("##### listener WriteRentalRequest 랜탈요청 정보 CQRT 저장 : " + rentalRequested.toJson());            
            TotalView totalView = new TotalView(); 
            totalView.setOrderId(rentalRequested.getOrderId());
            totalView.setProductId(rentalRequested.getProductId());
            totalView.setAmount(rentalRequested.getAmount());
            totalView.setRentalRequested(true);
            totalView.setLastEvent("RentalRequested");
            totalViewRepository.save(totalView);
            
        }
    }
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverFeeReceived_WriteFeeReceived(@Payload FeeReceived feeReceived){

        if(feeReceived.isMe()){
            System.out.println("##### listener WriteFeeReceived 요금 결재 정보 CQRS 저장 : " + feeReceived.toJson());
            TotalView totalView = totalViewRepository.findByOrderId(feeReceived.getOrderId()).get(0);
            totalView.setOrderId(feeReceived.getOrderId());
            totalView.setAmount(feeReceived.getAmount());
            totalView.setFeeReceived(true);
            totalView.setLastEvent("FeeReceived");
            totalViewRepository.save(totalView);
        }
    }
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverInventoryStockShortage_WriteInventoryStockShortage(@Payload InventoryStockShortage inventoryStockShortage){

        if(inventoryStockShortage.isMe()){
            System.out.println("##### listener WriteInventoryStockShortage 재고 부족으로 랜터 요청 취소 정보 QCRS 저장 : " + inventoryStockShortage.toJson());
            TotalView totalView = totalViewRepository.findByOrderId(inventoryStockShortage.getOrderId()).get(0);
            totalView.setOrderId(inventoryStockShortage.getOrderId());
            totalView.setProductId(inventoryStockShortage.getProductId());
            totalView.setInventoryStockShortage(true);
            totalView.setLastEvent("InventoryStockShortage");
            totalViewRepository.save(totalView);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryStarted_WriteDeliveryStarted(@Payload DeliveryStarted deliveryStarted){

        if(deliveryStarted.isMe()){
            System.out.println("##### listener WriteDeliveryStarted 배송 시작 정보 QCRS 저장 : " + deliveryStarted.toJson());
            TotalView totalView = totalViewRepository.findByOrderId(deliveryStarted.getOrderId()).get(0);
            totalView.setOrderId(deliveryStarted.getOrderId());
            totalView.setLogistisId(deliveryStarted.getLogistisId());
            totalView.setDeliveryStarted(true);
            totalView.setLastEvent("DeliveryStarted");
            totalViewRepository.save(totalView);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryStarted_RetriveStarted(@Payload RetriveStarted retriveStarted){

        if(retriveStarted.isMe()){
            System.out.println("##### listener WriteRetriveStarted Rental 제품 회수 정보 QCRS 저장 : " + retriveStarted.toJson());
            TotalView totalView = totalViewRepository.findByOrderId(retriveStarted.getOrderId()).get(0);
            totalView.setOrderId(retriveStarted.getOrderId());
            totalView.setProductId(retriveStarted.getProductId() );
            totalView.setRetriveStarted(true);
            totalView.setLastEvent("RetriveStarted");
            totalViewRepository.save(totalView);
        }
    }
}
