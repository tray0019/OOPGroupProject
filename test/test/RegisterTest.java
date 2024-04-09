//package test;
//
//import DataAccessLayer.UserDAO;
//import Model.CharitableOrganizationDTO;
//import Model.ConsumersDTO;
//import Model.CredentialsDTO;
//import Model.RetailersDTO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.platform.runner.JUnitPlatform;
//import org.junit.runner.JUnitCore;
//import org.junit.runner.Result;
//import org.junit.runner.RunWith;
//import org.junit.runner.notification.Failure;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(JUnitPlatform.class)
//public class RegisterTest {
//
//    public UserDAO userDAO;
//
//    @BeforeEach
//    public void initial() {
//        userDAO = new UserDAO();
//    }
//
//    @Test
//    public void registerTest() {
//        String email = "mahsa@gmail.com";
//        String password = "123456";
//        String address = "address";
//        String phone = "phone";
//
//        String userType = "retailer";
//
//        // retailer
//        String retailerName = "retailer_name";
//
//        // consumer
//        String firstName = "Mahsa";
//        String lastName = "Mahsa";
//
//        // charitableOrg
//        String charityName = "charityName";
//
//        UserDAO userDAO = new UserDAO();
//        CredentialsDTO user = null;
//
//        switch (userType) {
//            case "retailer":
//                user = new RetailersDTO();
//                ((RetailersDTO) user).setRetailerName(retailerName);
//                break;
//            case "consumer":
//                user = new ConsumersDTO();
//                ((ConsumersDTO) user).setFirstName(firstName);
//                ((ConsumersDTO) user).setLastName(lastName);
//                break;
//            case "charitableOrg":
//                user = new CharitableOrganizationDTO();
//                ((CharitableOrganizationDTO) user).setCharitableOrgName(charityName);
//                break;
//        }
//
//        assertNotNull(user);
//
//        user.setEmailAddress(email);
//        user.setPassword(password);
//        user.setLocation(address);
//        user.setPhoneNumber(phone);
//        user.setUserType(userType);
//
//        assertTrue(userDAO.addUser(user));
//    }
//
//    public static void main(String[] args) {
//        Result result = JUnitCore.runClasses(RegisterTest.class);
//        for (Failure failure : result.getFailures()) {
//            System.err.println(failure.toString());
//        }
//        if (result.wasSuccessful()) {
//            System.out.println("All tests passed successfully.");
//        }
//    }
//
//}
