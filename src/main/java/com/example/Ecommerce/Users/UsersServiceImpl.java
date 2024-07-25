package com.example.Ecommerce.Users;

import com.example.Ecommerce.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class UsersServiceImpl extends UserService {
@Autowired
    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        super(usersRepository);
    }

    @Override
    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void addNewUsers(Users users) {
        Optional<Users> usersOptional = usersRepository.findByEmail(users.getEmail());
        if (usersOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        usersRepository.save(users);
    }

    @Override
    public void deleteUsers(Long userId) {
        boolean exists = usersRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("user with id " + userId + " does not exist");
        }
        usersRepository.deleteById(userId);
    }

    @Override
    public void updateUsers(Long userId, Users users) {
        Users existingUsers = usersRepository.findById(userId).orElseThrow(() ->
                new IllegalStateException("user with id " + userId + " does not exist"));

        existingUsers.setAvatar(users.getAvatar());
        existingUsers.setFirstName(users.getFirstName());
        existingUsers.setLastName(users.getLastName());
        existingUsers.setUsername(users.getUsername());
        existingUsers.setEmail(users.getEmail());
        existingUsers.setBirthOfDate(users.getBirthOfDate());
        existingUsers.setPhoneNumber(users.getPhoneNumber());
        // Don't update createdAt to preserve original creation time

        usersRepository.save(existingUsers);
    }
}


