package mk.ukim.finki.nationalheritage.service.impl;

import mk.ukim.finki.nationalheritage.model.Heritage;
import mk.ukim.finki.nationalheritage.model.HeritageType;
import mk.ukim.finki.nationalheritage.model.User;
import mk.ukim.finki.nationalheritage.model.exceptions.InvalidHeritageIdException;
import mk.ukim.finki.nationalheritage.model.exceptions.InvalidUserIdException;
import mk.ukim.finki.nationalheritage.repository.HeritageRepository;
import mk.ukim.finki.nationalheritage.repository.UserRepository;
import mk.ukim.finki.nationalheritage.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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


        List<Heritage> visitedHeritages = heritageIds.stream()
                .map(heritageId -> heritageRepository.findById(heritageId)
                        .orElseThrow(InvalidHeritageIdException::new))
                .collect(Collectors.toList());

        User user = new User(name, email, password, visitedHeritages, birthday);
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, String name, String email, String password, List<Long> visitedId, LocalDate birthday) {

        List<Heritage> visitedHeritages = visitedId.stream()
                .map(heritageId -> heritageRepository.findById(heritageId)
                        .orElseThrow(InvalidHeritageIdException::new))
                .collect(Collectors.toList());

        User user = userRepository.findById(id).orElseThrow(InvalidUserIdException::new);

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setClosestHeritages(visitedHeritages);
        user.setBirthday(birthday);

        return userRepository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(InvalidUserIdException::new);
        userRepository.delete(user);
        return user;
    }

    @Override
    public List<User> filter(String city, String description, HeritageType type, String location, LocalDate birthday, HeritageType heritageType) {

        if (birthday != null) {
            return userRepository.findByBirthday(birthday);
        }
        if (location != null) {
            List<Heritage> heritageList = heritageRepository.findByLocation(location);
            return userRepository.findByVisited(heritageList);
        }
        return null;
    }
    @Override
    public List<Heritage> getClosestHeritages(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(InvalidUserIdException::new);
        Double latUser= user.getLat();
        Double lonUser = user.getLon();
        List<Heritage> heritages = this.heritageRepository.findAll();
        return  heritages.stream()
                .filter(l -> calculateDistance(l.getLat(), l.getLon(), latUser, lonUser) <= 65)
                .collect(Collectors.toList());
    }



    public static Double calculateDistance(Double latHeritage, Double lonHeritage, Double latLocation, Double lonLocation)
    {
        Integer radius = 6371;
        Double distanceLat = Math.toRadians(latLocation - latHeritage);
        Double distanceLon = Math.toRadians(lonLocation - lonHeritage);

        Double a = Math.sin(distanceLat / 2) * Math.sin(distanceLat / 2) +
                Math.cos(Math.toRadians(latHeritage)) * Math.cos(Math.toRadians(latLocation))*

                        Math.sin(distanceLon / 2) * Math.sin(distanceLon / 2);

        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distance = radius * c;

        return distance;
    }

}
