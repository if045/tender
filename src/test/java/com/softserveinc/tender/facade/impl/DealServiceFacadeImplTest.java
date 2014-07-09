package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.FeedbackDto;
import com.softserveinc.tender.dto.FeedbackSaveDto;
import com.softserveinc.tender.dto.ConflictSaveDto;
import com.softserveinc.tender.facade.DealServiceFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional
public class DealServiceFacadeImplTest {
    private static final Integer TEST_COMMUNICATION = 5;
    private static final Integer TEST_SPEED = 5;
    private static final Integer TEST_LOGISTIC = 5;
    private static final String TEST_COMMENT = "test";
    private static final Integer TEST_PROFILE_ID = 4;
    private static final Integer TEST_USER_ID = 1;
    private static final String TEST_LOGIN = "odin_ogame@ukr.net";

    private static final Integer TEST_BID_ID = 4;
    private static final Integer TEST_MODERATOR_ID = 5;
    private static final Integer TEST_STATUS_ID = 6;


    private FeedbackSaveDto feedbackSaveDto;
/*    private ConflictSaveDto conflictSaveDto;*/

    @Autowired
    private DealServiceFacade dealServiceFacade;

    @Before
    public void setUp() throws Exception {
        feedbackSaveDto = new FeedbackSaveDto();
/*        conflictSaveDto = new ConflictSaveDto();*/
    }

    @Test
    public void testSaveFeedback() throws Exception {
        feedbackSaveDto.setComment(TEST_COMMENT);
        feedbackSaveDto.setCommunication(TEST_COMMUNICATION);
        feedbackSaveDto.setSpeed(TEST_SPEED);
        feedbackSaveDto.setLogistic(TEST_LOGISTIC);
        feedbackSaveDto.setProfileId(TEST_PROFILE_ID);

        SecurityContext sc = new SecurityContextImpl();
        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getCredentials() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getDetails() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getPrincipal() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public boolean isAuthenticated() {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public String getName() {
                return TEST_LOGIN;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };

        sc.setAuthentication(authentication);
        SecurityContextHolder.setContext(sc);

        FeedbackDto feedbackDto = dealServiceFacade.saveFeedback(feedbackSaveDto);
        assertEquals(TEST_COMMENT, feedbackDto.getComment());
        assertEquals(TEST_COMMUNICATION, feedbackDto.getCommunication());
        assertEquals(TEST_SPEED, feedbackDto.getSpeed());
        assertEquals(TEST_LOGISTIC, feedbackDto.getLogistic());
        assertEquals(TEST_USER_ID, feedbackDto.getUserId());

    }
}
   /* @Test
    public void testSaveConflict() throws Exception {
        conflictSaveDto.setDescription(TEST_COMMENT);
        conflictSaveDto.setBidId(TEST_BID_ID);
        conflictSaveDto.setModeratorId(TEST_MODERATOR_ID);
        conflictSaveDto.setStatusId(TEST_STATUS_ID);

        ConflictSaveDto conflictDto = dealServiceFacade.saveConflict(conflictSaveDto);
        assertEquals(TEST_COMMENT, conflictDto.getDescription());
        assertEquals(TEST_BID_ID, conflictDto.getBidId());
        assertEquals(TEST_MODERATOR_ID, conflictDto.getModeratorId());
        assertEquals(TEST_STATUS_ID, conflictDto.getStatusId());
    }
}*/
