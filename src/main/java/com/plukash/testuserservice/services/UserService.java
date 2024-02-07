package com.plukash.testuserservice.services;

import com.plukash.testuserservice.entities.*;
import com.plukash.testuserservice.entities.DTO.CRUD.Create.CreateEmailDTO;
import com.plukash.testuserservice.entities.DTO.CRUD.Create.CreatePhoneDTO;
import com.plukash.testuserservice.entities.DTO.CRUD.Delete.DeleteEmailDTO;
import com.plukash.testuserservice.entities.DTO.CRUD.Delete.DeletePhoneDTO;
import com.plukash.testuserservice.entities.DTO.CRUD.Update.UpdateEmailDTO;
import com.plukash.testuserservice.entities.DTO.CRUD.Update.UpdatePhoneDTO;
import com.plukash.testuserservice.repositories.AccountRepository;
import com.plukash.testuserservice.repositories.UserRepository;
import com.plukash.testuserservice.utilities.CustomExceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailDataService emailDataService;
    private final PhoneDataService phoneDataService;

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    public User loadByCred(String userCredential) {
        return userRepository.findByEmailOrPhone(userCredential).orElseThrow(UserNotFoundException::new);
    }

    public User loadById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }


    public void init() {
        var mail_first = emailDataService.saveWithReturn(EmailData.builder().email("email_1@mail.ru").build());
        var mail_first_2 = emailDataService.saveWithReturn(EmailData.builder().email("email_2@mail.ru").build());
        var mail_second = emailDataService.saveWithReturn(EmailData.builder().email("email_3@mail.ru").build());
        var mail_second_2 = emailDataService.saveWithReturn(EmailData.builder().email("email_4@mail.ru").build());
        var mail_third = emailDataService.saveWithReturn(EmailData.builder().email("email_5@mail.ru").build());
        var mail_third_2 = emailDataService.saveWithReturn(EmailData.builder().email("email_6@mail.ru").build());

        var phone_first = phoneDataService.saveWithReturn(PhoneData.builder().phone("79281912174").build());
        var phone_first_2 = phoneDataService.saveWithReturn(PhoneData.builder().phone("79281912175").build());
        var phone_second = phoneDataService.saveWithReturn(PhoneData.builder().phone("79281912176").build());
        var phone_second_2 = phoneDataService.saveWithReturn(PhoneData.builder().phone("79281912177").build());
        var phone_third = phoneDataService.saveWithReturn(PhoneData.builder().phone("79281912178").build());
        var phone_third_2 = phoneDataService.saveWithReturn(PhoneData.builder().phone("79281912179").build());

        var acc_1 = new Account(BigDecimal.valueOf(100.99));
        var acc_2 = new Account(BigDecimal.valueOf(100));
        var acc_3 = new Account(BigDecimal.valueOf(100));
        accountRepository.save(acc_1);
        accountRepository.save(acc_2);
        accountRepository.save(acc_3);

        var user_1 = userRepository.save(User.builder()
                .name("user_1")
                .password(passwordEncoder.encode("qwerty"))
                .role(Role.USER)
                .phones(new HashSet<>(Arrays.asList(phone_first, phone_first_2)))
                .emails(new HashSet<>(Arrays.asList(mail_first, mail_first_2)))
                .account(acc_1)
                .build());
        var user_2 = userRepository.save(User.builder()
                .name("user_2")
                .password(passwordEncoder.encode("qwerty"))
                .role(Role.USER)
                .phones(new HashSet<>(Arrays.asList(phone_second, phone_second_2)))
                .emails(new HashSet<>(Arrays.asList(mail_second, mail_second_2)))
                .account(acc_2)
                .build());
        var user_3 = userRepository.save(User.builder()
                .name("user_3")
                .password(passwordEncoder.encode("qwerty"))
                .role(Role.USER)
                .phones(new HashSet<>(Arrays.asList(phone_third, phone_third_2)))
                .emails(new HashSet<>(Arrays.asList(mail_third, mail_third_2)))
                .account(acc_3)
                .build());

        mail_first.setUser(user_1);
        mail_first_2.setUser(user_1);
        mail_second.setUser(user_2);
        mail_second_2.setUser(user_2);
        mail_third.setUser(user_3);
        mail_third_2.setUser(user_3);
        emailDataService.save(mail_first);
        emailDataService.save(mail_first_2);
        emailDataService.save(mail_second);
        emailDataService.save(mail_second_2);
        emailDataService.save(mail_third);
        emailDataService.save(mail_third_2);

        phone_first.setUser(user_1);
        phone_first_2.setUser(user_1);
        phone_second.setUser(user_2);
        phone_second_2.setUser(user_2);
        phone_third.setUser(user_3);
        phone_third_2.setUser(user_3);
        phoneDataService.save(phone_first);
        phoneDataService.save(phone_first_2);
        phoneDataService.save(phone_second);
        phoneDataService.save(phone_second_2);
        phoneDataService.save(phone_third);
        phoneDataService.save(phone_third_2);
    }

    public void createEmail(Long userId, CreateEmailDTO dto) {
        var user = this.loadById(userId);
        var new_data = emailDataService.createInstance(dto);
        user.addEmail(new_data);
        userRepository.save(user);
        emailDataService.createLink(user, new_data);
    }

    public void createPhone(Long userId, CreatePhoneDTO dto) {
        var user = this.loadById(userId);
        var new_data = phoneDataService.createInstance(dto);
        user.addPhone(new_data);
        userRepository.save(user);
        phoneDataService.createLink(user, new_data);
    }

    public void changeEmail(Long userId, UpdateEmailDTO dto) {
        var user = this.loadById(userId);
        emailDataService.update(user, dto.getOldEmail());
    }

    public void changePhone(User user, UpdatePhoneDTO dto) {
        phoneDataService.update(user, dto.getOldPhone());
    }

    public void deleteEmail(User user, DeleteEmailDTO dto) {
        emailDataService.delete(user, dto.getEmail());
    }

    public void deletePhone(User user, DeletePhoneDTO dto) {
        phoneDataService.delete(user, dto.getPhone());
    }
}
