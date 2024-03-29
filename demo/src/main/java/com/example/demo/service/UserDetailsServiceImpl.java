package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(
    final String username
  ) throws UsernameNotFoundException {
    final List<User> entity = userRepository.findByName(username);
    if (entity.isEmpty()) {
      throw new UsernameNotFoundException("該当データなし");
    }
    final Optional<User> first = entity.stream().findFirst();
    final User user =
      first.orElseThrow(() -> new UsernameNotFoundException(""));

    return new org.springframework.security.core.userdetails.User(
      user.getName(),
      user.getPassword(),
      AuthorityUtils.createAuthorityList()
    );
  }
}
