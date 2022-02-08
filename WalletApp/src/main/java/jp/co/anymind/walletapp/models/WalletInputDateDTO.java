package jp.co.anymind.walletapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletInputDateDTO {
	
	@Setter
	@Getter
	private String startDatetime;
	
	@Setter
	@Getter
	private String endDatetime;

	@Override
	public String toString() {
		return "WalletInputDateDTO [startDatetime=" + startDatetime + ", endDatetime=" + endDatetime + "]";
	}
}
