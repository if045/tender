import com.softserveinc.tender.dto.ConflictDto;
import com.softserveinc.tender.dto.FeedbackDto;
import com.softserveinc.tender.entity.Bid;

import com.softserveinc.tender.entity.Conflict;
import com.softserveinc.tender.entity.ConflictStatus;
import com.softserveinc.tender.entity.Feedback;
import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.entity.User;
import com.softserveinc.tender.facade.impl.DealServiceFacadeImpl;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class DealServiceFacadeImplTest {

    private DealServiceFacadeImpl dealServiceFacadeImpl;

    @Before
    public void setUp() throws Exception {
       dealServiceFacadeImpl = new DealServiceFacadeImpl();
    }

    @Test
    public void testMapConflict() throws Exception {
        Conflict conflict = new Conflict();
        conflict.setId(1);
        Bid bid = new Bid();
        bid.setId(2);
        conflict.setBid(bid);
        conflict.setDescription("Test");
        ConflictStatus conflictStatus = new ConflictStatus();
        conflictStatus.setId(3);
        conflict.setStatus(conflictStatus);
        ConflictDto conflictDto = dealServiceFacadeImpl.mapConflict(conflict); //!!!
        assertEquals(new Integer(1), conflictDto.getId());
        assertEquals(new Integer(2), conflictDto.getBidId());
        assertEquals(new String("Test"), conflictDto.getDescription());
        assertEquals(new Integer(3), conflictDto.getStatusId());
    }

    @Test
    public void testMapFeedback() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setId(1);
        Profile profile = new Profile();
        profile.setId(2);
        feedback.setProfile(profile);
        User user = new User();
        user.setId(3);
        feedback.setUser(user);
        feedback.setCommunication(5);
        feedback.setSpeed(5);
        feedback.setLogistic(5);
        feedback.setComment("Test");
        FeedbackDto feedbackDto = dealServiceFacadeImpl.mapFeedback(feedback);
        assertEquals(new Integer(1),feedbackDto.getId());
        assertEquals(new Integer(2),feedbackDto.getProfileId());
        assertEquals(new Integer(3),feedbackDto.getUserId());
        assertEquals(new Integer(5),feedbackDto.getCommunication());
        assertEquals(new Integer(5),feedbackDto.getSpeed());
        assertEquals(new Integer(5),feedbackDto.getLogistic());
        assertEquals(new String("Test"),feedbackDto.getComment());
    }
}
