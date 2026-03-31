package Service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.WebParam;

@WebService
public interface BankService {

    @WebMethod
    String createAccount(@WebParam(name = "id") String id,  @WebParam(name = "owner") String owner, @WebParam(name = "initialBalance") double initialBalance);

    @WebMethod
    String deposit(@WebParam(name = "id") String id, @WebParam(name = "amount") double amount);

    @WebMethod
    String withdraw(@WebParam(name = "id") String id, @WebParam(name = "amount") double amount);

    @WebMethod
    double getBalance(@WebParam(name = "id") String id);

    @WebMethod
    String transfer(@WebParam(name = "fromId") String fromId, @WebParam(name = "toId") String toId, @WebParam(name = "amount") double amount);
}