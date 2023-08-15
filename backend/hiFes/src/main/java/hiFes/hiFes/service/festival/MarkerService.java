package hiFes.hiFes.service.festival;

import hiFes.hiFes.domain.festival.Marker;
import hiFes.hiFes.repository.festival.MarkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class MarkerService {

    private final MarkerRepository markerRepository;


    public Marker findById(long id){
        return markerRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found:" + id));
    }

    public List<Marker> findByFestivalId(long festivalId){
        return markerRepository.findByOrganizedFestival_festivalId(festivalId);
    }
}
