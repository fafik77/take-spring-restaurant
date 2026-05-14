package com.example.demo.services;

import com.example.demo.dto.RegularCustomerDto;
import com.example.demo.dto.requests.AddCustomerRequest;
import com.example.demo.dto.requests.UpdateCustomerRequest;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
	private final CustomerRepository customerRepository;
	private final EntityManager entityManager;

	public Customer add(AddCustomerRequest request) {
		var customer = request.mapToEntity();
		return customerRepository.save(customer);
	}

	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Optional<Customer> findById(Long id) {
		return customerRepository.findById(id);
	}

	public Iterable<Order> getCustomerOrders(Long id) {
		var customer = customerRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(Customer.class, id));
		return customer.getOrders();
	}

	public void deleteById(Long id) {
		if (!customerRepository.existsById(id))
			throw new ItemNotFoundException(Customer.class, id);
		customerRepository.deleteById(id);
	}

	public void update(UpdateCustomerRequest request) {
		var customer = findById(request.getId()).orElseThrow(() -> new ItemNotFoundException(Customer.class, request.getId()));
		request.populateFields(customer);
		customerRepository.save(customer);
	}

	public List<RegularCustomerDto> getRegulars() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RegularCustomerDto> query = cb.createQuery(RegularCustomerDto.class);
		Root<Customer> root = query.from(Customer.class);

		Join<Customer, Order> ordersJoin = root.join(Customer.orders_, JoinType.LEFT);
		var orderCount = cb.count(ordersJoin);  // order count

		query.select(cb.construct(
			RegularCustomerDto.class,
			root.get(Customer.id_),
			root.get(Customer.joinDate_),
			root.get(Customer.deliveryAddress_),
			root.get(Customer.phoneNumber_),
			orderCount
		));

		query.groupBy(
			root.get(Customer.id_),
			root.get(Customer.joinDate_),
			root.get(Customer.deliveryAddress_),
			root.get(Customer.phoneNumber_)
		);

		query.having(cb.gt(orderCount, 0)); // order count > 0

		query.orderBy(cb.desc(orderCount));

		return entityManager.createQuery(query).getResultList();
	}
}
