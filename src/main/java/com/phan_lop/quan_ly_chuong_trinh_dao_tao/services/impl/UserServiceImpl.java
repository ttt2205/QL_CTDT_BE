package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.UserReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.UserResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.User;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.exception.BadRequestException;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.UserMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.UserRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.utils.PasswordUtils;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResDto createUser(UserReqDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
        String encodedPassword = PasswordUtils.hashPassword(request.getPassword());
        User user = User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .username(request.getUsername())
                .role(request.getRole())
                .soDienThoai(request.getSoDienThoai())
                .status(request.getStatus())
                .build();

        return UserMapper.toResponse(userRepository.save(user));
    }

    @Override
    public List<UserResDto> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(user -> !user.isDeleted())
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public UserResDto updateUser(Long id, UserReqDto request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(PasswordUtils.hashPassword(request.getPassword()));
        }
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());
        user.setSoDienThoai(request.getSoDienThoai());
        user.setStatus(request.getStatus());
        user.setUpdatedAt(LocalDateTime.now());
        return UserMapper.toResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        user.softDelete(null);
        userRepository.save(user);
    }

    @Override
    public UserResDto getUserById(Long id) {
        User user = userRepository.findById(id).stream()
                .filter(user1 -> !user1.isDeleted())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        return UserMapper.toResponse(user);
    }
}
