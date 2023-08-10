package hiFes.hiFes.service;

import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.domain.group.Hashtag;
import hiFes.hiFes.domain.group.JoinedGroup;
import hiFes.hiFes.domain.group.RegisteredHashtag;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.group.GroupCreateDto;
import hiFes.hiFes.dto.group.HashTagDto;
import hiFes.hiFes.repository.group.GroupRepository;
import hiFes.hiFes.repository.group.HashtagRepository;
import hiFes.hiFes.repository.group.JoinedGroupRepository;
import hiFes.hiFes.repository.group.RegisteredHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final JoinedGroupRepository joinedGroupRepository;
    private final RegisteredHashtagRepository registeredHashtagRepository;
    private final HashtagRepository hashtagRepository;

    public void groupCreate(/*이메일*/GroupCreateDto groupCreateDto, NormalUser normalUser){
        LocalDateTime now = LocalDateTime.of(2020,9,16,0,0,0);
        Group group = Group.builder()
                .groupName(groupCreateDto.getGroupName())
                .groupPic(groupCreateDto.getGroupPic())
                .maxPop(groupCreateDto.getMaxPop())
                .content(groupCreateDto.getContent())
                .groupPassword(groupCreateDto.getGroupPassword())
                .build();


        groupRepository.save(group);

        ArrayList hashtags =  groupCreateDto.getHashtags();




        JoinedGroup joinedGroup = new JoinedGroup();
        // 이메일로 노말유저 아이디를 찾아서 저장
        joinedGroup.setNormalUser(normalUser);
        joinedGroup.setGroup(group);
        // 그룹을 생성한 사람이 모임장이 된다.
        joinedGroup.setIsLeader(true);

        joinedGroupRepository.save(joinedGroup);

//        Hashtag hashtag = Hashtag.builder()
//                .title(hashTagDto.getTitle())
//                .build();
//
//        hashtagRepository.save(hashtag);

        RegisteredHashtag registeredHashtag = new RegisteredHashtag();
        registeredHashtag.setGroup(group);

        registeredHashtagRepository.save(registeredHashtag);



    }

    public void groupDelete(Long id){
        groupRepository.deleteById(id);
    }

    public Group groupDetail(Long id){
        return groupRepository.findById(id).get();
    }

    public List<Group> getGrouplist(){
        return groupRepository.findAll();
    }

    public List<Group> getGroupSearch(String searchWord){
        return groupRepository.findBygroupNameContainingOrContentContaining(searchWord, searchWord);
    }
}
