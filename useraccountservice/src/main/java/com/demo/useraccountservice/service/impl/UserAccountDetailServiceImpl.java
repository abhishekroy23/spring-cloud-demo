package com.demo.useraccountservice.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.useraccountservice.dto.UserAccountDetailDTO;
import com.demo.useraccountservice.entity.UserAccountDetail;
import com.demo.useraccountservice.entity.UserTransactionHist;
import com.demo.useraccountservice.enums.TransactionType;
import com.demo.useraccountservice.enums.TransferStatus;
import com.demo.useraccountservice.mapper.EntityMapper;
import com.demo.useraccountservice.repository.UserAccountDetailRepository;
import com.demo.useraccountservice.service.UserAccountDetailService;
import com.demo.useraccountservice.service.UserTransactionHistService;
@Service
@Transactional
public class UserAccountDetailServiceImpl implements UserAccountDetailService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityMapper entityMapper;
	@Autowired
	UserAccountDetailRepository userAccountDetailRepository;
	@Autowired
	UserTransactionHistService userTransactionHistService;
	
	@Value("${account.default.balance}")
	Long defaultAccountBalance;
	
	@Override
	public UserAccountDetailDTO createUserAccount(UserAccountDetailDTO userAccountDetailDTO) {
		UserAccountDetail userAccountDetail = entityMapper.mapToUserAccountEntity(userAccountDetailDTO);
		userAccountDetail = userAccountDetailRepository.save(userAccountDetail);
		return entityMapper.mapToUserAccountDTO(userAccountDetail);
	}

	@Override
	public UserAccountDetailDTO createUserAccount(Long userId) {
		UserAccountDetail userAccountDetail = new UserAccountDetail();
		userAccountDetail.setAccountNumber(RandomStringUtils.random(8, true, false));
		userAccountDetail.setBalance(defaultAccountBalance);
		userAccountDetail.setUserId(userId);
		userAccountDetail = userAccountDetailRepository.save(userAccountDetail);
		return entityMapper.mapToUserAccountDTO(userAccountDetail);
	}

	@Override
	public String fundsTransfer(String fromAccount, String toAccount, Long amount) {
		List<UserAccountDetail> lstAccount = userAccountDetailRepository.findByAccountNumberIn(Arrays.asList(fromAccount,toAccount));
		
		Optional<UserAccountDetail> optFromAcc = lstAccount.stream().filter(a -> a.getAccountNumber().equals(fromAccount)).findFirst();
		Optional<UserAccountDetail> optToAcc = lstAccount.stream().filter(a -> a.getAccountNumber().equals(toAccount)).findFirst();
		if(optFromAcc.isPresent() && optToAcc.isPresent()) {
			if(optFromAcc.get().getBalance()<amount) {
				return TransferStatus.FAILED_INSUFF.getValue();
			}else {
				optFromAcc.get().setBalance(optFromAcc.get().getBalance()-amount);
				optToAcc.get().setBalance(optToAcc.get().getBalance()+amount);
				List<UserAccountDetail> lstUpdatedAccDetails = userAccountDetailRepository.saveAll(Arrays.asList(optFromAcc.get(),optToAcc.get()));
				logger.info("[fundsTransfer] saved account: {}",lstUpdatedAccDetails);
				UserTransactionHist userTransactionTo = new UserTransactionHist();
				userTransactionTo.setAccountNumber(toAccount);
				userTransactionTo.setBalance(optToAcc.get().getBalance());
				userTransactionTo.setTransactionAmount(amount);
				userTransactionTo.setTransactionType(TransactionType.CREDIT.getValue());
				userTransactionTo.setAccount(optToAcc.get());
				
				UserTransactionHist userTransactionFrom = new UserTransactionHist();
				userTransactionFrom.setAccountNumber(fromAccount);
				userTransactionFrom.setBalance(optFromAcc.get().getBalance() );
				userTransactionFrom.setTransactionAmount(amount);
				userTransactionFrom.setTransactionType(TransactionType.DEBIT.getValue());
				userTransactionFrom.setAccount(optFromAcc.get());
				
				userTransactionHistService.createUserTransactionHist(Arrays.asList(userTransactionFrom,userTransactionTo));
				
				return TransferStatus.SUCCESS.getValue();
			}
		}else {
			return TransferStatus.ACCOUNT_NOT_FOUND.getValue();
		}
	}

	@Override
	public UserAccountDetailDTO getUserAccount(Long userId) {
		Optional<UserAccountDetail> userAccountDetail = userAccountDetailRepository.findByUserId(userId);
		if(userAccountDetail.isPresent()) {
			return entityMapper.mapToUserAccountDTO(userAccountDetail.get());
		}
		return null;
	}

}
