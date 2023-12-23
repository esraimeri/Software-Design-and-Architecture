package mk.ukim.finki.nationalheritage.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.nationalheritage.model.Heritage;
import mk.ukim.finki.nationalheritage.model.HeritageType;
import mk.ukim.finki.nationalheritage.repository.HeritageRepository;
import mk.ukim.finki.nationalheritage.service.HeritageService;
import mk.ukim.finki.nationalheritage.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataInitializer {

    private final HeritageService heritageService;
    private final UserService userService;
    private final HeritageRepository heritageRepository; // Assuming you have HeritageRepository
    private final Random random;
    public DataInitializer(HeritageService heritageService, UserService userService, HeritageRepository heritageRepository) {
        this.heritageService = heritageService;
        this.userService = userService;
        this.heritageRepository = heritageRepository;
        random= new Random();
    }

    private HeritageType randomizeHeritageType(int i) {
        return i % 2 == 0 ? HeritageType.MEMORIAL : HeritageType.BUILDING;
    }

    @PostConstruct
    public void initData() {
        // Fetch Heritage data from PostgreSQL
        List<Heritage> heritages = heritageRepository.findAll();

        // If no data exists, create some sample data
        if (heritages.isEmpty()) {
            for (int i = 1; i < 6; i++) {
                this.heritageService.create(
                        random.nextLong(),
                        "Temporary",
                        randomizeHeritageType(i),
                        "English Temporary",
                        "Description",
                        "+389 79 999 999",
                        "website.com",
                        "34T 561925.94117292 4607155.5667881"

                );
            }

            heritages = this.heritageService.listAll();
        }

        // Initialize User data
        for (int i = 1; i < 11; i++) {
            List<Long> visitedHeritageIds = Stream.of(heritages.get((i - 1) % 5), heritages.get((i + 1) % 5))
                    .map(Heritage::getId)
                    .collect(Collectors.toList());

            this.userService.create(
                    "User " + i,
                    "user" + i + "@example.com",
                    "password" + i,
                    visitedHeritageIds,
                    LocalDate.now().minusYears((i + 1) * 5)
            );
        }
    }
}
