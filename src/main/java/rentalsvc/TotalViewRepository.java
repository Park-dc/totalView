package rentalsvc;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface TotalViewRepository extends CrudRepository<TotalView, Long>{
	
	List<TotalView> findByOrderId(Long orderId);
	
}