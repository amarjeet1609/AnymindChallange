package jp.co.anymind.walletapp.repositories;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.anymind.walletapp.entities.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

	@Query(value = "SELECT * FROM WALLET where DATETIME = ?1", nativeQuery = true)
	Wallet getWallet(Timestamp datetime);

	@Query(value = "SELECT sum(amount) FROM WALLET where DATETIME >= ?1 AND DATETIME <= ?2", nativeQuery = true)
	Double getTotalAmount(Timestamp timeStamp, Timestamp offsetDatetimeToTimestamp);
}
