package com.plukash.testuserservice.services;

import com.plukash.testuserservice.entities.*;
import com.plukash.testuserservice.repositories.AccountRepository;
import com.plukash.testuserservice.repositories.EmailDataRepository;
import com.plukash.testuserservice.repositories.PhoneDataRepository;
import com.plukash.testuserservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailDataRepository emailDataRepository;
    private final PhoneDataRepository phoneDataRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;


    public void init() {
        var mail_first = emailDataRepository.save(EmailData.builder().email("email_1@mail.ru").build());
        var mail_first_2 = emailDataRepository.save(EmailData.builder().email("email_2@mail.ru").build());
        var mail_second = emailDataRepository.save(EmailData.builder().email("email_3@mail.ru").build());
        var mail_second_2 = emailDataRepository.save(EmailData.builder().email("email_4@mail.ru").build());
        var mail_third = emailDataRepository.save(EmailData.builder().email("email_5@mail.ru").build());
        var mail_third_2 = emailDataRepository.save(EmailData.builder().email("email_6@mail.ru").build());

        var phone_first = phoneDataRepository.save(PhoneData.builder().phone("79281912174").build());
        var phone_first_2 = phoneDataRepository.save(PhoneData.builder().phone("79281912175").build());
        var phone_second = phoneDataRepository.save(PhoneData.builder().phone("79281912176").build());
        var phone_second_2 = phoneDataRepository.save(PhoneData.builder().phone("79281912177").build());
        var phone_third = phoneDataRepository.save(PhoneData.builder().phone("79281912178").build());
        var phone_third_2 = phoneDataRepository.save(PhoneData.builder().phone("79281912179").build());

        var acc_1 = accountRepository.save(Account.builder().balance(BigDecimal.valueOf(100.99)).build());
        var acc_2 = accountRepository.save(Account.builder().balance(BigDecimal.valueOf(100)).build());
        var acc_3 = accountRepository.save(Account.builder().balance(BigDecimal.valueOf(100)).build());

        var user_1 = userRepository.save(User.builder()
                .name("user_1")
                .password(passwordEncoder.encode("qwerty"))
                .role(Role.USER)
                .phoneDatas(new HashSet<>(Arrays.asList(phone_first, phone_first_2)))
                .emails(new HashSet<>(Arrays.asList(mail_first, mail_first_2)))
                .account(acc_1)
                .build());
        var user_2 = userRepository.save(User.builder()
                .name("user_2")
                .password(passwordEncoder.encode("qwerty"))
                .role(Role.USER)
                .phoneDatas(new HashSet<>(Arrays.asList(phone_second, phone_second_2)))
                .emails(new HashSet<>(Arrays.asList(mail_second, mail_second_2)))
                .account(acc_2)
                .build());
        var user_3 = userRepository.save(User.builder()
                .name("user_3")
                .password(passwordEncoder.encode("qwerty"))
                .role(Role.USER)
                .phoneDatas(new HashSet<>(Arrays.asList(phone_third, phone_third_2)))
                .emails(new HashSet<>(Arrays.asList(mail_third, mail_third_2)))
                .account(acc_3)
                .build());

        mail_first.setUser(user_1);
        mail_first_2.setUser(user_1);
        mail_second.setUser(user_2);
        mail_second_2.setUser(user_2);
        mail_third.setUser(user_3);
        mail_third_2.setUser(user_3);
        emailDataRepository.save(mail_first);
        emailDataRepository.save(mail_first_2);
        emailDataRepository.save(mail_second);
        emailDataRepository.save(mail_second_2);
        emailDataRepository.save(mail_third);
        emailDataRepository.save(mail_third_2);

        phone_first.setUser(user_1);
        phone_first_2.setUser(user_1);
        phone_second.setUser(user_2);
        phone_second_2.setUser(user_2);
        phone_third.setUser(user_3);
        phone_third_2.setUser(user_3);
        phoneDataRepository.save(phone_first);
        phoneDataRepository.save(phone_first_2);
        phoneDataRepository.save(phone_second);
        phoneDataRepository.save(phone_second_2);
        phoneDataRepository.save(phone_third);
        phoneDataRepository.save(phone_third_2);
    }
}
