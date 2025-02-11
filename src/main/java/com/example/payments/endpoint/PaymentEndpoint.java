package com.example.payments.endpoint;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payments.service.PaymentProviderService;
import com.example.payments.service.PaymentService;

@RestController
@RequestMapping("payments")
public class PaymentEndpoint {

	private PaymentProviderService providerService;
	private PaymentService paymentService;

	public PaymentEndpoint(PaymentProviderService service,
			PaymentService paymentService) {
		this.providerService = service;
		this.paymentService = paymentService;
	}

	@GetMapping("/providers")
	public ResponseEntity<List<PaymentProvider>> listProviders(@RequestBody ProviderRequest providerRequest) {
		return ResponseEntity.ok(providerService.determineProviders(providerRequest));
	}
	
	@PostMapping("/pay")
	public ResponseEntity<PaymentResponse> pay(@RequestBody PaymentRequest paymentRequest) {
		return ResponseEntity.ok(paymentService.pay(paymentRequest));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentData> getPayment(@PathVariable("id") Long paymentId) {
		return ResponseEntity.ok(paymentService.findById(paymentId));
	}

}
