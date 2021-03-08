package br.com.weecode.weesign_backend.integration.paypal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import br.com.weecode.weesign_backend.utils.Utils;

@RestController
@RequestMapping("payment")
public class PaymentController {

	public static final String PAYPAL_SUCCESS_URL = "pay/success";
	public static final String PAYPAL_CANCEL_URL = "pay/cancel";
	
	@Autowired
	private PaypalServiceImpl paypalService;
	
	@PostMapping("/pay")
	public String pay(HttpServletRequest request) {
		String cancelUrl = Utils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
		String successUrl = Utils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL;
		try {
			Payment payment = paypalService.createPayment(
					1.00, 
					"USD", 
					PaypalPaymentMethod.paypal, 
					PaypalPaymentIntent.sale,
					"payment description", 
					cancelUrl, 
					successUrl);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@GetMapping(PAYPAL_SUCCESS_URL)
	public String cancelPay() {
		return "cancel";
	}
	
	@GetMapping(PAYPAL_CANCEL_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				return "success";
			}
		} catch (PayPalRESTException e) {
			return e.getMessage();
		}
		
		return "";
	}
	
}
