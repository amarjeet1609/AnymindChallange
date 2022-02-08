package jp.co.anymind.walletapp.entities;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import jp.co.anymind.walletapp.models.WalletDTO;
import jp.co.anymind.walletapp.util.WalletUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wallet {
	
	public Wallet(WalletDTO walletDto) {
		this.datetime = WalletUtil.toTimeStamp(walletDto.getDatetime());
		this.amount = walletDto.getAmount();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Timestamp datetime;
	private Double amount;
	
	@Override
	public String toString() {
		return "Wallet [id=" + id + ", datetime=" + datetime + ", amount=" + amount + "]";
	}
	
	
}
