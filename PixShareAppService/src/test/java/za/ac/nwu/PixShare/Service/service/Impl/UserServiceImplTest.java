package za.ac.nwu.PixShare.Service.service.Impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.PixShare.Domain.DTO.UserDTO;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserServiceImpl userService;

    UserDTO newUser, user;

    @Before
    public void setUp() throws Exception {
        newUser = new UserDTO(0,"Caitlyn","Opperman","ceopperman@gmail.com","caitlyn2000");
        user = userService.addUser(newUser);
    }

    @After
    public void tearDown() throws Exception {
        newUser = null;
        user = null;
    }

    @Test
    public void addUser() throws Exception {
        when(userService.addUser(any(UserDTO.class))).then(returnsFirstArg());
        UserDTO resultUser = userService.addUser(newUser);
        assertNotNull(resultUser);
        assertTrue(resultUser.getEmail().contains("@"));
        verify(userService, atLeastOnce()).addUser(any(UserDTO.class));
    }

    @Test
    public void checkUserExists() throws Exception {
        assertNotNull(newUser);
        when(userService.checkUserExists(newUser.getUserID())).thenReturn(true);
        Boolean exists = userService.checkUserExists(newUser.getUserID());
        assertTrue(exists);
        verify(userService, atLeastOnce()).checkUserExists(newUser.getUserID());
    }

    @Test
    public void getID() throws Exception {
        assertNotNull(newUser);
        Integer id = userService.getID(newUser.getEmail());
        assertEquals(newUser.getUserID(),id);
        verify(userService, atLeastOnce()).getID(newUser.getEmail());
    }
}


