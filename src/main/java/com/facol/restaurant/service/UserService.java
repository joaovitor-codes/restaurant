package com.facol.restaurant.service;

import com.facol.restaurant.dto.UserRequestDto;
import com.facol.restaurant.dto.UserResponseDto;
import com.facol.restaurant.entity.UserEntity;
import com.facol.restaurant.exception.NotFoundException;
import com.facol.restaurant.exception.ReferentialIntegrityException;
import com.facol.restaurant.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    // injeção de dependencia
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //

    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(u -> new UserResponseDto(
                        u.getId(),
                        u.getName())
                )
                .orElseThrow(() -> new NotFoundException("Usuário não Encontrado"));
                // vai tentar encontrar e se nao encontrar retorna um user nao encontrado
    }

    public Page<UserResponseDto> getUsers() {
        return findAll();
    }


    /*
        Retorna todos os usuários paginados
    */
    public Page<UserResponseDto> findAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "name");

        Page<UserEntity> userList = userRepository.findAll(pageRequest);

        /*
        GetContent: retorna a lista interna de userlist(page)
        stream: converte a lista em stream, é uma forma funcional de processar dados em sequência
        map: para cada elemento da lista(user), transforma em um novo objeto UserResponse
        collect: transformar todos os elementos do stream, você coleta tudo converte em uma Lista
        */
        List<UserResponseDto> dtoList = userList
                .getContent()
                .stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getName())
                )
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageRequest, userList.getTotalElements());
    }

    public void createUser(UserRequestDto userCreate) {
        UserEntity userEntity = new UserEntity();

        userEntity.setName(userCreate.name());
        userEntity.setEmail(userCreate.email());
        userEntity.setPassword(userCreate.password());

        userRepository.save(userEntity);
    }

    public void updateUser(Long id, UserRequestDto userUpdate) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        user.setName(userUpdate.name());
        user.setEmail(userUpdate.email());
        user.setPassword(userUpdate.password());

        userRepository.save(user);
    }

    public void pathUser(Long id, UserRequestDto parcialUpdate) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        if (parcialUpdate.name() != null) {
            user.setName(parcialUpdate.name());
        }
        if (parcialUpdate.email() != null) {
            user.setEmail(parcialUpdate.email());
        }
        if (parcialUpdate.password() != null) {
            user.setPassword(parcialUpdate.password());
        }
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usúario não Encontrado"));

        try{
            userRepository.delete(user);
        }catch (DataIntegrityViolationException e){
            throw new ReferentialIntegrityException("Não é possível deletar o usuário. Existem registros associados, como reviews.");
        }
    }
}
