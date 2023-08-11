package hiFes.hiFes.service.group;

import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.domain.group.MyPic;
import hiFes.hiFes.domain.group.SharedPic;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.repository.group.MyPicRepository;
import hiFes.hiFes.repository.group.SharedPicRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupPictureService {
    private final SharedPicRepository sharedPicRepository;
    private final MyPicRepository myPicRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String GroupPictureUpload(NormalUser normalUser, Group group, MultipartFile image) throws Exception{
        String projectPath = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\images";
        String imageName = image.getOriginalFilename();
        File saveImage = new File(projectPath, imageName);
        image.transferTo(saveImage);

        SharedPic sharedPic = SharedPic.builder()
                .sharedPic("/images/"+  imageName)
                .normalUser(normalUser)
                .group(group)
                .build();

        sharedPicRepository.save(sharedPic);


        MyPic myPic = new MyPic();
        myPic.setNormalUser(normalUser);
        myPic.setSharedPic(sharedPic);
        myPic.setGroup(group);

        myPicRepository.save(myPic);

        return "upload success";

    }

    public List<SharedPic> GroupPicCollect(Long groupId){
        return sharedPicRepository.findByGroupId(groupId);
    }

    public Long getNormalUserIdsForSharedPic(Long sharedPicId) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        logger.debug("debug log={}", sharedPicId);
        SharedPic sharedPic = sharedPicRepository.findById(sharedPicId)
                .orElseThrow(() -> new IllegalArgumentException("올린 사람을 찾을 수 없습니다. : " + sharedPicId));

        logger.debug("debug log={}", sharedPic.getSharedPic());


        return sharedPic.getNormalUser().getId();
    }

}
