package jp.co.anymind.walletapp.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WalletDTO {
	
	@Setter
	@Getter
	private String datetime;
	
	@Setter
	@Getter
	private Double amount;

	@Override
	public String toString() {
		return "WalletDTO [datetime=" + datetime + ", amount=" + amount + "]";
	}
}
