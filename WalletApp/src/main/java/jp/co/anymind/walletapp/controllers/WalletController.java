package jp.co.anymind.walletapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.anymind.walletapp.models.WalletDTO;
import jp.co.anymind.walletapp.models.WalletInputDateDTO;
import jp.co.anymind.walletapp.services.WalletService;
import jp.co.anymind.walletapp.validations.ValidationError;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

	@Autowired
	private WalletService walletService;
	
	@Autowired
	private ValidationError validationService;
	
	@GetMapping("/history")
	public ResponseEntity<?> getHistory(@Valid @RequestBody WalletInputDateDTO walletInputDate, BindingResult result){
		
		List<WalletDTO> walletHistory = walletService.getHistory(walletInputDate);
		return new ResponseEntity<List<WalletDTO>>(walletHistory, HttpStatus.OK);
	}
	
	@PostMapping("/amount")
	public ResponseEntity<?> saveAmount(@Valid @RequestBody WalletDTO wallet, BindingResult result){
		ResponseEntity<?> errors = validationService.validate(result);
		if(errors != null) 
			return errors;
		String walletSaved = walletService.saveAmount(wallet);
		return new ResponseEntity<String>(walletSaved, HttpStatus.CREATED);
	}
}
