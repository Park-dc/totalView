package rentalsvc;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="TotalView_table")
public class TotalView {

    @Id
    private Long orderId;
    private Long logistisId;
    private Long amount;
    private Long productId;
    private String lastEvent;
    
    private boolean rentalRequested;
    private boolean rentalCancellationOccured;
    private boolean FeeReceived;
    private boolean FeeRefundCompleted;
    private boolean InventoryStockShortage;
    private boolean DeliveryStarted;
    private boolean RetriveStarted;


    public boolean isRentalRequested() {
		return rentalRequested;
	}

	public void setRentalRequested(boolean rentalRequested) {
		this.rentalRequested = rentalRequested;
	}

	public boolean isRentalCancellationOccured() {
		return rentalCancellationOccured;
	}

	public void setRentalCancellationOccured(boolean rentalCancellationOccured) {
		this.rentalCancellationOccured = rentalCancellationOccured;
	}

	public boolean isFeeReceived() {
		return FeeReceived;
	}

	public void setFeeReceived(boolean feeReceived) {
		FeeReceived = feeReceived;
	}

	public boolean isFeeRefundCompleted() {
		return FeeRefundCompleted;
	}

	public void setFeeRefundCompleted(boolean feeRefundCompleted) {
		FeeRefundCompleted = feeRefundCompleted;
	}

	public boolean isInventoryStockShortage() {
		return InventoryStockShortage;
	}

	public void setInventoryStockShortage(boolean inventoryStockShortage) {
		InventoryStockShortage = inventoryStockShortage;
	}

	public boolean isDeliveryStarted() {
		return DeliveryStarted;
	}

	public void setDeliveryStarted(boolean deliveryStarted) {
		DeliveryStarted = deliveryStarted;
	}

	public boolean isRetriveStarted() {
		return RetriveStarted;
	}

	public void setRetriveStarted(boolean retriveStarted) {
		RetriveStarted = retriveStarted;
	}

	public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getLogistisId() {
        return logistisId;
    }

    public void setLogistisId(Long logistisId) {
        this.logistisId = logistisId;
    }
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

	public String getLastEvent() {
		return lastEvent;
	}

	public void setLastEvent(String lastEvent) {
		this.lastEvent = lastEvent;
	}


}
