package hiFes.hiFes.service;

import hiFes.hiFes.domain.HostUser;
import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.dto.GroupCreateDto;
import hiFes.hiFes.repository.group.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public void groupCreate(GroupCreateDto groupCreateDto){
        LocalDateTime now = LocalDateTime.of(2020,9,16,0,0,0);
        Group group = Group.builder()
                .groupName(groupCreateDto.getGroupName())
                .groupPic(groupCreateDto.getGroupPic())
                .maxPop(groupCreateDto.getMaxPop())
                .content(groupCreateDto.getContent())
                .groupPassword(groupCreateDto.getGroupPassword())
                .build();


        groupRepository.save(group);
    }

    public void groupDelete(Long id){
        groupRepository.deleteById(id);
    }

//    public List<GroupCreateDto> groupDetail(Long id){
//
//    }
//
//    public void groupSearch(){
//
//    }
}
