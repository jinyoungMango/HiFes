package hiFes.hiFes.service.group;

import com.google.gson.JsonObject;
import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.domain.group.Hashtag;
import hiFes.hiFes.domain.group.JoinedGroup;
import hiFes.hiFes.domain.group.RegisteredHashtag;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.group.GroupCreateDto;
import hiFes.hiFes.repository.group.GroupRepository;
import hiFes.hiFes.repository.group.HashtagRepository;
import hiFes.hiFes.repository.group.JoinedGroupRepository;
import hiFes.hiFes.repository.group.RegisteredHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
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


    public void groupCreate(GroupCreateDto groupCreateDto, NormalUser normalUser, MultipartFile image) throws Exception {
        String projectPath = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\images";
        String imageName = image.getOriginalFilename();
        File saveImage = new File(projectPath, imageName);
        image.transferTo(saveImage);

//        LocalDateTime now = LocalDateTime.of(2020,9,16,0,0,0);
        Group group = Group.builder()
                .groupName(groupCreateDto.getGroupName())
                .groupPic("/images/"+ imageName)
                .maxPop(groupCreateDto.getMaxPop())
                .content(groupCreateDto.getContent())
                .festivalId(groupCreateDto.getFestivalId())
                .build();

        groupRepository.save(group);

        String[] hashtags = groupCreateDto.getHashtags();

        int hashLen = hashtags.length;
        for (int i = 0; i < hashLen; i++) {
            RegisteredHashtag registeredHashtag = new RegisteredHashtag();
            registeredHashtag.setGroup(group);
            if (hashtagRepository.existsByTitle(hashtags[i])) {
                Hashtag tag = hashtagRepository.findByTitle(hashtags[i]);
                registeredHashtag.setHashtag(tag);
            } else {
                Hashtag hashtag = Hashtag.builder()
                        .title(hashtags[i])
                        .build();
                hashtagRepository.save(hashtag);

                registeredHashtag.setHashtag(hashtag);
            }
            registeredHashtagRepository.save(registeredHashtag);
        }

        JoinedGroup joinedGroup = new JoinedGroup();
        // 이메일로 노말유저 아이디를 찾아서 저장
        joinedGroup.setNormalUser(normalUser);
        joinedGroup.setGroup(group);
        // 그룹을 생성한 사람이 모임장이 된다.
        joinedGroup.setIsLeader(true);

        joinedGroupRepository.save(joinedGroup);


        RegisteredHashtag registeredHashtag = new RegisteredHashtag();
        registeredHashtag.setGroup(group);

        registeredHashtagRepository.save(registeredHashtag);



    }

    public void groupJoin(NormalUser normalUser, Group group){
        JoinedGroup joinedGroup = new JoinedGroup();
        joinedGroup.setNormalUser(normalUser);
        joinedGroup.setGroup(group);
        joinedGroup.setIsLeader(false);
        joinedGroupRepository.save(joinedGroup);

    }

    public void groupDelete(Long id){
        groupRepository.deleteById(id);
    }

    public Group groupDetail(Long id){
        return groupRepository.findById(id).get();
    }

    public List<Group> findFesGroup(Long FesId){
        return groupRepository.findByFestivalId(FesId);
    }

    public Boolean isJoinedFesGroup(Long userId, Long fesId){
        List<JoinedGroup> joinedGroups = joinedGroupRepository.findByNormalUserId(userId);
        List<Long> festivalIds = new ArrayList<>();
        for (JoinedGroup joinedGroup : joinedGroups) {
            festivalIds.add(joinedGroup.getGroup().getFestivalId());
        }

        for (Long festivalId : festivalIds) {
            if (festivalId.equals(fesId)) {
                return true;
            }
        }
        return false;
    }


    public JsonObject isJoined(Long GroupId, NormalUser user){
        Group group = getById(GroupId);
        Long fesId = group.getFestivalId();
        Long userId = user.getId();

        JsonObject info = new JsonObject();
        // 이 그룹과 연관된 행사에 이 유저는 가입되어 있는가?
        info.addProperty("isJoinedFesGroup",isJoinedFesGroup(userId, fesId));



        if (joinedGroupRepository.existsByNormalUserAndGroup(user, group)){
            info.addProperty("isJoinedGroup", true);
            JoinedGroup joinedGroup =  joinedGroupRepository.findByNormalUserAndGroup(user, group);
            if (joinedGroup.getIsLeader()){
                info.addProperty("isLeader", true);
            }
            else{
                info.addProperty("isLeader", false);
            }
        }
        else{
            info.addProperty("isJoinedGroup", false);
            info.addProperty("isLeader", false);
        }
        return info;
    }

    public List<Group> getGrouplist(){
        return groupRepository.findAll();
    }

    public List<Group> getGroupSearch(String searchWord){
        return groupRepository.findBygroupNameContainingOrContentContaining(searchWord, searchWord);
    }

    public Group getById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public List<Group> getGroupHashtagSearch(String searchTag){
        List<RegisteredHashtag> registeredHashtags = registeredHashtagRepository.findByHashtagTitleContaining(searchTag);
        List<Group> groups = new ArrayList<>();
        for (RegisteredHashtag registeredHashtag : registeredHashtags) {
            Group group = registeredHashtag.getGroup();
            groups.add(group);
        }
        return groups;
    }

    public List<NormalUser> getJoinedPeople(Long groupId){
        List<JoinedGroup> joinedGroupsList = joinedGroupRepository.findByGroupId(groupId);
        List<NormalUser> joinedPeople = new ArrayList<>();
        NormalUser leader = null;
        for (JoinedGroup joinedGroup : joinedGroupsList) {
            if (joinedGroup.getIsLeader()){
                leader = joinedGroup.getNormalUser();
            }
            else{
                joinedPeople.add(joinedGroup.getNormalUser());
            }

        }
        joinedPeople.add(leader);

        return joinedPeople;
    }
}
