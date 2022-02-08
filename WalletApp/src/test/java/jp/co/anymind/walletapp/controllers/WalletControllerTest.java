package jp.co.anymind.walletapp.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;

import jp.co.anymind.walletapp.models.WalletDTO;
import jp.co.anymind.walletapp.models.WalletInputDateDTO;
import jp.co.anymind.walletapp.services.WalletService;
import jp.co.anymind.walletapp.validations.ValidationError;

@SpringBootTest
public class WalletControllerTest {

	@InjectMocks
	private WalletController walletController;

	@Mock
	private WalletService walletService;
	
	@Mock
	private ValidationError validationService;
	
	@Test
	public void getHistoryTest() {
		WalletInputDateDTO walletInputDateDTO = new WalletInputDateDTO();
		walletInputDateDTO.setStartDatetime("2019-10-04T10:00:00+00:00");
		walletInputDateDTO.setEndDatetime("2019-10-04T18:00:00+00:00");
		
		when(walletService.getHistory(walletInputDateDTO)).thenReturn(new ArrayList<WalletDTO>());
		walletController.getHistory(walletInputDateDTO, 
				new BeanPropertyBindingResult(walletInputDateDTO, "walletHistoryValidation"));
		verify(walletService, times(1)).getHistory(walletInputDateDTO);
	}
	
	@Test
	public void saveAmountTest() {
		WalletDTO walletDTO = new WalletDTO();
		walletDTO.setDatetime("2019-10-04T10:00:00+00:00");
		walletDTO.setAmount(1000.0);	
		
		when(validationService.validate(new BeanPropertyBindingResult(walletDTO, "walletValidation")))
		.thenReturn(null);
		when(walletService.saveAmount(walletDTO)).thenReturn("Amount saved successfully");
		ResponseEntity<?> response = walletController.saveAmount(walletDTO, 
				new BeanPropertyBindingResult(walletDTO, "walletValidation"));
		verify(walletService, times(1)).saveAmount(walletDTO);
		assertEquals("Amount saved successfully", response.getBody());
	}
}
