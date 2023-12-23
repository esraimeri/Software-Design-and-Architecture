package mk.ukim.finki.nationalheritage.service.impl;

import mk.ukim.finki.nationalheritage.model.Heritage;
import mk.ukim.finki.nationalheritage.model.HeritageType;
import mk.ukim.finki.nationalheritage.model.exceptions.InvalidHeritageIdException;
import mk.ukim.finki.nationalheritage.repository.HeritageRepository;
import mk.ukim.finki.nationalheritage.service.HeritageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeritageServiceImpl implements HeritageService {
    private final HeritageRepository heritageRepository;

    public HeritageServiceImpl(HeritageRepository heritageRepository) {
        this.heritageRepository = heritageRepository;
    }

    @Override
    public Heritage findById(Long id) {
        return this.heritageRepository.findById(id).orElseThrow(InvalidHeritageIdException::new);
    }

    @Override
    public List<Heritage> listAll() {
        return heritageRepository.findAll();
    }

    @Override
    public Heritage create(Long id,String name, HeritageType heritageType, String englishName, String description, String phoneNumber, String website, String location) {
        Heritage heritage = new Heritage(id,heritageType,name,englishName,description,phoneNumber,website,location);
        return this.heritageRepository.save(heritage);
    }


    @Override
    public Heritage update(Long id, String name, HeritageType heritageType, String englishName, String description, String phoneNumber, String website, String location) {
        Heritage heritage= this.heritageRepository.findById(id).orElseThrow(InvalidHeritageIdException::new);

        heritage.setName(name);
        heritage.setHeritageType(heritageType);
        heritage.setEnglishName(englishName);
        heritage.setDescription(description);
        heritage.setPhoneNumber(phoneNumber);
        heritage.setWebsite(website);
        heritage.setLocation(location);
        return this.heritageRepository.save(heritage);
    }

    @Override
    public Heritage delete(Long id) {
        Heritage h = this.heritageRepository.findById(id).orElseThrow(InvalidHeritageIdException::new);
        this.heritageRepository.delete(h);
        return h;
    }

    @Override
    public List<Heritage> filter(HeritageType heritageType, String location) {
        if(heritageType!=null&& location!=null){
            return heritageRepository.findByHeritageTypeAndLocation(heritageType,location);
        }
        if(heritageType!=null){
            return heritageRepository.findByHeritageType(heritageType);
        }
        if(location!=null){
            return heritageRepository.findByLocation(location);
        }
        return null;
    }




}
