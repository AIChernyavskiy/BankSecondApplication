import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import ru.sbertech.Controller.AdminController;
import ru.sbertech.DAO.DocumentDaoImpl;

import javax.servlet.http.HttpServletRequest;


@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminControllerTest {

    public ApplicationContext testApplicationContext = new ClassPathXmlApplicationContext("application-context-test.xml");

    @Autowired
    AdminController adminController;

    @Before
    public void setUp(){
        DocumentDaoImpl mockDocumentDao = Mockito.mock(DocumentDaoImpl.class);
        adminController.setDocumentDao(mockDocumentDao);
        Mockito.when(mockDocumentDao.getDocumentById(1)).thenReturn(null);
    }

    @Test
    public void testFindAllDocuments() {
        Assert.assertNotNull(adminController.findAllDocuments());
    }

    @Test
    public void testFrontPageClient() {
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        Mockito.when(mockRequest.getParameter("idDocument")).thenReturn(String.valueOf(1));
        ModelAndView modelAndView = adminController.frontPageClient(mockRequest);
        Assert.assertEquals("admin",modelAndView.getViewName());
        Assert.assertNotNull(modelAndView.getModel().get("messageCancel"));

    }
}
