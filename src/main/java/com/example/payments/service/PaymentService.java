package com.example.payments.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.payments.dao.ChannelEntity;
import com.example.payments.dao.ChannelRepository;
import com.example.payments.dao.ClientProfileEntity;
import com.example.payments.dao.ClientProfileRepository;
import com.example.payments.endpoint.Channel;
import com.example.payments.endpoint.Currency;
import com.example.payments.endpoint.PaymentData;
import com.example.payments.endpoint.PaymentProvider;
import com.example.payments.endpoint.PaymentRequest;
import com.example.payments.endpoint.PaymentResponse;
import com.example.payments.exception.AntifraudException;
import com.example.payments.exception.PaymentException;

@Service
public class PaymentService {

	private ChannelRepository channelRepository;
	private ClientProfileRepository clientProfileRepository;
	private KafkaProducerService eventService;

	public PaymentService(ChannelRepository channelRepository,
			ClientProfileRepository clientProfileRepository,
			KafkaProducerService notificationService) {
		this.channelRepository = channelRepository;
		this.eventService = notificationService;
		this.clientProfileRepository = clientProfileRepository;
	}

	public PaymentResponse pay(PaymentRequest paymentRequest) {
		PaymentProvider paymentProvider = paymentRequest.getProvider();
		Currency currency = paymentRequest.getCurrency();

		Channel channel = determineChannel(paymentProvider, currency);
		PaymentResponse response = null; 
		
		try {
			eventService.sendPaymentAttemptEvent(channel, paymentRequest, currency);
			
			antifraudCheck(channel, paymentRequest, currency);

			ClientProfileEntity sender = clientProfileRepository.findByUsername(paymentRequest.getSender().getUsername());
			BigDecimal fullAmount = calculateFee(paymentRequest.getAmount()).add(paymentRequest.getAmount());
			balancesCheck(sender, fullAmount);

			eventService.sendPayEvent(channel, paymentRequest, currency);

			eventService.sendCollectTaxEvent(channel, paymentRequest, currency);

			eventService.sendEmailEvent(channel, paymentRequest, currency);
			
			response = buildSuccessfullResponse();
		} catch (AntifraudException e) {
			
			eventService.sendAntifraudFailureEvent(channel, paymentRequest, currency);
			response = buildAntifraudErrorResponse(e);
		} catch (PaymentException e) {
			
			eventService.sendPaymentFailureEvent(channel, paymentRequest, currency);
			response = buildPaymentErrorResponse(e);
		}
		
		return response;
	}

	
	
	
	
	private BigDecimal calculateFee(BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	private void balancesCheck(ClientProfileEntity sender, BigDecimal amount) throws PaymentException {
		if (sender.getBalance().compareTo(amount) < 0) {
			throw new PaymentException();
		}
	}

	private PaymentResponse buildAntifraudErrorResponse(AntifraudException e) {
		// TODO Auto-generated method stub
		return null;
	}

	private PaymentResponse buildPaymentErrorResponse(PaymentException e) {
		// TODO Auto-generated method stub
		return null;
	}

	private void notifyCustomer() {
		// TODO Auto-generated method stub
		
	}

	private PaymentResponse buildSuccessfullResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	private void collectTax(Channel channel, PaymentRequest paymentRequest, Currency currency) {
		// TODO Auto-generated method stub
		
	}

	private void pay(Channel channel, PaymentRequest paymentRequest, Currency currency) throws PaymentException {
		// TODO Auto-generated method stub
		
	}

	private void antifraudCheck(Channel antifraudCheck, PaymentRequest paymentRequest, Currency currency) throws AntifraudException {
		// TODO Auto-generated method stub
		
	}

	private Channel determineChannel(PaymentProvider paymentProvider, Currency currency) {
		List<ChannelEntity> channels = channelRepository.findByPaymentProviderAndCurrency(paymentProvider, currency);
		return parseChannel(channels.get(0));		
	}

	private Channel parseChannel(ChannelEntity channelEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public PaymentData findById(Long paymentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
