package hiFes.hiFes.dto.group;

import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.domain.group.Hashtag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GroupListDto {
    private Long id;
    private String groupName;
    private String groupPic;
    private LocalDateTime createdAt;
    private Integer maxPop;
    private String content;

    private Long festivalId;
    private int numOfPeople;
    private List<String> hashtags;


    public GroupListDto(Group group, int numOfPeople, List<Hashtag> hashtagList) {
        this.id = group.getId();
        this.groupName = group.getGroupName();
        this.groupPic = group.getGroupPic();
        this.createdAt = group.getCreatedAt();
        this.maxPop = group.getMaxPop();
        this.content = group.getContent();
        this.festivalId = group.getFestivalId();
        this.numOfPeople = numOfPeople;
        this.hashtags = getHashtags(hashtagList);
    }




    public int getNumOfPeople() {
        return numOfPeople;
    }

    public List getHashtags(List<Hashtag> hashtagList){
        List hashtagsList = new ArrayList<>();
        try {
            for (Hashtag hashtag : hashtagList) {
                hashtagsList.add(hashtag.getTitle());
            }
        }
        catch (Exception e){

        }


        return hashtagsList;
    }
}
