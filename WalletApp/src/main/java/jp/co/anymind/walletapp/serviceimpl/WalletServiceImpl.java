package jp.co.anymind.walletapp.serviceimpl;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.anymind.walletapp.entities.Wallet;
import jp.co.anymind.walletapp.models.WalletDTO;
import jp.co.anymind.walletapp.models.WalletInputDateDTO;
import jp.co.anymind.walletapp.repositories.WalletRepository;
import jp.co.anymind.walletapp.services.WalletService;
import jp.co.anymind.walletapp.util.WalletUtil;

@Service
public class WalletServiceImpl implements WalletService{

	@Autowired
	private WalletRepository walletRepository;

	@Override
	@Transactional
	public String saveAmount(WalletDTO walletDto) {
		Wallet wallet = new Wallet(walletDto);
		Wallet saved = walletRepository.save(wallet);
		if (null != saved) 
			return "Amount saved successfully";
		return "Failed to save amount";
	}

	@Override
	@Transactional
	public List<WalletDTO> getHistory(WalletInputDateDTO walletInputDate) {

		List<WalletDTO> walletList = new ArrayList<>();


		OffsetDateTime startDateTime = OffsetDateTime.parse(walletInputDate.getStartDatetime());
		OffsetDateTime endDatetime = OffsetDateTime.parse(walletInputDate.getEndDatetime());

		Long totalHours = ChronoUnit.HOURS.between(startDateTime, endDatetime);
		Wallet wallet = walletRepository.getWallet(WalletUtil.toTimeStamp(walletInputDate.getStartDatetime()));

		if(null != wallet) {
			WalletDTO walletdto = new WalletDTO();
			walletdto.setAmount(wallet.getAmount());

			walletdto.setDatetime(walletInputDate.getStartDatetime());
			walletList.add(walletdto);
		}else {
			WalletDTO walletdto = new WalletDTO();
			walletdto.setAmount(0.0);

			walletdto.setDatetime(walletInputDate.getStartDatetime());
			walletList.add(walletdto);
		}
		for(long i = 1; i <= totalHours; i++) {
			OffsetDateTime hourlyTime = OffsetDateTime.parse(walletInputDate.getStartDatetime()).plusHours(i); 
			Double sum = walletRepository.getTotalAmount(
					WalletUtil.toTimeStamp(walletInputDate.getStartDatetime()), 
					WalletUtil.offsetDatetimeToTimestamp(hourlyTime));
			WalletDTO walletdto = new WalletDTO();
			walletdto.setAmount(null == sum ? 0.0 : sum);
			walletdto.setDatetime(hourlyTime.toString().replace("Z", ":00+00:00"));
			walletList.add(walletdto);
		}
		return walletList;
	}

}
