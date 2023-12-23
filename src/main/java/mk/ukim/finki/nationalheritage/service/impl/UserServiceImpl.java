package mk.ukim.finki.nationalheritage.service.impl;

import mk.ukim.finki.nationalheritage.model.HeritageType;
import mk.ukim.finki.nationalheritage.model.User;
import mk.ukim.finki.nationalheritage.model.exceptions.InvalidUserIdException;
import mk.ukim.finki.nationalheritage.repository.HeritageRepository;
import mk.ukim.finki.nationalheritage.repository.UserRepository;
import mk.ukim.finki.nationalheritage.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final HeritageRepository heritageRepository;

    public UserServiceImpl(UserRepository userRepository, HeritageRepository heritageRepository) {
        this.userRepository = userRepository;
        this.heritageRepository = heritageRepository;
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(InvalidUserIdException::new);
    }

    @Override
    public User create(String name, String email, String password, List<Long> heritageIds, LocalDate birthday) {

        return null;
    }

    @Override
    public User update(Long id, String name, String email, String password, List<Long> interestId, LocalDate birthday) {
        return null;
    }

    @Override
    public User delete(Long id) {
        return null;
    }
    //TODO further implementation
    @Override
    public List<User> filter(String city, String description, HeritageType type, String location,LocalDate birthday, HeritageType heritageType) {
        if(birthday!=null){
            return this.userRepository.filterByBirthday();
        }
        if(location!=null){
           // return this.heritageRepository.filterByLocation(heritageType,location);
        }
        return null;

    }


}
