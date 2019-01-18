package kr.green.spring.service;

import kr.green.spring.vo.AccountVo;

public interface AccountService {
	public AccountVo getAccount(String id);

	public boolean signup(AccountVo accountVo);

	public boolean signin(String id, String pw);

}
