package com.example.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatis.mapper.AccountMapper;
import com.example.mybatis.pojo.Account;
import com.example.mybatis.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * AccountServiceImpl
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-28 13:36:23
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
}
