package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    List<User> entity = userRepository.findByName(username);
    Optional<User> first = entity.stream().findFirst();
    if (first.isPresent()){
      User user = first.get();

      return new org.springframework.security.core.userdetails.User(
        user.getName(),
        user.getPassword(),
        AuthorityUtils.createAuthorityList()
      );
    }
    throw new UsernameNotFoundException("");
  }
}
