package jp.co.anymind.walletapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.anymind.walletapp.models.WalletDTO;
import jp.co.anymind.walletapp.models.WalletInputDateDTO;

@Service
public interface WalletService {
	
	public String saveAmount(WalletDTO wallet);

	public List<WalletDTO> getHistory(WalletInputDateDTO walletInputDate);
}	
	