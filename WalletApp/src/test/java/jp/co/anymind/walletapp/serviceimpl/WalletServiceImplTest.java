package jp.co.anymind.walletapp.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.anymind.walletapp.entities.Wallet;
import jp.co.anymind.walletapp.models.WalletDTO;
import jp.co.anymind.walletapp.models.WalletInputDateDTO;
import jp.co.anymind.walletapp.repositories.WalletRepository;
import jp.co.anymind.walletapp.util.WalletUtil;

@SpringBootTest
public class WalletServiceImplTest {
	
	@InjectMocks
	private WalletServiceImpl walletServiceImpl;
	
	@Mock
	private WalletRepository walletRepository;
	
	
	@Test
	public void saveAmountTest() {
		WalletDTO walletDTO = new WalletDTO();
		walletDTO.setDatetime("2019-10-04T10:00:00+00:00");
		walletDTO.setAmount(1000.0);	
		
		Wallet wallet = new Wallet(walletDTO);
		
		when(walletRepository.save(wallet)).thenReturn(wallet);
		String response = walletServiceImpl.saveAmount(walletDTO);
		verify(walletRepository, times(1)).save(wallet);
		assertEquals("Amount saved successfully", response);
	}
	
	@Test
	public void getHistoryTest() {
		WalletInputDateDTO walletInputDateDTO = new WalletInputDateDTO();
		walletInputDateDTO.setStartDatetime("2019-10-04T10:00:00+00:00");
		walletInputDateDTO.setEndDatetime("2019-10-04T18:00:00+00:00");
		WalletDTO walletDTO = new WalletDTO();
		walletDTO.setDatetime("2019-10-04T10:00:00+00:00");
		walletDTO.setAmount(1000.0);	
		
		Wallet wallet = new Wallet(walletDTO);
		
		when(walletRepository.getWallet(WalletUtil.toTimeStamp(
				walletInputDateDTO.getStartDatetime()))).thenReturn(wallet);
		when(walletRepository.getTotalAmount(Mockito.any(Timestamp.class), 
				Mockito.any(Timestamp.class))).thenReturn(1000.0);
		List<WalletDTO> response = walletServiceImpl.getHistory(walletInputDateDTO);
		assertNotNull(response);
		verify(walletRepository, times(1)).getWallet(WalletUtil.toTimeStamp(
				walletInputDateDTO.getStartDatetime()));
		verify(walletRepository, atLeastOnce()).getTotalAmount(Mockito.any(Timestamp.class), 
				Mockito.any(Timestamp.class));
	}
	
}
