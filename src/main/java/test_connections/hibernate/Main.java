package test_connections.hibernate;

import com.lanit.satonin18.model.service.OrganizationService;
import com.lanit.satonin18.model.service.OrganizationServiceImp;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.lang.invoke.MethodHandles;

public class Main {
    private static final Logger log = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        OrganizationService organizationService = new OrganizationServiceImp();
        log.log(Level.INFO,organizationService.organizations());

        //Organization organization = new Organization("MashaCORPARATION",true);
        //organizationService.saveOrganization(organization);
    }
}
