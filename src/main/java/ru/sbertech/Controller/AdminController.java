package ru.sbertech.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.net.URI;
import java.util.List;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import ru.sbertech.DAO.DocumentDaoImpl;
import ru.sbertech.Logic.Document;
import ru.sbertech.jms.SpringJmsProducer;

import javax.servlet.http.HttpServletRequest;

import static ru.sbertech.Main.applicationContext;

@Controller
public class AdminController {

//    BrokerService broker = null;
    DocumentDaoImpl documentDao = (DocumentDaoImpl) applicationContext.getBean("DocumentDaoImpl");


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView frontPageAdmin() {
//        if (broker == null) {
//            try {
//                broker = BrokerFactory.createBroker(new URI(
//                        "broker:(tcp://localhost:61616)"));
//                broker.start();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        return new ModelAndView("admin");
    }

    @RequestMapping(value = {"/PrintDocuments"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Document> findAllDocuments() {
        return documentDao.documentList();
    }

    @RequestMapping(value = {"/AdminSendMessage"}, method = RequestMethod.GET)
    public ModelAndView frontPageClient(HttpServletRequest request) {
        Document document = (Document) applicationContext.getBean("ru.sbertech.Logic.Document");
        document = documentDao.getDocumentById(Long.parseLong(request.getParameter("idDocument")));
        if (document == null) {
            return new ModelAndView("admin", "messageCancel","Document with ID = "+request.getParameter("idDocument")+ " not found");
        } else {
            if (document.isStorno()) {
                return new ModelAndView("admin", "messageCancel","Document with ID = "+document.getId()+ " already cansel");
            } else {
                if (!document.getAccountDT().checkSaldo(document.getSumma())) {
                    return new ModelAndView("admin", "messageCancel","Account DT "+document.getAccountDT().getAccNum()+ " available " + document.getAccountDT().getSaldo()+ " document with ID = "+document.getId()+" not canceled");
                } else {
                    SpringJmsProducer producer = (SpringJmsProducer) applicationContext.getBean("springJmsProducer");
                    producer.sendMessage(request.getParameter("idDocument"));
                    return new ModelAndView("admin", "messageCancel","Document with ID = "+document.getId()+ " sent to the marking");
                }
            }
        }
    }

//    public BrokerService getBroker() {
//        return broker;
//    }
//
//    public void setBroker(BrokerService broker) {
//        this.broker = broker;
//    }

    public DocumentDaoImpl getDocumentDao() {
        return documentDao;
    }

    public void setDocumentDao(DocumentDaoImpl documentDao) {
        this.documentDao = documentDao;
    }
}
