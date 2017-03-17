/*
 * 
 * 
 */
package com.apu.seedshop.services;

import com.apu.seedshop.jpa.Appuser;
import com.apu.seedshop.jpa.Invoice;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apu.seedshop.repository.AppuserRepository;


@Service
public class UserService {
private static final Logger logger =  LoggerFactory.getLogger(UserService.class);   

@Autowired
AppuserRepository userRepository;

  
    public List<Appuser> getAllUsers(){
        return  userRepository.findAll();
    }

    public Appuser getUserById(Long id) {
        Appuser u = userRepository.findOne(id);
        return u;
    }
    
    public List<Appuser> findUserByName(String name){
        List<Appuser> udl = userRepository.findByFirstName(name);
        List<Appuser> res = new ArrayList<>();
        for(Appuser u:udl){
            System.out.println(u.getUserId() + ", " +
                                u.getSecName()+ ", " + 
                                u.getFirstName());
            res.add(u);
        }        
        return res;
    }
    
    public List<Appuser> findUserBySessionId(String sessionId){
        List<Appuser> udl = userRepository.findBySessId(sessionId);
        List<Appuser> res = new ArrayList<>();
        for(Appuser u:udl){
            res.add(u);
        }        
        return res;
    }
    
    public List<Appuser> findBySecNameAndFirstName(String secName, String firstName){
        List<Appuser> udl = userRepository.findBySecNameAndFirstName(secName, firstName);
        List<Appuser> res = new ArrayList<>();
        for(Appuser u:udl){
            System.out.println(u.getUserId() + ", " +
                                u.getSecName()+ ", " + 
                                u.getFirstName());
            res.add(u);
        }        
        return res;
    }

    public Appuser addUser(Appuser u) {
        logger.debug(String.format("Adding users %s %s %s with id %s", u.getFirstName(), u.getSecName(), u.getThirdName(), u.getUserId()));
        u = userRepository.save(u);
        return u;
    }

    public void delUser(Long id){
        Appuser u = userRepository.findOne(id);
        if(u!=null){
            logger.debug(String.format("Deleting users %s %s %s with id %s", u.getFirstName(), u.getSecName(), u.getThirdName(), u.getUserId()));
            //List<Ugroup> gl = u.getUgroupList();
            //detailsRepository.delete(id);
            if(u.getTemp() == true) {
                userRepository.delete(id);
            } else {
                u.setUsed(false);
                userRepository.save(u);
            }
        }
    }
    
    public List findInvoiceIdBySessionId(String sessId) throws Exception{
        List list = new ArrayList();
        
        List<Appuser> users = findUserBySessionId(sessId);
            if(!users.isEmpty()) {
                //if exist, then extract invoices for this user
                if(users.size() > 1) {
                    String err = "Error delete from basket. Two users with equal session_id";
                    logger.error(err);
                    throw new Exception(err);
                } 
                Appuser u = users.get(0);
                List<Invoice> invoices = (List<Invoice>)u.getInvoiceCollection();
                
                for(Invoice inv:invoices) {
                    //invoiceService.delInvoice(inv.getOrderId());
                    list.add(inv.getOrderId());
                }
                invoices = null;
                u = null;                               
            }     
        users = null; 
        return list;
    }
}
